package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import pojo.CXFreturnResult;
import pojo.InitialDataObject;

public class HttpClientHandler4X {

	//static int count = 0;
	/**
	 * ��get��������ʽ��������
	 * 
	 * @param object
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public CXFreturnResult getHandler(InitialDataObject object) {
		
		// ����Ĭ�ϵ�httpClientʵ��.
		HttpClient httpclient = new DefaultHttpClient();
		CXFreturnResult result = null;
		
		try {
			
			
			String dataId = object.getDataId() == null ? "1" : object.getDataId();
			String url = object.getCxfUrl() + "/" + dataId+ "/df/df/20150808/dfdfdf";
			System.out.println(url);
			// ����httpget
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = null;
			try {
				// ִ��get����.
				//++count;
				//System.out.println("count = "+count);
				response = httpclient.execute(httpget);
				// ��ȡ��Ӧʵ��
				HttpEntity entity = response.getEntity();
				// ��ӡ��Ӧ״̬
				if (entity != null) {
					
					result = new CXFreturnResult();
					String str = EntityUtils.toString(entity);
					XMLDocumentUtil xmlUtil = new XMLDocumentUtil();
					xmlUtil.parseXML(str, result);
					
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} finally {
			// �ر�����,�ͷ���Դ
			httpclient.getConnectionManager().shutdown();
		}
		return result;

	}

	public CXFreturnResult getHandler2(InitialDataObject object) {
		
		CXFreturnResult result = new CXFreturnResult();
		result.setDataId("157");
		result.setAccount("fdf");
		result.setExpectedValue("1234");
		result.setMsgType("D");
		result.setOnBehalfOfCompID("df");
		result.setResCode("0");
		result.setReturnMsg("gaga");
		result.setTransactTime("20150202");
		int k = 0;
		for (int i = 0; i < 10000000; i++) {
			++k;
		}
		return result;

	}

	/**
	 * ��post�ķ�ʽ�������� 
	 * ����CXF��δģ�⣬
	 * ��ʱδ��ɵ���
	 * @param object
	 */
	public CXFreturnResult postHandler(InitialDataObject object) {
		
		
		// ����Ĭ�ϵ�httpClientʵ��.
		HttpClient httpclient = new DefaultHttpClient();
		
		// ����httppost
		HttpPost httppost = new HttpPost("http://192.168.4.61:8080/CXFWebDemo/cxf/user/getUserById");

		// �����������
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("ID", "123"));
		formparams.add(new BasicNameValuePair("Account", "987321"));
		formparams.add(new BasicNameValuePair("MsgType", "D"));
		formparams.add(new BasicNameValuePair("TransactTime",
				"2015-8-10 12:09:53"));
		formparams.add(new BasicNameValuePair("OnBehalfOfCompID", "HSBC"));

		UrlEncodedFormEntity uefEntity;
		CXFreturnResult result = null;
		try {

			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			HttpResponse response;
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {

				result = new CXFreturnResult();
				String str = EntityUtils.toString(entity);
				XMLDocumentUtil xmlUtil = new XMLDocumentUtil();
				xmlUtil.parseXML(str, result);
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			// �ر�����,�ͷ���Դ
			httpclient.getConnectionManager().shutdown();

		}
		return result;

	}

	public static void main(String args[]) throws ClientProtocolException,
			IOException {

		HttpClient httpclient = new DefaultHttpClient();
		try {
			// ����httpget.
			HttpGet httpget = new HttpGet(
					"http://192.168.4.61:8080/CXFWebDemo/cxf/item/"
							+ "getItemById/2/b/c/c/d");
			// ִ��get����.
			HttpResponse response = httpclient.execute(httpget);
			// ��ȡ��Ӧʵ��
			HttpEntity entity = response.getEntity();
			// ��ӡ��Ӧ״̬
			System.out.println(response.getStatusLine());
			CXFreturnResult obj = new CXFreturnResult();
			if (entity != null) {
				String str = EntityUtils.toString(entity);
				XMLDocumentUtil xmlUtil = new XMLDocumentUtil();
				xmlUtil.parseXML(str, obj);
			}

		} finally {
			// �ر�����,�ͷ���Դ
			httpclient.getConnectionManager().shutdown();
		}

	}

}
