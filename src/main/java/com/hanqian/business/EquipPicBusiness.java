package com.hanqian.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.DbEquipPic;
import com.hanqian.pojo.DbEquipPicRel;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2010 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author jkj
 * @version 1.4 2012-9-4
 * @see
 */
@Service
public class EquipPicBusiness extends BaseBusiness {
	/**
	 * log4g日志
	 */
	private static final Logger logg = Logger.getLogger(EquipPicBusiness.class);

	// private static final Log logg =
	// LogFactory.getLog(EquipPicBusiness.class);

	// @Resource
	// private EquipPicMgr equipPicMgr;
	//
	//
	// public EquipPicMgr getEquipPicMgr() {
	// return equipPicMgr;
	// }
	//
	// public void setEquipPicMgr(EquipPicMgr equipPicMgr) {
	// this.equipPicMgr = equipPicMgr;
	// }

	/**
	 * 分页显示所有设备图纸
	 * 
	 * @param dbEquipPic
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public RetCode findDbEquipPic(DbEquipPicRel dbEquipPicRel, int currentPage,
			int pageSize) {
		// return equipPicMgr.findDbEquipPic(dbEquipPicRel, currentPage,
		// pageSize);
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipPicBusiness.findDbEquipPic,对象dbEquipPicRel:"
					+ dbEquipPicRel);
		}
		Map map = new HashMap();
		map.put("equipId", dbEquipPicRel.getDbEquipList().getEquipId());
		return this.getPageData("findDbEquipPic", map, currentPage, pageSize);
	}

	public Map getFindDbEquipPicSql() {
		return PerformSQLUtil.get("findDbEquipPic", this);
	}

	/**
	 * 根据设备图纸编号删除图纸信息
	 * 
	 * @param equipPicId
	 * @return
	 */
	public boolean deleteEquipPic(DbEquipPicRel dbEquipPicRel) {
		// return equipPicMgr.deleteEquipPic(dbEquipPicRel);
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipPicBusiness.deleteEquipPic,对象dbEquipPicRel:"
					+ dbEquipPicRel);
		}
		boolean bool = true;
		try {
			sqlSession.delete("deleteEquipPic", dbEquipPicRel);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getDeleteEquipPicSql() {
		return PerformSQLUtil.get("deleteEquipPic", this);
	}

	/**
	 * 删除图纸信息
	 * 
	 * @param equipPic
	 * @return
	 */
	public boolean deletePic(DbEquipPic equipPic) {
		// return equipPicMgr.deletePic(equipPic);
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipPicBusiness.deletePic,对象DbEquipPic:" + equipPic);
		}
		boolean bool = true;
		try {
			sqlSession.delete("deletePic", equipPic);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getDeletePicSql() {
		return PerformSQLUtil.get("deletePic", this);
	}

	/**
	 * 获得所有的设备图纸类型
	 * 
	 * @return
	 */
	public RetCode findALLEquipPicType() {
		// return equipPicMgr.findALLEquipPicType();
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipPicBusiness.findALLEquipPicType");
		}
		return this.getPageData("findALLEquipPicType", null);
	}

	public Map getFindALLEquipPicTypeSql() {
		return PerformSQLUtil.get("findALLEquipPicType", this);
	}

	public DbEquipPic findById(Integer picId) {
		// return equipPicMgr.findById(picId);
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipPicBusiness.findById,参数picId:" + picId);
		}
		return sqlSession.selectOne("findById", picId);
	}

	public Map getFindByIdSql() {
		return PerformSQLUtil.get("findById", this);
	}

	/**
	 * 添加设备图纸
	 * 
	 * @param statement
	 * @param imgFile
	 *            图片FIle ，imgFileName 图片Name,
	 * @param picName
	 *            , 数据库中 放的一个辨识的名称
	 * @return
	 */

	public boolean insertBuildingPic(File[] imgFile, String[] picCode,
			String[] imgFileName, String[] picName,
			DbEquipPicRel dbEquipPicRel, HttpServletRequest request) {
		if (logg.isDebugEnabled()) {
			logg.debug("进入EquipPicBusiness.insertBuildingPic,对象DbEquipPicRel:"
					+ dbEquipPicRel);
		}
		boolean check = true;
		try {
			// 获取工程所在目录路径
			String url = new File(System.getProperty("user.dir"))
					.getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"EquipPic";

			for (int i = 0; null != imgFile && null != imgFileName ? i < imgFile.length
					: false; i++) {
				if (imgFile[i] == null || imgFileName[i] == null) {
					break;
				}
				DbEquipPicRel dbEquipPicR = new DbEquipPicRel();
				DbEquipPic dbEquipPic = new DbEquipPic();
				
				Map equipPicmap = new HashMap();
				equipPicmap.put("equipPicName", picName[i]);
				// 获得当前上传文件的后缀名
				String fileType = imgFileName[i].substring(imgFileName[i]
						.lastIndexOf("."));
				// 获得UUID，用来防止文件名重复
				String uuid = java.util.UUID.randomUUID().toString();
				equipPicmap.put("picAddress", uuid + fileType);
				equipPicmap.put("picCode", picCode[i]);
				equipPicmap.put("picTypeId", dbEquipPicRel.getDbEquipPic()
						.getDbEquipPicType().getPicTypeId());
				equipPicmap.put("buildId", dbEquipPicRel.getDbEquipPic()
						.getBuildId());
				equipPicmap.put("storey", dbEquipPicRel.getDbEquipPic().getStorey());
				equipPicmap.put("status", 0);
				equipPicmap.put("oper", dbEquipPicRel.getDbEquipPic()
						.getDbUsers().getSeq());
				equipPicmap.put("opertime", dbEquipPicRel.getDbEquipPic()
						.getOpertime());
				
				
				
				
//				dbEquipPic.setEquipPicName(picName[i]);
//				// 获得当前上传文件的后缀名
//				String fileType = imgFileName[i].substring(imgFileName[i]
//						.lastIndexOf("."));
//				// 获得UUID，用来防止文件名重复
//				String uuid = java.util.UUID.randomUUID().toString();
//				// 设备文件名
//				dbEquipPic.setPicAddress(uuid + fileType);
//				dbEquipPic.setStatus(Short.valueOf("0"));
//				dbEquipPic.setDbEquipPicType(dbEquipPicRel.getDbEquipPic()
//						.getDbEquipPicType());
//				dbEquipPic.setBuildId(dbEquipPicRel.getDbEquipPic()
//						.getBuildId());
//				dbEquipPic.setStorey(dbEquipPicRel.getDbEquipPic().getStorey());
//				dbEquipPic.setDbUsers(dbEquipPicRel.getDbEquipPic()
//						.getDbUsers());
//				dbEquipPic.setOpertime(dbEquipPicRel.getDbEquipPic()
//						.getOpertime());
//				dbEquipPic.setPicCode(picCode[i]);
				sqlSession.insert("insertDbEquipPic", equipPicmap);
				
				Map dbEquipPicRMap = new HashMap();
				dbEquipPicRMap.put("equipId", dbEquipPicRel.getDbEquipList().getEquipId());
//				dbEquipPicRMap.put("picId", dbEquipPic.getEquipPicId());
				dbEquipPicRMap.put("picId", equipPicmap.get("equipPicId"));
				
//				dbEquipPicR.setDbEquipList(dbEquipPicRel.getDbEquipList());
//				dbEquipPicR.setDbEquipPic(dbEquipPic);
				boolean picRel = true;
				try {
					sqlSession.insert("insertDbEquipPicREL", dbEquipPicRMap);
				} catch (Exception e) {
					picRel = false;
				}

				// 如果添加成功
				if (dbEquipPicR != null && picRel)
					try {
						this.copyImgFile(imgFile[i], url, uuid + fileType); // 上传图片
					} catch (Exception e) {
						logg.error("EquipPicBusiness-->insertBuildingPic", e);
						e.printStackTrace();
					}
			}
		} catch (Exception e1) {
			logg.error("EquipPicBusiness-->insertBuildingPic", e1);
			e1.printStackTrace();
		}
		return check;
	}

	public Map getInsertBuildingPicSql() {
		return PerformSQLUtil.get("insertDbEquipPic", this);
	}

	/**
	 * 将File 通过io写入指定url‘
	 * 
	 * @param statement
	 * @param imgFile
	 *            文件，imgUrl 写入的地址
	 * @return
	 */
	public boolean copyImgFile(File imgFile, String imgUrl, String imgFileName)
			throws IOException {
		File f1 = new File(imgUrl);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		f1 = new File(imgUrl + File.separator + imgFileName);
		OutputStream os = new FileOutputStream(f1); // 写
		InputStream is = new FileInputStream(imgFile); // 读
		BufferedInputStream bis = new BufferedInputStream(is);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		byte by[] = new byte[(int) (imgFile.length())];
		while (bis.read(by) != -1) {
			bos.write(by);
		}
		bos.flush();
		bos.close();
		bis.close();
		is.close();
		bos.close();
		return true;

	}

}
