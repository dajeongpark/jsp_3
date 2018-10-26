package com.dajeong.page;

public class Pager {
	
	//페이징 처리 
	private int totalPage;
	private int totalBlock;
	private int curBlock;
	private int startNum;
	private int lastNum;
	private Search search;
	
	public int getTotlaBlock() {
		return totalBlock;
	}
	public void setTotlaBlock(int totlaBlock) {
		this.totalBlock = totlaBlock;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getLastNum() {
		return lastNum;
	}
	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
