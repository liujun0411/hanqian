package com.hanqian.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.hanqian.form.VOEquipGroup;
import com.hanqian.form.VOEquipList;
import com.hanqian.pojo.DbEquipGroup;
import com.hanqian.pojo.DbEquipType;
import com.hanqian.pojo.DbGroupEquip;
import com.hanqian.pojo.DbGroupEquipId;
import com.hanqian.util.HashList;
import com.hanqian.util.Page;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

/**
 * 描 述: 设备分组 前置条件: 后置条件: 调用者 : 被调用者: 重载说明: Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-9-19
 * @see
 */
@Service
public class EquipGroupBusiness extends BaseBusiness {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(EquipGroupBusiness.class);

	/**
	 * 设备分组列表
	 * 
	 * @return
	 */
	public RetCode findSelGroup() {
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findSelGroup");	
		RetCode rc = new RetCode();
		// 得到查询的所有

		List list = sqlSession.selectList("findEquipGroupList");
		
		rc.setObj(list);
		if (list != null) {
			Map m = null;
			VOEquipGroup obj = null;
			String groupId = null;
			String groupName = null;
			List<VOEquipGroup> rlist = new ArrayList<VOEquipGroup>();
			for (int i = 0; i < list.size(); i++) {
				m = (Map) list.get(i);
				groupId = m.get("group_id") + "";
				groupName = m.get("group_name") + "";
				// 用VOEquipGroup做实体类
				obj = new VOEquipGroup();
				obj.setGroupId(groupId);
				obj.setGroupName(groupName);
				//
				rlist.add(obj);
			}
			rc.setObj(rlist);
		} else {
			rc.setDesc("暂无数据");
		}
		return rc;
	}
	
	public Map getFindSelGroupSql() {
		return PerformSQLUtil.get("findEquipGroupList", this);
	}

	

