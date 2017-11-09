package com.hanqian.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.constant.ConfigCST;
import com.hanqian.pojo.DbBuildingPic;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.pojo.DbUsers;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

/**
 * 描 述: 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012 Company:
 * SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 张电男
 * @version 1.4 2012-9-6
 * @see
 */
@Service
public class BuildingPicBusiness extends BaseBusiness {
	// 日志
	private static final Logger log = Logger
			.getLogger(BuildingPicBusiness.class);

	/**
	 * 查询所有楼宇图纸类型
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public List findPicTypes() {
		// return this.buildingPicMgr.findPicTypes();
		if (log.isDebugEnabled())
			log.debug("进入BuildingPicBusiness.findPicTypes!");
		return sqlSession.selectList("findPicTypes");
	}

	public Map getFindPicTypesSql() {
		return PerformSQLUtil.get("findPicTypes", this);
	}

	/**
	 * 条件查询楼宇图片信息
	 * 
	 * @param statement
	 * @param page
	 *            分页
	 * @return list
	 */
	public RetCode findBuildingPic(DbBuildingPic buildingPic, int pageSize,
			int currentPage) {
		if (log.isDebugEnabled())
			log.debug("进入BuildingPicBusiness.findBuildingPic!对象DbBuildingPic:"
					+ buildingPic);
		Map map = new HashMap();
		if (null != buildingPic) {
			// 楼层
			if (null != buildingPic.getStorey() && 0 != buildingPic.getStorey()) {
				map.put("storey", buildingPic.getStorey());
			} else {
				map.put("storey", null);
			}
			// 图纸类型
			if (null != buildingPic.getPicType()
					&& null != buildingPic.getPicType().getSeq()) {
				map.put("picType", buildingPic.getPicType().getSeq());
			} else {
				map.put("picType", null);
			}
			// 图纸名称
			if (null != buildingPic.getPicName()
					&& !"".equals(buildingPic.getPicName())) {
				map.put("picName", buildingPic.getPicName());
			} else {
				map.put("picName", null);
			}
			// 楼宇
			if (null != buildingPic.getDbBuilding()
					&& null != buildingPic.getDbBuilding().getBuildingName()
					&& !"0".equals(buildingPic.getDbBuilding()
							.getBuildingName())) {
				map.put("buildingName", buildingPic.getDbBuilding()
						.getBuildingName());
			} else {
				map.put("buildingName", null);
			}
			if (null != buildingPic.getDbBuilding()
					&& null != buildingPic.getDbBuilding().getBuildingId()) {
				map.put("buildingId", buildingPic.getDbBuilding()
						.getBuildingId());
			} else {
				map.put("buildingId", null);
			}
		}

		return this.getPageData("findBuildingPic", map, currentPage, pageSize);

	}

	public Map getFindBuildingPicSql() {
		return PerformSQLUtil.get("findBuildingPic", this);
	}

	/**
	 * 添加楼宇图纸
	 * 
	 * @param statement
	 * @param imgFile
	 *            图片FIle ，imgFileName 图片Name,
	 * @param picName
	 *            , 数据库中 放的一个辨识的名称
	 * @return
	 */

