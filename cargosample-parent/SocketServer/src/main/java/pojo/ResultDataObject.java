package pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResultDataObject implements Serializable {

	// ����
	private int id;
	// case ID
	private String dataId;
	// �˻�ID
	private String account;
	// case ����
	private String msgType;
	// ����ʱ��
	private String transactTime;
	// ��˾ID
	private String onbehalfOfCompId;
	// ����ֵ
	private float expectedValue;
	// ʵ�ʽ��׽��
	private float actuallyValue;
	// �޸�(����)ʱ��
	private String modifyTime;
	// �ԱȽ�� 1 �Ա�ͨ�� 2 �ԱȲ�ͨ�� 3����
	private String result;

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

	public float getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(float expectedValue) {
		this.expectedValue = expectedValue;
	}

	public float getActuallyValue() {
		return actuallyValue;
	}

	public void setActuallyValue(float actuallyValue) {
		this.actuallyValue = actuallyValue;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
