package com.javaex.vo;

public class GuestbookVo {

	private int no;
	private String name, password, content, reg_date;	
	
	public GuestbookVo() {
	}
	
	public GuestbookVo(int number, String name, String password, String content, String reg_date) {
		this.no = number;
		this.name = name;
		this.password = password;
		this.content = content;
		this.reg_date = reg_date;
	}
	

	public int getNumber() {
		return no;
	}


	public void setNumber(int number) {
		this.no = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "GuestbookVo [number=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}

	
}