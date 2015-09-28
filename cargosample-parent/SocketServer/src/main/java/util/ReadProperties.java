package util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	// �ļ�·����
	public String filePath = "";

	// �����ļ�·����
	public void setPropertiesFilePath(String fPath) {
		this.filePath = fPath;
	}

	// ��ȡ
	public String getKeyValue(String str) {
		Properties pro = new Properties();
		BufferedInputStream in = null;
		try {
 			//in = new BufferedInputStream(new FileInputStream(this.filePath));
			in = new BufferedInputStream(ReadProperties.class.getClassLoader().getResourceAsStream(this.filePath));
			pro.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
//				try {
//					in.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}

		}
		String valueStr = pro.getProperty(str);
		if(valueStr == null){
			valueStr = pro.getProperty("A");
		}
		return valueStr;

	}

	public static void main(String[] args) {
		ReadProperties pro = new ReadProperties();
		pro.setPropertiesFilePath("./src/util/CaseTypeHttp.properties");
		String str = pro.getKeyValue("A");
		System.out.println(str);

	}

}
