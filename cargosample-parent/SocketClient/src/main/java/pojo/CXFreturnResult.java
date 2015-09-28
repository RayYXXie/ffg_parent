package pojo;

import java.io.Serializable;

/**
 * ���� CXF���صĽ��
 * 
 * @author zhaorong.liang
 * 
 */
@SuppressWarnings("serial")
public class CXFreturnResult implements Serializable {
	
	// ���׷�����
	private String resCode;
	// ���׷�����Ϣ
	private String returnMsg;
	// case ID
	private String dataId;
	// case Type
	private String msgType;
	// �˻� ID
	private String account;
	// ����ʱ��
	private String transactTime;
	// ��˾ID
	private String onBehalfOfCompID;
	// �ԱȽ������ֵ
	private String expectedValue;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

	public String getOnBehalfOfCompID() {
		return onBehalfOfCompID;
	}

	public void setOnBehalfOfCompID(String onBehalfOfCompID) {
		this.onBehalfOfCompID = onBehalfOfCompID;
	}

}
