package com.hanqian.action;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.hanqian.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 描 述: 实时监控 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-13
 * @see
 */
public class controlConfigAction extends ActionSupport {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(controlConfigAction.class);
	private static final long serialVersionUID = 2029318058729777012L;
	private HttpServletRequest request;
	private String resultJSON;

	public void updateConfigXml() {
		try {
			request = ServletActionContext.getRequest();
			String condition = request.getParameter("condition");
			String condVal = request.getParameter("rationalval");
			String criterionval = request.getParameter("criterionval");
			String startTime = request.getParameter("energyStartTime");
			String endTime = request.getParameter("energyEndTime");
			String path = Thread.currentThread().getContextClassLoader()
					.getResource("").getPath().toString();
			logg.debug("    -----path----:      "+path);
			File temp = new File(path);
			String basePath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
			// 创建一个存放文件文件夹
			temp = new File(
					basePath
							+ "manager/monitoring/control/electricity/config/targetConfig.xml");
			if (!temp.exists()) {
				// 如果文件不存在
				temp.createNewFile();
			} else {
				// 文件存在，修改数值
				XMLWriter writer = null;// 声明写XML的对象
				SAXReader reader = new SAXReader();
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("GBK");
				Document document = reader.read(temp);// 读取XML文件
				Element root = document.getRootElement();// 得到根节点
				for (Iterator i = root.elementIterator(condition); i.hasNext();) {
					Element node = (Element) i.next();
					node.selectSingleNode("rationalval").setText(condVal);
					node.selectSingleNode("criterionval").setText(criterionval);
					node.selectSingleNode("startTime").setText(startTime);
					node.selectSingleNode("endTime").setText(endTime);
					break;
				}
				writer = new XMLWriter(new FileWriter(temp), format);
				writer.write(document);
				writer.close();
			}
			resultJSON = "[{'msg':'yes'}]";
		} catch (Exception e) {
			logg.error("controlConfigAction-->updateConfigXml", e);
			e.printStackTrace();
			resultJSON = "[{'msg':'no'}]";
		} finally {
			try {
				ServletActionContext.getResponse().getWriter()
						.print(resultJSON);
			} catch (Exception e) {
				logg.error("controlConfigAction-->updateConfigXml", e);
				// TODO: handle exception
			}
		}
	}