	/**
	 * 设备分组列表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param wherestr
	 *            参数字符串
	 * @return
	 */
	public RetCode findGroupList(int currentPage, int pageSize,@Param("_parameter")String _parameter) {
		if(_parameter!=null){
			if(_parameter.equals("")){
				_parameter=null;
			}
		}
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findGroupList,参数wherestr:"+_parameter);	
		RetCode rt = new RetCode();
		HashList<VOEquipGroup> list = null;
		try {
			// 查询设备组
			//List rlist = equipGroupMgr.findEquipGroupList(wherestr);
			List rlist = sqlSession.selectList("findEquipGroupLists", _parameter);
			
			if (rlist != null) {
				list = new HashList<VOEquipGroup>();
				Map m = null;
				// 设备组
				VOEquipGroup obj = null;
				// 设备
				VOEquipList eq = null;
				String seq = null;
				String groupName = null;
				String eqName = null;
				String equipId = null;
				String equipType = null;
				String picName = null;
				String buildId = null;
				String groupRemark = null;
				String groupCode = null;
				
				DbGroupEquip objm = new DbGroupEquip();
				
				for (int i = 0, j = 1; i < rlist.size(); i++) {
					
					m =  (Map) rlist.get(i);
					seq = m.get("seq") + "";
					groupName = m.get("group_name") + "";
					eqName = m.get("equip_name") + "";
					equipId = m.get("equip_id") + "";
					equipType = m.get("equip_type") + "";
					picName = m.get("picname")+"";
					buildId = m.get("building_id")+"";
					groupRemark = m.get("remark") + "";
					groupCode = m.get("groupcode")+"";
					obj = list.find(seq);
					
					// 添加组
					if (obj == null) {
						obj = new VOEquipGroup();
						obj.setSeq(Integer.parseInt(seq));
						obj.setIndex(j++);
						obj.setGroupName(groupName);
						obj.setGroupRemark(groupRemark);
						obj.setPicName(picName);
						obj.setBuildId(buildId);
						obj.setEquipType(equipType);
						obj.setEquipList(new ArrayList<VOEquipList>());
						obj.setGroupCode(groupCode);
						list.add(seq, obj);
					}

					// 添加设备
					if (obj.findEqList(equipId) == null) {
						eq = new VOEquipList();
						eq.setEquipName(eqName);
						obj.getEquipList().add(eq);
					}
				}
			}

		} catch (Exception e) {
			logg.error("EquipGroupBusiness-->findGroupList", e);
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if (list != null) {
			HashList<VOEquipGroup> rlist = new HashList<VOEquipGroup>();
			for (int i = (currentPage - 1) * pageSize; i < list.size()
					&& i < currentPage * pageSize; i++) {
				rlist.add(list.get(i));
			}
			rt.setObj(rlist);
			rt.setPage(new Page(currentPage, list.size(), pageSize));
		}
		return rt;
	}
	
	public Map getFindGroupListSql() {
		return PerformSQLUtil.get("findEquipGroupLists", this);
	}

	/**
	 * 通过设备组编号删除组
	 * 
	 * @param groupId
	 * @return
	 */
	public boolean deleteEquipGroup(String groupId,String imgUrl) {
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.deleteEquipGroup,参数groupId:"
					+ groupId+" imgUrl:"+imgUrl);		
		boolean check = true;	
		try{
			// 设备分组
						DbEquipGroup equipGroup = new DbEquipGroup();
						equipGroup.setSeq(Integer.parseInt(groupId));

						equipGroup = sqlSession.selectOne("findEquipGroupById", equipGroup);
						
						if(equipGroup!=null){
							// 设备组关系
							DbGroupEquip dbGroupEquip = new DbGroupEquip();
							// Id
							DbGroupEquipId id = new DbGroupEquipId();
							id.setEquipGroup(equipGroup.getSeq());
							dbGroupEquip.setId(id);
							sqlSession.delete("deleteGroupEquip2",dbGroupEquip);
							
							//删除之前上传的文件
							File f2 = new File(imgUrl+equipGroup.getGroupPic());
							f2.deleteOnExit();
							// 1
							sqlSession.delete("deleteEquipGroup",equipGroup);
							
						}
			
			
		}catch(Exception e){
			check = false;
			logg.error("进入EquipGroupBusiness.deleteEquipGroup,对象删除失败！", e);
		}					
		return check;
		
	}
	


	/**
	 * 查询设备类型列表
	 * 
	 * @return
	 */
	public List<DbEquipType> findEqTypeListNotPei() {
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findEqTypeListNotPei!"	);	
		// 查询到可分组设备类型
		
		RetCode rt = this.getData("findGroupEquipTypeNotPei", null);	
		
		List<DbEquipType> typelist = null;
		// 判断有没有数据
		if (rt != null) {
			if (rt.getObj() != null) {
				typelist = (List<DbEquipType>) rt.getObj();
			}
		}
		// 设备类型
		return typelist;
	}
	
	public Map getFindEqTypeListNotPeiSql() {
		return PerformSQLUtil.get("findGroupEquipTypeNotPei", this);
	}

	/**
	 * 查询设备列表 (当前类型及二级子类型所有设备)
	 * 
	 * @param eqtype
	 *            设备类型
	 * @return
	 */
	public List<VOEquipList> findEqList(String eqtype, String groupId) {
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findEqList!参数eqtype:"+eqtype +" groupId"+groupId	);
		List<VOEquipList> list = null;
		// 有设备类型Id查询得到所有设备
		
		Map map = new HashMap();
		map.put("eqtype", eqtype);
		if (null==groupId||"".equals(groupId)) {
			map.put("groupId", null);
		}else{
			map.put("groupId", groupId);
		}
		
		List rlist = sqlSession.selectList("findEqList", map);
		// 判断是否有数据
		if (rlist != null) {
			list = new ArrayList<VOEquipList>();
			Map m = null;
			VOEquipList obj = null;
			// 用工具类VOEquipList
			for (int i = 0; i < rlist.size(); i++) {
				m = (Map) rlist.get(i);
				obj = new VOEquipList();
				obj.setEquipId(m.get("equipid") + "");
				obj.setEquipName(m.get("equipname") + "");
				list.add(obj);
			}
		}
		return list;
	}
	
