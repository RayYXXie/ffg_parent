package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DataBaseMethodHandler;
import dao.DataBaseMethodHandlerImpl;
import pojo.SocketCommunicationConfirmData;
import startcontrol.SocketClient;


public class HandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int viewCount = 0;
   	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String id = request.getParameter("id");
		viewCount++;
		System.out.println(" -----------------------------------viewCount-----------------------------------     "+viewCount);
		System.out.println("id1 00000000 ");
		response.getWriter().write("hello SocketServer !");
		response.getWriter().flush();
		final DataBaseMethodHandler handler = new DataBaseMethodHandlerImpl();
		handler.initDBEnvironment();
		//todo
                System.out.println("id1 = "+id);
                System.out.println("id1 = "+id);
		if(id !=null &&!"".equals(id)){
			//todo
	                System.out.println("id2 = "+id);
			final int dataId = Integer.parseInt(id);
			String updateSql = "update tb_socketcommunicationconfirm set communication_status='1' where id="+id;
			handler.updateSocketComConData(updateSql);
			new Thread(){
				@Override
				public void run() {
					
					SocketCommunicationConfirmData data = null;
					try {
						
						while(true){
								Thread.sleep(1000);
								data =handler.querySCCdata(dataId);
								String status = data.getCommunication_status();
								if(status !=null || "2".equals(status)){
									break;
								}
						}
						handler.updateSocketComConData("update tb_socketcommunicationconfirm set start_status='1' where id="+id);
						handler.closeAll();
						Thread.sleep(2000);
						//SocketClient.main(null);
						SocketClient client = new SocketClient();
						client.start();
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}.start();
			
		}
		
   	}

}