	public String insertBuildingPic(File[] imgFile, String[] imgFileName,
			String[] picName, DbBuildingPic buildingPic,
			HttpServletRequest request) {
		if (log.isDebugEnabled())
			log.debug("进入BuildingPicBusiness.insertBuildingPic!对象DbBuildingPic:"
					+ buildingPic);
		try {
			boolean check = true;
//			String url = ConfigCST.CST_BUILDING_PIC_LOCATION;

			String url =new File(System.getProperty("user.dir")).getParentFile().toURI().toURL().getPath()+"Logistics_UploadPic/"+"BuildingPic"; // 获取工程所在目录路径
			log.debug("     BuildingPicURL:   "+url);
			for (int i = 0; null != imgFile && null != imgFileName ? i < imgFile.length
					: false; i++) {
				if (imgFile[i] == null || imgFileName[i] == null) {
					break;
				}
				Map bpmap = new HashMap();
				String tempImgFileName = imgFileName[i];
				UUID uuid = UUID.randomUUID();
				tempImgFileName = uuid.toString()
						+ imgFileName[i].substring(imgFileName[i]
								.lastIndexOf("."));// 中文名乱码而设置的一个随便uuid文件名称
			
				bpmap.put("buildingId", buildingPic.getDbBuilding().getBuildingId());
				bpmap.put("storey", buildingPic.getStorey());
				bpmap.put("picAddress", tempImgFileName);
				bpmap.put("picName", picName[i]);
				bpmap.put("status", 0);
				if (buildingPic.getDbUsersByOper() != null
						&& buildingPic.getDbUsersByOper().getSeq() != null) {
					bpmap.put("oper", buildingPic.getDbUsersByOper().getSeq());
				} else {
					bpmap.put("oper", 1);
				}
				
				bpmap.put("opertime", new Date());
				bpmap.put("picType", buildingPic.getPicType().getSeq());
				
			
				
//				DbBuildingPic bp = new DbBuildingPic();
//				DbHospInfo hospInfo = new DbHospInfo();
//				hospInfo.setSeqHosp(1);
//				bp.setStorey(buildingPic.getStorey()); // 设置楼层
//				bp.setDbBuilding(buildingPic.getDbBuilding()); // 楼宇
//				bp.setPicType(buildingPic.getPicType()); // 图纸类型
//				bp.setDbHospInfo(hospInfo);
				// 上传人
//				if (buildingPic.getDbUsersByOper() != null
//						&& buildingPic.getDbUsersByOper().getSeq() != null) {
//					bp.setDbUsersByOper(buildingPic.getDbUsersByOper());
//				} else {
//					DbUsers user = new DbUsers();
//					user.setSeq(1);
//					bp.setDbUsersByOper(user);
//				}
//				String tempImgFileName = imgFileName[i];
//				UUID uuid = UUID.randomUUID();
//				tempImgFileName = uuid.toString()
//						+ imgFileName[i].substring(imgFileName[i]
//								.lastIndexOf("."));// 中文名乱码而设置的一个随便uuid文件名称
//				bp.setPicAddress(tempImgFileName); // 图片存放地址
//				bp.setPicName(picName[i]); // 图片名称
//				bp.setOpertime(new Date()); // 添加时间
//				bp.setStatus((short) 0); // 设置数据状态
				// 添加

				// //如果添加成功
				// if(check)

				boolean bool = this.copyImgFile(imgFile[i], url, tempImgFileName); // 上传图片
				if (bool) {

					boolean bool1 = true;
					try {
						sqlSession.insert("insertBuildingPic", bpmap);
					} catch (Exception e) {
						bool1 = false;
					}
 					check = bool1;
					// check=buildingPicMgr.insertBuildingPic(bp);
				} else {
					if (StringUtil.isNotEmpty(tempImgFileName)
							&& StringUtil.trimNull(tempImgFileName).length() > 0) {
						File f2 = new File(url + tempImgFileName);
						f2.deleteOnExit();
					}
					// 图片超大
					return "图纸限制在6兆以内";
				}
			}
		} catch (Exception e) {
			log.error("BuildingPicBusiness-->insertBuildingPic", e);
			log.debug(e.getMessage());
			e.printStackTrace();
			return "操作失败";
		}

		return "操作成功";
	}

	public Map getInsertBuildingPicSql() {
		return PerformSQLUtil.get("insertBuildingPic", this);
	}

	/**
	 * 根据id查询楼宇图纸
	 * 
	 * @param statement
	 * @param
	 * @return
	 */
	public DbBuildingPic findPicById(Integer picId) {
		// return buildingPicMgr.findPicById(buildingPicId);
		if (log.isDebugEnabled())
			log.debug("进入BuildingPicBusiness.findPicById!参数buildingPicId:"
					+ picId);
		DbBuildingPic dbBuildingPic = sqlSession.selectOne("selectByPrimaryKey",
				picId);
		return dbBuildingPic;
	}

	public Map getFindPicByIdSql() {
		return PerformSQLUtil.get("selectByPrimaryKey", this);
	}

	/**
	 * 修改图纸信息
	 * 
	 * @param statement
	 * @param
	 * @return boolean
	 */
	public boolean updateBuildingPic(DbBuildingPic buildingPic) {

		// return buildingPicMgr.updateBuildingPic(buildingPic);
		if (log.isDebugEnabled())
			log.debug("进入BuildingPicBusiness.updateBuildingPic!对象DbBuildingPic:"
					+ buildingPic);
		boolean bool = true;
		try {
			sqlSession.update("updateBuildingPic", buildingPic);
		} catch (Exception e) {
			bool = false;
		}
		return bool;
	}

	public Map getUpdateBuildingPicSql() {
		return PerformSQLUtil.get("updateBuildingPic", this);
	}

	/**
	 * 将File 通过io写入指定url‘
	 * 
	 * @param statement
	 * @param imgFile
	 *            文件，imgUrl 写入的地址
	 * @return
	 */
	public boolean copyImgFile(File imgFile, String imgUrl, String imgFileName) {
		OutputStream os = null;
		InputStream is = null;
		try {

			File f1 = new File(imgUrl);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			f1 = new File(imgUrl + File.separator + imgFileName);

			os = new FileOutputStream(f1); // 写
			is = new FileInputStream(imgFile); // 读

			int len = (int) imgFile.length();
			int i = 0;
			byte[] bt = new byte[1024];

			if (is.available() > len) {
				return false;
			}

			while ((i = is.available()) > 0) {
				is.read(bt);
				if (i < 1024) {
					os.write(bt, 0, i);
				} else {
					os.write(bt);
				}
				os.flush();
			}
			is.close();
		} catch (Exception e) {
			log.error("BuildingPicBusiness-->copyImgFile", e);
			return false;
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (Exception e) {
					log.error("BuildingPicBusiness-->copyImgFile", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("BuildingPicBusiness-->copyImgFile", e);
				}
			}
		}
		return true;
	}

	public static Logger getLog() {
		return log;
	}

	private static String replacePath(String rootPath) {
		if ("\\".equals(File.separator)) {
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = rootPath.replace("\\", "/");
		}
		return rootPath;
	}

}
