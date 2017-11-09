package com.hanqian.util;

public class PageMysql {

	private int totalPages; // 总页数
	private int pageSize = 5; // 每页条数
	private int totalCount; // 总条数
	private int currentPage = 1; // 当前页
	private int startIndex; // 从第几条开始取

	public PageMysql(int currentPage, int totalCount, int pageSize) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;

		if (0 == totalCount % pageSize)
			totalPages = totalCount / pageSize;
		else
			totalPages = totalCount / pageSize + 1;

		this.pageSize = pageSize;

		this.startIndex = (currentPage - 1) * pageSize;
	}

	/**
	 * @return Returns the currentPage.
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            The currentPage to set.
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return Returns the pageSize.
	 */
	public int getpageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            The pageSize to set.
	 */
	public void setpageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return Returns the totalCount.
	 */
	public int gettotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            The totalCount to set.
	 */
	public void settotalCount(int totalCount) {
		this.totalCount = totalCount;
		totalPages = (totalCount%pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;
	}

	/**
	 * @return Returns the startIndex.
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex
	 *            The startIndex to set.
	 */
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return Returns the totalPages.
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            The totalPages to set.
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	// 查询到下一页
	public int getNextPage() {
		if (currentPage >= totalPages)
			return totalPages;
		else if (currentPage < 1)
			return 1;
		else
			return ++currentPage;
	}

	// 查询到上一页
	public int getPreviousPage() {

		if (currentPage > totalPages)
			return totalPages;
		else if (currentPage <= 1)
			return 1;
		else
			return --currentPage;
	}
}