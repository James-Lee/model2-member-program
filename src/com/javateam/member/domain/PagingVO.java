package com.javateam.member.domain;

public class PagingVO {
	
	// 현재 페이지
	private int curPage = 1;
	// 시작 페이지
	private int startPage = 1;
	// 마지막 페이지
	private int endPage = 1;
	// 페이지별 글수
	private int rowPerPage = 3;
	// 총 페이지
	private int totPage = 0;
	// 이전 페이지
	private int prePage = 0;
	// 다음 페이지
	private int nextPage = 0;
	
	public PagingVO() {}
	
	public PagingVO(int curPage, int rowPerPage) {
		this.curPage = curPage;
		this.rowPerPage = rowPerPage;
	}
	
	public PagingVO(int curPage, int startPage, int endPage, 
					int rowPerPage, int totPage, int prePage, 
					int nextPage) {
		this.curPage = curPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.rowPerPage = rowPerPage;
		this.totPage = totPage;
		this.prePage = prePage;
		this.nextPage = nextPage;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagingVo [curPage=" + curPage + ", startPage=" + startPage + ", endPage=" + endPage + ", rowPerPage="
				+ rowPerPage + ", totPage=" + totPage + ", prePage=" + prePage + ", nextPage=" + nextPage + "]";
	}

	/**
	 * @return the curPage
	 */
	public int getCurPage() {
		return curPage;
	}
	/**
	 * @param curPage the curPage to set
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	/**
	 * @return the startPage
	 */
	public int getStartPage() {
		return startPage;
	}
	/**
	 * @param startPage the startPage to set
	 */
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	/**
	 * @return the endPage
	 */
	public int getEndPage() {
		return endPage;
	}
	/**
	 * @param endPage the endPage to set
	 */
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	/**
	 * @return the rowPerPage
	 */
	public int getRowPerPage() {
		return rowPerPage;
	}
	/**
	 * @param rowPerPage the rowPerPage to set
	 */
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	/**
	 * @return the totPage
	 */
	public int getTotPage() {
		return totPage;
	}
	/**
	 * @param totPage the totPage to set
	 */
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	/**
	 * @return the prePage
	 */
	public int getPrePage() {
		return prePage;
	}
	/**
	 * @param prePage the prePage to set
	 */
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}
	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

}