	//修改xml信息
	public void updateTargetConfigXml() {
		try {
			request = ServletActionContext.getRequest();
			String energyYears = request.getParameter("energyYears");
			
			String valJiange = request.getParameter("valJiange");
			String valUnit = request.getParameter("valUnit");

			String criterionval = request.getParameter("criterionval");
			String safetyStartTime = request.getParameter("safetyStartTime");
			String safetyEndTime = request.getParameter("safetyEndTime");

			String safetyHisStartTime = request.getParameter("safetyHisStartTime");
			String safetyHisEndTime = request.getParameter("safetyHisEndTime");
			
			//String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
			String path = request.getSession().getServletContext().getRealPath("/manager/monitoring/control/electricity/config/");
			File temp = new File(path);
			//String basePath = temp.getParentFile().getParentFile().getPath(); // 项目根目录
			// 创建一个存放文件文件夹
			temp = new File(path+File.separator+ "targetConfig.xml");
			logg.debug("--------------参数设置的路径-------------:"+temp);
			if (!temp.exists()) {
				// 如果文件不存在
				temp.createNewFile();
				// 文件存在，修改数值
				XMLWriter writer = null;// 声明写XML的对象
				SAXReader reader = new SAXReader();
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				Document document = reader.read(temp);// 读取XML文件
				Element root = document.getRootElement();// 得到根节点
				// 写入综合节能指
				Element energy = (Element) root.elementIterator("energysave").next();
				// 判断是否为空
				if (panduan(energyYears)) {
					energy.selectSingleNode("statTime").setText(energyYears);
				}
				// 写入变压器电流峰值
				Element transformer = (Element) root.elementIterator(
						"transformer").next();
				// 判断是否为空
				if (panduan(valJiange)&& panduan(valUnit)) {
					transformer.selectSingleNode("quantum").setText(
							valJiange + "," + valUnit);
				}
				// 写入实时安全
				Element safesave = (Element) root.elementIterator("safesave")
						.next();
				// 判断是否为空
				if (panduan(criterionval)) {
					safesave.selectSingleNode("criterionval").setText(
							criterionval);
				}
				// 判断是否为空
				if (panduan(safetyStartTime)) {
					safesave.selectSingleNode("startTime").setText(
							safetyStartTime);
				}
				// 判断是否为空
				if (panduan(safetyEndTime)) {
					safesave.selectSingleNode("endTime").setText(safetyEndTime);
				}

				// 写入历史--------------安全
				Element history = (Element) root.elementIterator("history")
						.next();
				// 判断是否为空
				if (panduan(safetyHisStartTime)) {
					history.selectSingleNode("startTime").setText(
							safetyHisStartTime);
				}
				// 判断是否为空
				if (panduan(safetyHisEndTime)) {
					history.selectSingleNode("endTime").setText(
							safetyHisEndTime);
				}

				writer = new XMLWriter(new FileWriter(temp), format);
				writer.write(document);
				writer.close();
			} else {
				// 文件存在，修改数值
				XMLWriter writer = null;// 声明写XML的对象
				SAXReader reader = new SAXReader();
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				Document document = reader.read(temp);// 读取XML文件
				Element root = document.getRootElement();// 得到根节点
				// 写入综合节能指
				Element energy = (Element) root.elementIterator("energysave").next();
				// 判断是否为空
				if (panduan(energyYears)) {
					energy.selectSingleNode("statTime").setText(energyYears);
				}
				// 写入变压器电流峰值
				Element transformer = (Element) root.elementIterator(
						"transformer").next();
				// 判断是否为空
				if (panduan(valJiange)&& panduan(valUnit)) {
					transformer.selectSingleNode("quantum").setText(
							valJiange + "," + valUnit);
				}
				// 写入实时安全
				Element safesave = (Element) root.elementIterator("safesave")
						.next();
				// 判断是否为空
				if (panduan(criterionval)) {
					safesave.selectSingleNode("criterionval").setText(
							criterionval);
				}
				// 判断是否为空
				if (panduan(safetyStartTime)) {
					safesave.selectSingleNode("startTime").setText(
							safetyStartTime);
				}
				// 判断是否为空
				if (panduan(safetyEndTime)) {
					safesave.selectSingleNode("endTime").setText(safetyEndTime);
				}

				// 写入历史--------------安全
				Element history = (Element) root.elementIterator("history")
						.next();
				// 判断是否为空
				if (panduan(safetyHisStartTime)) {
					history.selectSingleNode("startTime").setText(
							safetyHisStartTime);
				}
				// 判断是否为空
				if (panduan(safetyHisEndTime)) {
					history.selectSingleNode("endTime").setText(
							safetyHisEndTime);
				}

				writer = new XMLWriter(new FileWriter(temp), format);
				writer.write(document);
				writer.close();
			}
			resultJSON = "[{'msg':'yes'}]";
		} catch (Exception e) {
			logg.error("controlConfigAction-->updateTargetConfigXml", e);
			e.printStackTrace();
			resultJSON = "[{'msg':'no'}]";
		} finally {
			try {
				ServletActionContext.getResponse().getWriter()
						.print(resultJSON);
			} catch (Exception e) {
				logg.error("controlConfigAction-->updateTargetConfigXml", e);
				// TODO: handle exception
			}
		}
	}

	public boolean panduan(String str) {
		if (!SysUtil.isNull(str) && !"".equals(str) && !"null".equals(str)
				&& !"undefined".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	public String getResultJSON() {
		return resultJSON;
	}

	public void setResultJSON(String resultJSON) {
		this.resultJSON = resultJSON;
	}
}
