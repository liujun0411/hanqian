package com.hanqian.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PaginatorTagMysql extends BodyTagSupport {

	/**
	 * 作 用：显示“上一页 下一页”等信息
	 * 
	 * @param url
	 *            ----链接地址
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
	private static final long serialVersionUID = 1L;
	

	protected Log log = LogFactory.getLog(this.getClass());	

	String url = "";
	
    boolean showTotal = true;

	boolean showAllPages = false;

	String strUnit = "";

	public boolean isShowAllPages() {

		return showAllPages;

	}

	public void setShowAllPages(boolean showAllPages) {

		this.showAllPages = showAllPages;

	}

	public boolean isShowTotal() {

		return showTotal;

	}

	public void setShowTotal(boolean showTotal) {

		this.showTotal = showTotal;

	}

	public String getStrUnit() {

		return strUnit;

	}

	public void setStrUnit(String strUnit) {

		this.strUnit = strUnit;

	}



	public String getUrl() {

		return url;

	}

	public void setUrl(String url) {

		this.url = url;

	}

	public int doStartTag() throws JspException {

		return SKIP_BODY;

	}

	public int doEndTag() throws JspException {
		
		
		try {
			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();
			PageMysql page = (PageMysql) request.getAttribute("pagemysql");
			Map map = request.getParameterMap();

			StringBuffer paraList = new StringBuffer("");
			if (map != null) {
				int mapsize = map.size();

				String key;
				String value;
				Object[] keyValuePairs2 = map.keySet().toArray();

				for (int i = 0; i < mapsize; i++) {
					key = keyValuePairs2[i].toString();

					value = request.getParameter(key);
					if ((!key.equals("page"))) 
					{   
						if (paraList.length()==0)
							paraList.append("?" + key + "=" + value);
						else 
						{	
							paraList.append("&" + key + "=" + value);
						}
					}
				}
			}
			
			String out = showPage(page.getCurrentPage(),url+paraList.toString(), page.gettotalCount(), page.getpageSize(),
					showTotal, showAllPages, strUnit);
			
			pageContext.getOut().print(out);

		} catch (IOException e) {

			e.printStackTrace();

		}

		return EVAL_PAGE;

	}

	protected String showPage(int currentPage, String url, int totalPut,
			int maxPerPage, boolean showTotal, boolean showAllPages,
			String strUnit) {
		int n = 0;

		StringBuffer buf = new StringBuffer();

		String strUrl;

		if (totalPut % maxPerPage == 0)

			n = totalPut / maxPerPage;

		else

			n = totalPut / maxPerPage + 1;

		buf.append("<table align='center'><tr><td class='biaoge_fanye'>");

		if (showTotal == true)

			buf.append("共 <b>" + totalPut + "</b> " + strUnit

			+ "&nbsp;");

		strUrl = JoinChar(url);

		if (currentPage < 2) {

			buf.append("首页 上一页 ");

		} else {

			buf.append("<a href='" + strUrl
							+ "page=1' title='首页'>首页</a>&nbsp;");

			buf.append("<a href='" + strUrl + "page=" + (currentPage - 1)

			+ "' title='上一页'>上一页</a>&nbsp;");

		}

		if (n - currentPage < 1)

			buf.append(" 下一页 尾页 ");

		else {

			buf.append("<a href='" + strUrl + "page=" + (currentPage + 1)

			+ "' title='下一页'>下一页</a>&nbsp;");

			buf.append("<a href='" + strUrl + "page=" + n
					+ "' title='尾页'>尾页</a>");
		}
		buf.append("&nbsp;页次:<strong><font color=red>" + currentPage

		+ "</font>/" + n + "</strong>页 ");

		buf.append("&nbsp;<b>" + maxPerPage + "</b>" + strUnit + "/页");

		if (showAllPages == true) {
			buf.append("&nbsp;转到:<select name='page' size='1' onchange=\"javascript:window.location='"

							+ strUrl

							+ "page="

							+ "'+this.options[this.selectedIndex].value;\">");

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

	/**
	 * 向地址中加入 ? 或 &
	 * 
	 * @param strUrl
	 *            ----网址.
	 * @return 加了 ? 或 & 的网址.
	 */

	protected String JoinChar(String strUrl) {

		String result = "";

		if (strUrl.equals("") || strUrl.length() <= 0) {

			return result;

		}

		if (strUrl.indexOf("?") < strUrl.length()) {

			if (strUrl.indexOf("?") > -1) {

				if (strUrl.indexOf("&") < strUrl.length()) {

					result = strUrl + "&";

				} else {

					result = strUrl;

				}
			} else {

				result = strUrl + "?";
			}

		} else {
			result = strUrl;
		}

		return result;

	}
}
