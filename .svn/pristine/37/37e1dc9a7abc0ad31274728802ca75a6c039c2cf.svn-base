package com.hanqian.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 *提交form表单的分页控件 
 * */
public class PageTagVFormMysql extends BodyTagSupport {

	private static final Log log = LogFactory.getLog(PageTagVFormMysql.class);

	/**
	 * 作 用：显示“上一页 下一页”等信息
	 * 
	 * @param totalPut
	 *            ----总数量
	 * @param maxPerPage
	 *            ----每页数量
	 * @param showTotal
	 *            ----是否显示总数量
	 * @param showAllPages
	 *            ---是否用下拉列表显示所有页面以供跳转。有某些页面不能使用，否则会出现JS错误。
	 * @param strUnit
	 *            ----计数单位
	 * @return .
	 * @throws IOException
	 * 
	 */

	private boolean showTotal = true;

	private boolean showAllPages = false;

	private String strUnit = "";

	public int doStartTag() throws JspException {

		return SKIP_BODY;

	}
	public int doEndTag() throws JspException {

		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			PageMysql page = (PageMysql) request.getAttribute("pagemysql");
			String out = "";
			if (page!=null) {
				out = showPage(page.getCurrentPage(), page.gettotalCount(),
						page.getpageSize(), showTotal, showAllPages, strUnit);
			}
			pageContext.getOut().print(out);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	protected String showPage(int currentPage, int totalPut, int maxPerPage,
			boolean showTotal, boolean showAllPages, String strUnit) {
		int n = 0;
		StringBuffer buf = new StringBuffer();
		// 计算总页数
		if (totalPut % maxPerPage == 0)
			n = totalPut / maxPerPage;
		else
			n = totalPut / maxPerPage + 1;
		// 开始打印表格
		buf.append("<table align='center'><tr><td class='biaoge_fanye'>");
		// 显示总记录数
		if (showTotal == true)
			buf.append("共 <b>" + totalPut + "</b> " + strUnit + "&nbsp;");

		// 显示分页信息
		if (currentPage < 2) {
			buf.append("首页 上一页 ");
		} else {
			buf
					.append("<a style='cursor:pointer' onclick=\"javascript:formCheck('1');\" title='首页'>首页</a>&nbsp;");

			buf
					.append("<a style='cursor:pointer' onclick=\"javascript:formCheck('"
							+ (currentPage - 1)
							+ "');\" title='上一页'>上一页</a>&nbsp;");
		}
		if (n - currentPage < 1)
			buf.append(" 下一页 尾页 ");
		else {
			buf
					.append("<a style='cursor:pointer' onclick=\"javascript:formCheck('"
							+ (currentPage + 1) + "');\" title='下一页'>下一页</a>&nbsp;");
			buf
					.append("<a style='cursor:pointer' onclick=\"javascript:formCheck('"
							+ n + "');\" title='尾页'>尾页</a>");
		}
		buf.append("&nbsp;页次:<strong><font color=red>" + currentPage
				+ "</font>/" + n + "</strong>页 ");

		buf.append("&nbsp;<b>" + maxPerPage + "</b>" + strUnit + "/页");

		if (showAllPages == true) {
			buf
					.append("&nbsp;转到:<select name='page' size='1' onchange=\"javascript:formCheck(this.options[this.selectedIndex].value);\">");

			for (int i = 1; i <= n; i++) {
				buf.append("<option value='" + i + "'");
				if (currentPage == i)
					buf.append(" selected ");
				buf.append(">第" + i + "页</option>");
			}
			buf.append("</select>");
		}
		buf.append("</td></tr></table>");
		return (buf.toString());
	}

	public boolean isShowTotal() {
		return showTotal;
	}

	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}

	public boolean isShowAllPages() {
		return showAllPages;
	}

	public void setShowAllPages(boolean showAllPages) {
		this.showAllPages = showAllPages;
	}

	public String getStrUnit() {
		return strUnit;
	}

	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
	}

}
