package board;

public class BoardVo {
	
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getWriterNo() {
		return writerNo;
	}
	public void setWriterNo(String writerNo) {
		this.writerNo = writerNo;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", enrollDate=" + enrollDate
				+ ", writerNo=" + writerNo + "]";
	}
	public BoardVo(String no, String title, String content, String enrollDate, String writerNo) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.enrollDate = enrollDate;
		this.writerNo = writerNo;
	}
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String no;
	private String title;
	private String content;
	private String enrollDate;
	private String writerNo;

}