	public Map getFindEqListSql() {
		return PerformSQLUtil.get("findEqList", this);
	}
	
	/**
	 * 查询设备列表 (当前类型及二级子类型所有设备)
	 * 
	 * @param eqtype
	 *            设备类型
	 * @return
	 */ 
	public List<VOEquipList> findEqList(String eqtype, String groupId,String buildId) {
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findEqList!参数eqtype:"+eqtype +" groupId"+groupId+" buildId"+buildId	);
		List<VOEquipList> list = null;
		// 有设备类型Id查询得到所有设备
		
		Map map = new HashMap();
		map.put("eqtype", eqtype);
		if (null==buildId||"".equals(buildId)||"0".equals(buildId)) {
			map.put("buildId", null);
		}else{
			map.put("buildId", buildId);
		}
		if (null==groupId||"".equals(groupId)) {
			map.put("groupId", null);
		}else{
			map.put("groupId", groupId);
		}
		
		List rlist = sqlSession.selectList("findEqLists", map);
		
		// 判断是否有诗句
		if (rlist != null) {
			list = new ArrayList<VOEquipList>();
			Map m = null;
			VOEquipList obj = null;
			// 用工具类VOEquipList
			for (int i = 0; i < rlist.size(); i++) {
				m = (Map) rlist.get(i);
				obj = new VOEquipList();
				obj.setEquipId(m.get("equipid") + "");
				obj.setEquipName(m.get("equipname") + "");
				obj.setBuildingId(m.get("buildingId") + "");
				list.add(obj);
			}
		}
		return list;
	}

	public Map getFindEqListsSql() {
		return PerformSQLUtil.get("findEqLists", this);
	}
	/**
	 * 查询当前组设备列表(名称列表)
	 * 
	 * @param id
	 * @return
	 */
	public String[] findGrouEq(String id) {
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findGrouEq!参数id:"+id );
		
		List list =  sqlSession.selectList("findEquipList", id);
		
		String eqlist = "";
		String haveEq = "";
		if (list != null) {
			Map m = null;
			for (int i = 0; i < list.size(); i++) {
				m = (Map) list.get(i);
				if ("" == eqlist) {
					eqlist = m.get("equip_id") + "=" + m.get("equip_name");
				} else {
					eqlist += "," + m.get("equip_id") + "="
							+ m.get("equip_name");
				}
				haveEq += m.get("equip_id") + "";
				if (i < list.size() - 1) {
					haveEq += ",";
				}
			}
		}
		String[] str = {eqlist, haveEq};
		return str;
	}
	
	public Map getFindGrouEqSql() {
		return PerformSQLUtil.get("findEquipList", this);
	}

	/**
	 * 新增
	 * 
	 * @param 2012-9-25
	 * @param @param equipId
	 * @param @param groupName
	 * @param @param equipType
	 * @return void
	 */
	public String insertEquipGroup(String equipId, String groupName, String groupRemark,
			String equipType,File groupPic,String fileName,String imgUrl,String build_id) {
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.insertEquipGroup!参数equipId:"+equipId+" groupName:"+groupName+ 
					" groupRemark:"+groupRemark+" equipType:"+equipType+" fileName:"+fileName
					+" imgUrl:"+imgUrl+" build_id:"+build_id);
		
		String uuid=java.util.UUID.randomUUID().toString();
		
