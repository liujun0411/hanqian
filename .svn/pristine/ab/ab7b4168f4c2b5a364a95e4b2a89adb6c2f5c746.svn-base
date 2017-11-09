package com.hanqian.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanqian.util.DateUtil;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author cc
 * @version 1.4 2014-10-21
 * @see
 */
@Service
public class DbinfoBuiness extends BaseBusiness {
	private static final Logger log = Logger.getLogger(DbinfoBuiness.class);

/*	@Autowired
	private DataSource dataSource;
*/
	/**
	 * 查询信息
	 * 
	 * @param 2014-10-21
	 * @param @return
	 * @return List
	 */
	public List findinfo_all() {
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.findinfo_all!");

		RetCode retCode = this.getData("findinfoAllDbControlData", null);
		Object obj = retCode.getObj();
		List list = (List) obj;
		for (int i = 0; i < list.size(); i++) {

			Map map = (Map) list.get(i);
			dateToString(map);
			try {
				ConvertBLOBtoString(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Map getFindinfo_allSql() {
		return PerformSQLUtil.get("findinfoAllDbControlData", this);
	}

	public List find_infobynum(Map sqlmap) {

		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.find_infobynum!参数Map:" + sqlmap);

		Map parammap = new HashMap();
		parammap.put("userid", sqlmap.get("userid"));
		parammap.put("min", sqlmap.get("min"));
		parammap.put("max", sqlmap.get("max"));
		RetCode retCode = this.getData("findInfobynumDbControlData", parammap);
		Object obj = retCode.getObj();
		List list = (List) obj;
		if (list == null) {

			Log.info("数据为null  ");
		} else {
			for (int i = 0; i < list.size(); i++) {

				Map map = (Map) list.get(i);
				dateToString(map);
				try {

					ConvertBLOBtoString(map);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public Map getFind_infobynumSql() {
		return PerformSQLUtil.get("findInfobynumDbControlData", this);
	}

	/**
	 * 大字段转成字符串
	 * 
	 * @param 2014-10-21
	 * @param @param map
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 */
	public static String ConvertBLOBtoString(Map map) throws Exception {

		oracle.sql.CLOB clob = (oracle.sql.CLOB) map.get("content");
		if (clob == null) {

			return "";

		} else {

			BufferedReader in = new BufferedReader(clob.getCharacterStream());
			StringWriter out = new StringWriter();
			int c;
			while ((c = in.read()) != -1) {

				out.write(c);
			}

			String content = out.toString();
			map.put("content", content);
			return content;
		}
	}

	/**
	 * 日期转成字符串
	 * 
	 * @param 2014-10-21
	 * @param @param map
	 * @param @return
	 * @return String
	 */
	public static String dateToString(Map map) {
		Object objtime = map.get("otime");
		Date dat = (Date) objtime;

		String formatDate = DateUtil.dateToString(dat,
				DateUtil.SHORT_TIME_FORMAT);
		map.put("otime", formatDate);

		return formatDate;
	}

	/**
	 * 根据id查询具体的信息记录
	 * 
	 * @param 2014-10-21
	 * @param @param mid
	 * @return void
	 */
	public Map findinfo_byid(String mid) {
		if (log.isDebugEnabled())
			log.debug("进入DbinfoBusiness.findinfo_byid!参数mid:" + mid);
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.findinfo_byid!参数mid:" + mid);
		Map parammap = new HashMap();
		parammap.put("mid", mid);

		RetCode retCode = this.getData("findinfoByidDbControlData", parammap);

		List list = (List) retCode.getObj();
		Map map = (Map) list.get(0);

		dateToString(map);
		try {

			ConvertBLOBtoString(map);
		} catch (Exception e) {

			e.printStackTrace();
			Log.info("大字段转字符串");
		}
		return map;
	}

	public Map getFindinfo_byidSql() {
		return PerformSQLUtil.get("findinfoByidDbControlData", this);
	}

	/**
	 * 查询最大的id
	 * 
	 * @param 2014-10-22
	 * @param @return
	 * @return int
	 */
	public int finddbmessageMaxId() {
		if (log.isDebugEnabled())
			log.debug("进入DbinfoBusiness.finddbmessageMaxId!");
		if (log.isDebugEnabled())
			log.debug("进入DeptBusiness.finddbmessageMaxId!");

		RetCode ret = this.getData("finddbmessageMaxId", null);

		List list = (List) ret.getObj();
		Map map = (Map) list.get(0);
		Object obj = map.get("mtid");
		int maxmid = Integer.parseInt(StringUtil.toString(obj));

		return maxmid + 1;
	}

	public Map getFinddbmessageMaxIdSql() {
		return PerformSQLUtil.get("finddbmessageMaxId", this);
	}

	/**
	 * 添加发布信息 1、flag 标志 0 写的信息，代表发送 1收到的信息 代表接收的。 2
	 * 
	 * @param 2014-10-22
	 * @param @param dbinfo
	 * @return void
	 * @throws SQLException
	 * @throws SQLException
	 */
	public void inserInfoByMap(Map dbinfo) throws SQLException {
		if (log.isDebugEnabled())
			log.debug("进入DbinfoBusiness.inserInfoByMap!参数dbinfo:" + dbinfo);
		String content = StringUtil.toString(dbinfo.get("content"));
		String trycontent = "EMPTY_CLOB()";
		String otime = StringUtil.toString(dbinfo.get("Otime"));
		String title = StringUtil.toString(dbinfo.get("title"));
		String cesi =(String) dbinfo.get("attach");

		String info_from = StringUtil.toString(dbinfo.get("info_from"));
		String from_name = StringUtil.toString(dbinfo.get("from_name"));
		String info_to = StringUtil.toString(dbinfo.get("info_to"));
		String to_name = StringUtil.toString(dbinfo.get("to_name"));
		int mid = Integer.valueOf(dbinfo.get("mid").toString());
		Map parametermap = new HashMap();
		parametermap.put("mid", mid);
		parametermap.put("info_from", info_from);
		parametermap.put("from_name", from_name);
		parametermap.put("info_to", info_to);
		parametermap.put("to_name", to_name);
		parametermap.put("title", title);
		parametermap.put("trycontent", content);
		parametermap.put("cesi", cesi);
		parametermap.put("otime", otime);
		sqlSession.insert("insertDbmessage", parametermap);
		Map clobMap = new HashMap();
		clobMap.put("content", content);
		clobMap.put("mid", dbinfo.get("mid").toString());
		//对CLOB进行处理
		sqlSession.update("updateDbmessageClobinserInfoByMap", clobMap);
/*		String updatesql = "select content from db_message where mid="
				+ dbinfo.get("mid") + " for update";
		Connection conn = this.sqlSession.getConnection(); //dataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery(updatesql);
		if (rs.next()) {
			oracle.sql.CLOB clob = null;
			clob = (oracle.sql.CLOB) rs.getClob("content");
			BufferedWriter out = new BufferedWriter(
					clob.getCharacterOutputStream());
			try {
				out.write(content.toString());
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
		}
		rs.close();
		conn.commit();
		conn.setAutoCommit(true);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		Object objid = dbinfo.get("userid");
		int userid = Integer.parseInt(StringUtil.toString(objid));
		// 添加是否已阅的 信息。
		Map parametermap6 = new HashMap();
		parametermap6.put("mid", mid);
		parametermap6.put("userid", userid);
		parametermap6.put("otime", otime);
		sqlSession.insert("insertReaded", parametermap6);

	}

	public Map getInserInfoByMapSql() {
		return PerformSQLUtil.get("insertReaded", this);
	}

/*	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/

	/**
	 * 查询总条数
	 * 
	 * @param 2014-10-23
	 * @param
	 * @return void
	 */

	public int findallpagenum() {
		if (log.isDebugEnabled())
			log.debug("进入DbinfoBusiness.findallpagenum!");

		RetCode rtcode = this.getData("findallpagenum", null);
		List list = (List) rtcode.getObj();
		Map map = (Map) list.get(0);

		Object objnum = map.get("pagenum");
		int pagenum = Integer.parseInt(StringUtil.toString(objnum));

		return pagenum;
	}

	public Map getFindallpagenumSql() {
		return PerformSQLUtil.get("findallpagenum", this);
	}

	/**
	 * 插入从总平台接收的信息
	 * 
	 * @param 2014-10-27
	 * @param @param map
	 * @return void
	 * @throws Exception
	 */

	public void insertInforeceipt(Map map) throws Exception {

		String content = StringUtil.toString(map.get("Content"));
		String trycontent = "EMPTY_CLOB()";
		String otime = StringUtil.toString(map.get("Otime"));
		String title = StringUtil.toString(map.get("Title"));
		String cesi = (String) map.get("Attach");

		Object objmid = map.get("mid");
		int mid = Integer.parseInt(StringUtil.toString(objmid));
		String info_from = StringUtil.toString(map.get("info_from"));
		String info_to = StringUtil.toString(map.get("info_to"));
		String toname = StringUtil.toString(map.get("to_name"));
		String fromname = StringUtil.toString(map.get("from_name"));

		String strdate = DateUtil.parseString(new Date());
		Map parametermap = new HashMap();
		parametermap.put("mid", mid);
		parametermap.put("info_from", info_from);
		parametermap.put("from_name", fromname);
		parametermap.put("info_to", info_to);
		parametermap.put("to_name", toname);
		parametermap.put("title", title);
		parametermap.put("trycontent", content);
		parametermap.put("cesi", cesi);
		parametermap.put("otime", otime);
		sqlSession.insert("insertDbmessage1", parametermap);
		Map clobMap = new HashMap();
		clobMap.put("content", content);
		clobMap.put("mid", map.get("mid").toString());
		//对CLOB进行处理
		sqlSession.update("updateDbmessageClob", clobMap);
		/*String updatesql = "select content from db_message where mid="
				+ map.get("mid") + " for update";
		Connection conn =this.sqlSession.getConnection();// dataSource.getConnection();
		ResultSet rs = conn.createStatement().executeQuery(updatesql);

		if (rs.next()) {

			oracle.sql.CLOB clob = null;
			clob = (oracle.sql.CLOB) rs.getClob("content");
			BufferedWriter out = new BufferedWriter(
					clob.getCharacterOutputStream());

			try {

				out.write(content.toString());
				out.flush();
				out.close();

			} catch (IOException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
		}
		rs.close();
		conn.commit();
		conn.setAutoCommit(true);

		try {
			conn.close();
		} catch (SQLException e) {
			Log.info("update club error");
			e.printStackTrace();
		}*/
		// 添加是否已阅的 信息。
//		Map parametermap1 = new HashMap();
//		parametermap1.put("userid", map.get("userid"));
//		parametermap1.put("mid", mid);
//		parametermap1.put("otime", otime);
//		sqlSession.insert("insertReaded", parametermap1);

	}

	public Map getInsertInforeceiptSql() {
		return PerformSQLUtil.get("insertReaded", this);
	}

	/**
	 * 修改信息的状态
	 * 
	 * @param 2014-10-28
	 * @param @param mid
	 * @return void
	 */

	public void updateInfoStatusByid(Map parmmap) {
		if (log.isDebugEnabled())
			log.debug("进入DbinfoBusiness.updateInfoStatusByid!参数Map:" + parmmap);
		Object objmid = parmmap.get("mid");
		int mid = Integer.parseInt(StringUtil.toString(objmid));

		Object objuserid = parmmap.get("userid");
		int userid = Integer.parseInt(StringUtil.toString(objuserid));

		Map parametermap1 = new HashMap();
		parametermap1.put("userid", userid);
		parametermap1.put("mid", mid);
		RetCode resultmap = this
				.getData("updateInfoStatusByid1", parametermap1);

		List list = (List) resultmap.getObj();
		if (list != null) {

			Map parametermap2 = new HashMap();
			parametermap2.put("userid", userid);
			parametermap2.put("mid", mid);
			sqlSession.update("updateInfoStatusByid2", parametermap2);

		} else {

			String odate = DateUtil.parseString(new Date());

			Map parametermap9 = new HashMap();
			parametermap9.put("userid", userid);
			parametermap9.put("mid", mid);
			parametermap9.put("otime", odate);

			try {
				int count = sqlSession.insert("insertReaded", parametermap9);
			} catch (Exception e) {

				log.info("insert exception");
			}
		}

	}

	public Map getUpdateInfoStatusByidSql() {
		return PerformSQLUtil.get("updateInfoStatusByid1", this);
	}

}