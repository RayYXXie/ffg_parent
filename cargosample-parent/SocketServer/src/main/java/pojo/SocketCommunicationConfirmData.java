package pojo;

public class SocketCommunicationConfirmData {

	//socketͨѶȷ��data ID
	private int id;
	//����ʱ��
	private String create_time;
	//ͨѶ״̬
	private String communication_status;
	//�Ƿ�ɹ������ͻ���
	private String start_status;
	//�Ƿ�����ȫ��TestCase
	private String success_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCommunication_status() {
		return communication_status;
	}
	public void setCommunication_status(String communication_status) {
		this.communication_status = communication_status;
	}
	public String getStart_status() {
		return start_status;
	}
	public void setStart_status(String start_status) {
		this.start_status = start_status;
	}
	public String getSuccess_status() {
		return success_status;
	}
	public void setSuccess_status(String success_status) {
		this.success_status = success_status;
	}
	
}