		try {
			if (groupPic!=null&&StringUtil.isNotEmpty(fileName)&&StringUtil.trimNull(fileName).length()>0) {
				String afterName = fileName.substring(fileName.lastIndexOf("."));
				if( this.copyImgFile(groupPic, imgUrl, uuid+afterName)){
					this.insertEquipGroup(equipId, groupName, groupRemark, equipType,uuid+afterName,build_id);
				}else{
					return "图片限制在6兆以内！";
				}
			}else{
				this.insertEquipGroup(equipId, groupName, groupRemark, equipType,"",build_id);
			}
		} catch (Exception e) {
			logg.error("EquipGroupBusiness-->insertEquipGroup", e);
			e.printStackTrace();
			return "操作失败";
		}
		return "操作成功";
	}
	

	
	public void insertEquipGroup(String equipId, String groupName, String groupRemark,
			String equipType,String fileName,String build_id) {
		try {
			DbEquipGroup equipGroup = new DbEquipGroup();
			equipGroup.setGroupName(groupName);
			equipGroup.setGroupPic(fileName);
			equipGroup.setRemark(groupRemark);
			if (StringUtil.isNotEmpty(build_id)&&!"0".equals(build_id)) {
				equipGroup.setBuildId(Integer.parseInt(build_id));
			}
			DbEquipType dbEquipType = new DbEquipType();
			dbEquipType.setEquipTypeId(Integer.parseInt(equipType));
			equipGroup.setDbEquipType(dbEquipType);
			equipGroup.setOpertime(new Date());
			// 1
			
			sqlSession.insert("insertEquipGroup", equipGroup);
			String[] equip = equipId.split(",");
			for (String str : equip) {
				// 设备组关系
				DbGroupEquip dbGroupEquip = new DbGroupEquip();
				dbGroupEquip.setDbEquipGroup(equipGroup);
				// Id
				DbGroupEquipId id = new DbGroupEquipId();
				id.setEquipGroup(equipGroup.getSeq());
				id.setEquipId(Integer.parseInt(str));
				dbGroupEquip.setId(id);
				
				sqlSession.insert("insertGroupEquip", dbGroupEquip);
			}			
		} catch (Exception e) {			
			logg.error("EquipGroupMgr-->insertEquipGroup", e);
			e.printStackTrace();
		}

	}
	

	/**
	 * 修改
	 * 
	 * @param 2012-9-26
	 * @param @param equipBefore
	 * @param @param equipAfter
	 * @param @param groupId
	 * @param @param groupName
	 * @param @param equipType
	 * picName 之前的图片名
	 * @return void
	 */
	public String updateEquipGroup(String equipBefore, String equipAfter,
			String groupId, String groupName, String groupRemark, String equipType,File groupPic,String groupPicFileName,String imgUrl,String picName,String build_id,String groupCode) {
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.updateEquipGroup!参数equipBefore:"+equipBefore+" equipAfter:"+equipAfter+ 
					" groupId:"+groupId+" groupName:"+groupName+" groupRemark:"+groupRemark
					+" equipType:"+equipType+" build_id:"+build_id);
		
		try {
			String[] delEquip = equipBefore.split(",");
			String[] addEquip = equipAfter.split(",");
			
			String uuid=java.util.UUID.randomUUID().toString();
			if (groupPicFileName!=null&&StringUtil.isNotEmpty(groupPicFileName)&&StringUtil.trimNull(groupPicFileName).length()>0) {//上传的图片
				String afterName = groupPicFileName.substring(groupPicFileName.lastIndexOf("."));
					if(this.copyImgFile(groupPic, imgUrl, uuid+afterName)){
						if(StringUtil.isNotEmpty(picName)&&StringUtil.trimNull(picName).length()>0){
							File f2 = new File(imgUrl+picName);
							f2.deleteOnExit();
						}
						// 删除所有的原来的数据
						this.updateEquipGroup(delEquip, addEquip, groupId, groupName, groupRemark,
								equipType,uuid+afterName,build_id,groupCode);
					}else{
						//图片超大
						return "图纸限制在6兆以内！";
					}
					
			}else{
				this.updateEquipGroup(delEquip, addEquip, groupId, groupName, groupRemark,equipType,picName,build_id,groupCode);
			}
		} catch (Exception e) {
			logg.error("EquipGroupBusiness-->updateEquipGroup", e);
			return "操作失败";
		}
		return "操作成功";
	}
	
	
	public void updateEquipGroup(String[] delEquip, String[] addEquip,
			String groupId, String groupName, String groupRemark, String equipType,String fileName,String build_id,String groupCode) {
		
		try {
			DbEquipGroup equipGroup = new DbEquipGroup();
			equipGroup.setSeq(Integer.parseInt(groupId));
			equipGroup.setGroupName(groupName);
			equipGroup.setRemark(groupRemark);
			equipGroup.setGroupCode(groupCode);
			equipGroup.setOpertime(new Date());
			if (StringUtil.isNotEmpty(fileName)) {
				equipGroup.setGroupPic(fileName);
			}
			if (StringUtil.isNotEmpty(build_id)&&!"0".equals(build_id)) {
				equipGroup.setBuildId(Integer.parseInt(build_id));
			}
			DbEquipType dbEquipType = new DbEquipType();
			dbEquipType.setEquipTypeId(Integer.parseInt(equipType));
			equipGroup.setDbEquipType(dbEquipType);
			// 修改设备组信息
			
			sqlSession.update("updateEquipGroupByKey", equipGroup);
			// 删除原本的数据
			for (String str : delEquip) {
				// 设备组关系
				DbGroupEquip dbGroupEquip = new DbGroupEquip();
				// Id
				DbGroupEquipId id = new DbGroupEquipId();
				id.setEquipGroup(equipGroup.getSeq());
				id.setEquipId(Integer.parseInt(str));
				dbGroupEquip.setId(id);
				
				sqlSession.delete("deleteGroupEquip", dbGroupEquip);
			}
			
			// 重新添加
			for (String str : addEquip) {
				// 设备组关系
				DbGroupEquip dbGroupEquip1 = new DbGroupEquip();
				dbGroupEquip1.setDbEquipGroup(equipGroup);
				// Id
				DbGroupEquipId id = new DbGroupEquipId();
				id.setEquipGroup(equipGroup.getSeq());
				id.setEquipId(Integer.parseInt(str));
				dbGroupEquip1.setId(id);
				//groupEquipDAO.save(dbGroupEquip1);
				sqlSession.insert("insertGroupEquip", dbGroupEquip1);
			}			
		} catch (Exception e) {			
			logg.error("EquipGroupMgr-->updateEquipGroup", e);
			e.printStackTrace();			
		}

	}

	
	/**
	 * 设备分组列表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param groupId
	 *            组Id
	 * @param groupName
	 *            组名字
	 * @param remark
	 *            备注
	 * @return
	 */
	public RetCode findGroupList(int currentPage, int pageSize, String typeId,
			String groupId) {
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findGroupList!参数typeId:"+typeId+" groupId:"+groupId);
		
		RetCode rt = new RetCode();

		HashList<VOEquipGroup> list = null;
		try {
			
			
			Map map = new HashMap();
			
			if (null==typeId||"".equals(typeId)) {
				map.put("typeId", null);
			}else{
				map.put("typeId", typeId);
			}
			
			if (null==groupId||"".equals(groupId)) {
				map.put("groupId", null);
			}else{
				map.put("groupId", groupId);
			}
			
			
			List rlist = sqlSession.selectList("findEquipGroupListss", map);
			
			if (rlist != null) {
				list = new HashList<VOEquipGroup>();
				Map m = null;
				// 设备组
				VOEquipGroup obj = null;
				// 设备
				VOEquipList eq = null;
				String seq = null;
				String groupName = null;
				String eqName = null;
				String equipId = null;
				String equipType = null;
				String typeName = null;
				String buildId = null;
				String remark = null;
				String groupCode = null;
				
				for (int i = 0, j = 1; i < rlist.size(); i++) {
					m = (Map) rlist.get(i);
					seq = m.get("seq") + "";
					groupName = m.get("group_name") + "";
					eqName = m.get("equip_name") + "";
					equipId = m.get("equip_id") + "";
					equipType = m.get("equip_type") + "";
					typeName = m.get("type_name") + "";
					buildId = m.get("build_id")+"";
					remark = m.get("remark")+"";
					groupCode = m.get("groupcode")+"";
					obj = list.find(seq);

					// 添加组
					if (obj == null) {
						obj = new VOEquipGroup();
						obj.setSeq(Integer.parseInt(seq));
						obj.setIndex(j++);
						obj.setGroupName(groupName);
						obj.setEquipType(equipType);
						obj.setTypeName(typeName);
						obj.setBuildId(buildId);
						obj.setGroupRemark(remark);
						obj.setGroupCode(groupCode);
						obj.setEquipList(new ArrayList<VOEquipList>());
						list.add(seq, obj);
					}

					// 添加设备
					if (obj.findEqList(equipId) == null) {
						eq = new VOEquipList();
						eq.setEquipName(eqName);
						obj.getEquipList().add(eq);
					}
				}
			}

		} catch (Exception e) {
			logg.error("EquipGroupBusiness-->findGroupList", e);
			// TODO: handle exception
			e.printStackTrace();
		}

		if (list != null) {
			HashList<VOEquipGroup> rlist = new HashList<VOEquipGroup>();
			for (int i = (currentPage - 1) * pageSize; i < list.size()
					&& i < currentPage * pageSize; i++) {
				rlist.add(list.get(i));
			}
			rt.setObj(rlist);
			rt.setPage(new Page(currentPage, list.size(), pageSize));
		}
		return rt;
	}
	
	public Map getFindGroupListssSql() {
		return PerformSQLUtil.get("findEquipGroupListss", this);
	}

	/**
	 * 查询当前组设备列表(名称列表)
	 * 
	 * @param id
	 * @return
	 */
	public List<VOEquipList> findEquipByGroupId(String groupId) {

		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findEquipByGroupId!参数groupId:"+groupId);
		List<VOEquipList> eqList = null;
		
		List list = sqlSession.selectList("findEquipList", groupId);
		
		// 设备
		VOEquipList eq = null;
		if (list != null) {
			Map m = null;
			String eqName = null;
			String equipId = null;
			for (int i = 0; i < list.size(); i++) {
				m = (Map) list.get(i);
				eqName = m.get("equip_name") + "";
				equipId = m.get("equip_id") + "";
				eq = new VOEquipList();
				eq.setEquipName(eqName);
				eq.setEquipId(equipId);
				eqList.add(eq);
			}
		}
		return eqList;
	}
	
	public Map getFindEquipByGroupIdSql() {
		return PerformSQLUtil.get("findEquipList", this);
	}

	/**
	 * 获得单个分组设备信息
	 * 
	 * @param groupId
	 * @return
	 */
	public List findEquipGroup(String groupId) {
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findEquipGroup!参数groupId:"+groupId);
		
		Map map = new HashMap();
		map.put("groupId", groupId);
		return sqlSession.selectList("findEquipGroup", map);
	}
	
	public Map getFindEquipGroupSql() {
		return PerformSQLUtil.get("findEquipGroup", this);
	}

	/**
	 * 根据参数，查询该类型下的所有的子组列表
	 * 
	 * @param 2012-11-6
	 * @param @param buildingId
	 * @param @param stroey
	 * @param @param eqTypeId
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findPeiDian(String buildingId, String stroey,
			String eqTypeId, int currentPage, int pageSize) {
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findPeiDian!参数buildingId:"+buildingId+" stroey:"+stroey+" eqTypeId:"+eqTypeId+" currentPage:"+currentPage+" pageSize:"+pageSize);
		
		Map map = new HashMap();
		map.put("eqTypeId", eqTypeId);
		if (null==buildingId||"".equals(buildingId)||"0".equals(buildingId)) {
			map.put("buildingId", null);
		}else{
			map.put("buildingId", buildingId);
		}
		if (null==stroey||"".equals(stroey)||"0".equals(stroey)) {
			map.put("stroey", null);
		}else{
			map.put("stroey", stroey);
		}
		
		RetCode rc = this.getPageData("findPeiDian", map, currentPage, pageSize);
		
		
		
		
		return rc;
	}
	
	public Map getFindPeiDianSql() {
		return PerformSQLUtil.get("findPeiDian", this);
	}

	/**
	 * 将File 通过io写入指定url‘
	 * @param statement  
	 * @param     imgFile 文件，imgUrl 写入的地址 
	 * @return
	 */
	public boolean copyImgFile(File imgFile,String imgUrl,String imgFileName){
		OutputStream os = null;
		InputStream is = null;
		try{
		File f1=new File(imgUrl);
		if(!f1.exists()){
			f1.mkdirs();
		}
		f1=new File(imgUrl+File.separator+imgFileName);
			
		os = new FileOutputStream(f1);	//写
		is = new FileInputStream(imgFile);		//读
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
		}catch (Exception e) {
			logg.error("EquipGroupBusiness-->copyImgFile", e);
			return false;
		}finally{
			if (os != null) {
				try {
					os.flush();
					os.close();
				} catch (Exception e) {
					logg.error("EquipGroupBusiness-->copyImgFile", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					logg.error("EquipGroupBusiness-->copyImgFile", e);
				}
			}
		}
		return true;

	}

	/**
	 * 根据父ID，获得所有子项组
	 * 
	 * @param 2012-11-6
	 * @param @param groupId
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findChildGroup(String groupId) {
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findChildGroup!参数groupId:"+groupId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		return this.getData("findChildGroup", map);
	}

	public RetCode findById(String groupId) {
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findById!参数groupId:"+groupId);
		Map map = new HashMap();
		map.put("groupId", groupId);
		return this.getData("findByIdEquipGroupBusiness", map);
	}

	
	/**
	 * 根据设备ID获得设备所属的组
	 */
	public RetCode findGroupByEquip(String equipId){
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findGroupByEquip!参数equipId:"+equipId);
		Map map = new HashMap();
		map.put("equipId", equipId);
		return this.getData("findGroupByEquip", map);
	}
	
	/**
	 * 根据设备类型Id,获得设备组列表
	 * @param 2012-11-16  
	 * @param @param eqTypeId
	 * @param @return       
	 * @return RetCode
	 */
	public RetCode findGroupByEQTypeId(String eqTypeId){
		
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findGroupByEQTypeId!参数eqTypeId:"+eqTypeId);
		Map map = new HashMap();
		map.put("eqTypeId", eqTypeId);
		return this.getData("findGroupByEQTypeId", map);
	}
	/**
	 * 根据组ID获取组图片
	 * @param 2012-11-21  
	 * @param @param groupId
	 * @param @return       
	 * @return String
	 */
	public String findPicNameByGroupId(Integer groupId){
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findPicNameByGroupId!参数groupId:"+groupId);
		String picName = "";
		
		Map mapv = new HashMap();
		mapv.put("groupId", groupId);
		RetCode rc = this.getData("findPicNameByGroupId", mapv);
		if(rc.getObj()!=null){
			List list = (List)rc.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map)list.get(i);
				if (map.get("group_pic")!=null) {
					picName = map.get("group_pic").toString();
				}
			}
		}
		return	picName;
	}
	
	/**
	 * 根据设备ID获取安装位置图
	 * @param 2012-11-21  
	 * @param @param groupId
	 * @param @return       
	 * @return String
	 */
	public String findPicNameByEquipId(Integer equipId){
		if (logg.isDebugEnabled())
			logg.debug("进入EquipGroupBusiness.findPicNameByEquipId!参数equipId:"+equipId);
		String picName = "";
		
		Map mapv = new HashMap();
		mapv.put("equipId", equipId);
		RetCode rc = this.getData("findPicNameByEquipId", mapv);
		if(rc.getObj()!=null){
			List list = (List)rc.getObj();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map)list.get(i);
				if (map.get("pic_address")!=null) {
					picName = map.get("pic_address").toString();
				}
			}
		}
		return	picName;
	}

}
