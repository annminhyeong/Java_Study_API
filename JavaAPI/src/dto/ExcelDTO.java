package dto;

public class ExcelDTO {
	private String title; //책 제목
	private String author; //저자
	private String company; //출판사
	private String isbn; //도서번호
	private String imgurl; //책 이미지 URL
	
	public ExcelDTO() {     }

	
	
	@Override
	public String toString() {
		return "ExcelDTO [title=" + title + ", author=" + author + ", company=" + company + ", isbn=" + isbn
				+ ", imgurl=" + imgurl + "]";
	}
	
	public ExcelDTO(String title, String author, String company, String isbn, String imgurl) {
		super();
		this.title = title;
		this.author = author;
		this.company = company;
		this.isbn = isbn;
		this.imgurl = imgurl;
	}

	public ExcelDTO(String title, String author, String company) {
		super();
		this.title = title;
		this.author = author;
		this.company = company;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	
	
	
}
