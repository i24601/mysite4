package com.javaex.vo;

public class BoardVo {
	private int no, hit, userNo, count;
	private String title, content, reg_date, name;

	public BoardVo() {
	}
	
	public BoardVo(int no, int hit, int userNo, String title, String content, String reg_date, String name, int count) {
		this.no = no;
		this.hit = hit;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.name = name;
		this.count = count;
	}



	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public int getHit() {
		return hit;
	}



	public void setHit(int hit) {
		this.hit = hit;
	}



	public int getUserNo() {
		return userNo;
	}



	public void setUser_no(int userNo) {
		this.userNo = userNo;
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



	public String getReg_date() {
		return reg_date;
	}



	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", hit=" + hit + ", useNo=" + userNo + ", count=" + count + ", title=" + title
				+ ", content=" + content + ", reg_date=" + reg_date + ", name=" + name + "]";
	}

	
	
	
	
	
	
	
}
