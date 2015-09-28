package startcontrol;

import ireport.JasperReportController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.Test;

import pojo.CXFreturnResult;
import pojo.ExpectedDataObject;
import pojo.InitialDataObject;
import pojo.ResultDataObject;
import util.ClientSocketHandlerPool;
import util.DateFormatUtil;
import util.ReadProperties;
import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;

public class SocketClient{

	//用于存储调每条Case的成功状态 1 表示成功，0表示失败
	public static ConcurrentHashMap <String,String> statusMap = new ConcurrentHashMap<String,String>();
	
    //客户端开始时间
	static long clientStartTime = System.currentTimeMillis();
	//总共已经处理的数目
	static int tempNum = 0;
	//需要处理的case总数目
	static int totalNum  = 0;
	
	/**
	 * 用于监控整个流程的进度
	 */
	public static void processMonitor(){
		++tempNum;
		float persent = ((float)tempNum/(float)totalNum)*100;
		System.out.println("总条数为："+totalNum+"  已经处理条数 ： "+tempNum+" 总进度为："+persent+"%"+"  现已用时："+(System.currentTimeMillis() - clientStartTime));
	    if(tempNum!=0 &&totalNum==tempNum){
	    	
	    	// update the success_status of tb_socketcommunicationconfirm table
	    	DataBaseMethodHandler handler = new DataBaseMethodHandlerImpl();
	    	handler.initDBEnvironment();
	    	int id = handler.getMaxSSCdataID();
	    	String sql = "update tb_socketcommunicationconfirm set success_status='1' where id="+id;
	    	handler.updateSocketComConData(sql);
	    	handler.closeAll();
	    	System.out.println("本次TestCase成功运行!");
	    	
//	    	//read the basic configuration from properties file
//	    	ReadProperties pro = new ReadProperties();
//	    	pro.setPropertiesFilePath("ireportConfig.properties");
//	    	String reportType=pro.getKeyValue("reportType")==null?"":pro.getKeyValue("reportType");
//			String modelFilePath=SocketClient.class.getResource("/").getPath().toString()+"ireportfile/";
//			System.out.println("modelFilePath = "+modelFilePath);
//			String modelFileName=pro.getKeyValue("modelFileName")==null?"":pro.getKeyValue("modelFileName");
//			String outputPath=pro.getKeyValue("outputPath")==null?"":pro.getKeyValue("outputPath");
//			String sqlDriver=pro.getKeyValue("sqlDriver")==null?"":pro.getKeyValue("sqlDriver");
//			String connectUrl=pro.getKeyValue("connectUrl")==null?"":pro.getKeyValue("connectUrl");
//			String userName=pro.getKeyValue("userName")==null?"":pro.getKeyValue("userName");
//			String password=pro.getKeyValue("password")==null?"":pro.getKeyValue("password");
//			
//			//read the sql configutation file to generate the sql Array for report generation
//	    	pro.setPropertiesFilePath("ireportSQLConfig.properties");
//	    	String [] sqlArr = pro.getSortedSqlArrayFromProperties();
//	    	for(int k=0;k<sqlArr.length;k++){
//	    		
//	    		System.out.println(sqlArr[k]);
//	    		
//	    	}
//	    	if(sqlArr ==null || sqlArr.length==0){
//	    		try {
//					throw new Exception("The report's SQL Array can't be null，please check the SQL configuration file ireportSQLConfig.properties!");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//	    	}
//	    	
//	    	
//	    	try {
//				JasperReportController.genFile(modelFilePath, reportType, modelFileName, outputPath, sqlArr, sqlDriver, connectUrl, userName,password,null);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	    }
	    
	}
	
	
	@Test
	public void start() {
		DataBaseMethodHandler dataHandler = new DataBaseMethodHandlerImpl();
		dataHandler.initDBEnvironment();
		List<InitialDataObject> list  = dataHandler.fetchAll();
		totalNum =list.size();
		TestRunnable[] trs = new TestRunnable[totalNum];
		try {
			for (int i = 0; i < list.size(); i++) {
				
				//將每一DataObject数据交给线程去处理，同时将线程交给线程池去管理
				final Socket socket = new Socket("127.0.0.1", 7777);
				socket.setSoTimeout(600000);
				final InitialDataObject object = (InitialDataObject) list.get(i);
				
				  TestRunnable runner = new TestRunnable() { 
		                @Override 
		                public void runTest() throws Throwable { 

		    				
		    				ObjectInputStream ois = null;
		    				ObjectOutputStream oos = null;
		    				Object obj = null;
		    				CXFreturnResult result = null;
		    				try {
		    					
		    					oos = new ObjectOutputStream(socket.getOutputStream());
		    					oos.writeObject(object);
		    					oos.flush();
		    					ois = new ObjectInputStream(socket.getInputStream());
		    					obj = ois.readObject();
		    					result = (CXFreturnResult) obj;
		    					String dataId = result.getDataId();
		    					
		    					DataBaseMethodHandler dataHandler = new DataBaseMethodHandlerImpl();
		    					dataHandler.initDBEnvironment();
		    					ExpectedDataObject expectData = dataHandler.getDataObjectById(dataId);
		    					
		    					
		    					//Assert.assertEquals(result.getExpectedValue(),expectData.getExpectedValue());
		    					
		    					String passResult = "2";
		    					if(Float.parseFloat(result.getExpectedValue())== expectData.getExpectedValue()){
		    						passResult = "1";
		    					}
		    					
		    					ResultDataObject insertObj = new ResultDataObject();
		    					insertObj.setDataId(result.getDataId());
		    					insertObj.setAccount(result.getAccount());
		    					insertObj.setActuallyValue(Float.parseFloat(result.getExpectedValue()));
		    					insertObj.setExpectedValue(expectData.getExpectedValue());
		    					
		    					insertObj.setModifyTime(DateFormatUtil.getFormatNowTime("yyyyMMddHHmmss"));
		    					insertObj.setMsgType(result.getMsgType());
		    					insertObj.setOnbehalfOfCompId(result.getOnBehalfOfCompID());
		    					insertObj.setResult(passResult);
		    					insertObj.setTransactTime(result.getTransactTime());
		    					dataHandler.initDBEnvironment();
		    					dataHandler.insertDataToDB(insertObj);
		    					SocketClient.processMonitor();
		    					
		    					
		    				} catch (IOException e) {
		    					e.printStackTrace();
		    				} catch (ClassNotFoundException e) {
		    					e.printStackTrace();
		    				}finally {
		    					
		    					if(ois != null){
		    						try {
		    							ois.close();
		    						} catch (IOException e) {
		    							e.printStackTrace();
		    						}
		    					}
		    					if(oos !=null){
		    						try {
		    							oos.close();
		    						} catch (IOException e) {
		    							e.printStackTrace();
		    						}
		    					}
		    					if (socket != null) {
		    						try {
		    							socket.close();
		    						} catch (IOException e) {
		    							e.printStackTrace();
		    						}
		    					}
		    				}
		    			
		                } 
		                
		            }; 
		            trs[i] = runner;
		           
				//ClientSocketHandlerPool.addSocketToThreadPool(socket, object);
		           
			}
			MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
			try { 
				// 开发并发执行数组里定义的内容 
				mttr.runTestRunnables(); 
			} catch (Throwable e) { 
				e.printStackTrace(); 
			} 
		 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//ClientSocketHandlerPool.shutDownThreadPool();
		}
	}

/*	public static void main(String args[]) throws ClassNotFoundException, InterruptedException {
		//启动客户端
		SocketClient client = new SocketClient();
		client.start();
		
	}*/
	


}
