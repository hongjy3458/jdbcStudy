package member;

public class MemberVo {
	
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getQuitYn() {
		return quitYn;
	}
	public void setQuitYn(String quitYn) {
		this.quitYn = quitYn;
	}
	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", id=" + id + ", pwd=" + pwd + ", nick=" + nick + ", enrollDate=" + enrollDate
				+ ", modifyDate=" + modifyDate + ", quitYn=" + quitYn + "]";
	}
	public MemberVo(String no, String id, String pwd, String nick, String enrollDate, String modifyDate,
			String quitYn) {
		super();
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.nick = nick;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.quitYn = quitYn;
	}
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String no;
	private String id;
	private String pwd;
	private String nick;
	private String enrollDate;
	private String modifyDate;
	private String quitYn;

}
