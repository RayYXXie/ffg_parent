package pojo;

import java.io.Serializable;

/**
 * ԭ������ pojo
 * 
 * @author zhaorong.liang
 * 
 */
@SuppressWarnings("serial")
public class InitialDataObject implements Serializable{
	
	// ����
	private int id;
	// case ID
	private String dataId;
	// �˻�
	private String account;
	// case ����
	private String msgType;
	// ����ʱ��
	private String transactTime;
	// ��˾ID
	private String onbehalfOfCompId;
	// �޸�ʱ��
	private String modifyTime;
	// ����CXF�ķ�������ַ
	private String cxfUrl;

	public String getCxfUrl() {
		return cxfUrl;
	}

	public void setCxfUrl(String cxfUrl) {
		this.cxfUrl = cxfUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getOnbehalfOfCompId() {
		return onbehalfOfCompId;
	}

	public void setOnbehalfOfCompId(String onbehalfOfCompId) {
		this.onbehalfOfCompId = onbehalfOfCompId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
