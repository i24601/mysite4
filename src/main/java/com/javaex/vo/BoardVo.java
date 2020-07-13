package com.javaex.vo;

public class BoardVo {
	private int no, hit, userNo, count, group_no, order_no, depth, deleteFlag;
	private String title, content, reg_date, name;

	public BoardVo() {
	}
	
	public BoardVo(int no, int hit, int userNo, int count, int group_no, int order_no, int depth, String title,
			String content, String reg_date, String name, int deleteFlag) {
		this.no = no;
		this.hit = hit;
		this.userNo = userNo;
		this.count = count;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.name = name;
		this.deleteFlag = deleteFlag;
	}

	
	


	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
		return "BoardVo [no=" + no + ", hit=" + hit + ", userNo=" + userNo + ", count=" + count + ", group_no="
				+ group_no + ", order_no=" + order_no + ", depth=" + depth + ", deleteFlag=" + deleteFlag + ", title="
				+ title + ", content=" + content + ", reg_date=" + reg_date + ", name=" + name + "]";
	}

	
	
	

	
	
	
	
	
	
	
}
