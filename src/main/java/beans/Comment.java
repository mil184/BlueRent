package beans;

import dao.UserDAO;

public class Comment {
	private int id;
	private String companyName;
	private int userId;
	private double grade;
	private String text;
	
	public Comment() {}
	
	public Comment(int id, String companyName, int userId, double grade, String text) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.userId = userId;
		this.grade = grade;
		this.text = text;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
