package pojo;
/**
 * �������� POJO
 * @author zhaorong.liang
 *
 */
public class ExpectedDataObject {
	// ����
	private int id;
	// case ID��
	private String dataId;
	// �˻�
	private String account;
	// case ����
	private String msgType;
	// ����ʱ��
	private String transactTime;
	// ��˾ id
	private String onbehalfOfCompId;
	// ���ݸĶ�ʱ��
	private String modifyTime;
	// �Ա�����ֵ
	private float expectedValue;
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
	public float getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(float expectedValue) {
		this.expectedValue = expectedValue;
	}

	

}
