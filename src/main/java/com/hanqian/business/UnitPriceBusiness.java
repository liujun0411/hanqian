package com.hanqian.business;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbEnergyBill;
import com.hanqian.pojo.DbUnitPrice;
import com.hanqian.util.RetCode;

@Service
public class UnitPriceBusiness extends BaseBusiness {
	private static final Logger log = Logger.getLogger(UnitPriceBusiness.class);

	
	/**
	 * 查询能源单价
	 * @param 2013-8-27  
	 * @param @return       
	 * @return RetCode
	 */
	public RetCode findUnitPrice(DbUnitPrice unitPrice) {
		RetCode rc = new RetCode();
		Map prarm = new HashMap();
		
		if (unitPrice != null && unitPrice.getType()!=null) {
			prarm.put("type",  unitPrice.getType());
		}
		if (unitPrice != null && unitPrice.getId()!=null) {			
			prarm.put("id",  unitPrice.getId());
		}
	
		rc = this.getData("findUnitPrice", prarm);
		return rc;
	}
	/**
	 * 添加单价信息
	 * @param 2013-8-27  
	 * @param @param unitPrice       
	 * @return void
	 */
	public void insertUnitPrice(DbUnitPrice unitPrice){
		sqlSession.insert("insertUnitPrice", unitPrice);
	}
	/**
	 * 更新单价信息
	 * @param 2013-8-27  
	 * @param @param unitPrice       
	 * @return void
	 */
	public void updateUnitPrice (DbUnitPrice unitPrice){
		sqlSession.update("updateUnitPrice", unitPrice);
	}
	/**新需要【能源账单，需要修改单价】时间：2016/03/09*/
	public boolean updateEnergyBillBypriceSql(DbEnergyBill engrgyBill) {
		try {
		Map map = new HashMap();
		map.put("pricId", engrgyBill.getDbUnitPrice().getId());
		map.put("pricValue", engrgyBill.getDbUnitPrice().getUnitPrice());
		sqlSession.update("updateEnergyBillByPriceSql", map);
		return true;
		} catch (Exception e) {
			log.error("EnergyBillMgr-->updateEnergyBillBypriceSql", e);
			return false;
		}
	}
	/**
	 * 删除
	 * @param 2013-9-12  
	 * @param @param unitPrice       
	 * @return void
	 */
	public boolean deleteUnitPriceBySql (DbUnitPrice unitPrice){
		try{
			Map param = new HashMap();
			param.put("id", unitPrice.getId());
			
			sqlSession.delete("deleteUnitPriceBySql", param);
			return  true;
		}catch (Exception e) {
			log.error("UnitPriceMgr-->deleteUnitPriceBySql", e);
			return false;
		}
	}
	

	/**
	 * 
	 * @param 2013-8-29  
	 * @param @param billMonth  添加的账单的月份
	 * @param @param energyType 能源类型
	 * @param @param cacu  上一条记录还是下一条记录 lt上一条 gt 下一条 eq该条记录
	 * @param @return       
	 * @return RetCode
	 */
	public RetCode findIsHasUnitPrice(String billMonth,Short energyType,String cacu){
		RetCode rc = new RetCode();
		Map param = new HashMap();
		param.put("billMonth", billMonth);
		param.put("energyType", energyType);
		
		
		if ("lt".equalsIgnoreCase(cacu)) {
			
			rc = this.getData("findIsHasUnitPricelt", param);
		}else if("gt".equalsIgnoreCase(cacu)){
			rc = this.getData("findIsHasUnitPricegt", param);
		
		}else{
			rc = this.getData("findIsHasUnitPrice", param);
			
		}
		
		return rc;

	}
	
	
	public String getOtherMonth(String billMonth,String type){
		String end = "";
		String start[] = billMonth.split("-");
		Integer month = Integer.parseInt(start[1]);
		Integer year = Integer.parseInt(start[0]);
		if ("lt".equalsIgnoreCase(type)) {
			if (month>1) {
				Integer tempMonth = month-1;
				if (tempMonth<10) {
					end = year+"-0"+ tempMonth;
				}else{
					end = year+"-"+tempMonth;
				}
			}else{
				end = (year-1)+"-"+12;
			}
		}else if("gt".equalsIgnoreCase(type)){
			if (month<12) {
				Integer tempMonth = month+1;
				if (tempMonth<10) {
					end = year+"-0"+ tempMonth;
				}else{
					end = year+"-"+tempMonth;
				}
			}
		}else{
			end = billMonth;
		}
			return end;
	}
	/**
	 * 
	 * @param 2013-8-29  
	 * @param @param billMonth  添加的账单的月份
	 * @param @param energyType 能源类型
	 * @param @param cacu  上一条记录还是下一条记录 lt上一条 gt 下一条  
	 * 
	 *   start  指 start相等  end  指 end 相等
	 *   and 表示 start end 之前
	 * @param @param unitPrice  单价
	 * @param @return       
	 * @return RetCode
	 */
	public RetCode findHasRecord(String billMonth,Integer id,Double unitPrice,String cacu){
		RetCode rc = new RetCode();
		String end = getOtherMonth(billMonth,cacu);
		Map param = new HashMap();
		param.put("end", end);
		param.put("id", id);
		param.put("unitPrice", unitPrice);
		
		if ("start".equalsIgnoreCase(cacu)) {
			rc = this.getData("findHasRecordstartstart", param);
		}else if("end".equalsIgnoreCase(cacu)){
			rc = this.getData("findHasRecordstartend", param);
		}else if("and".equalsIgnoreCase(cacu)){
			rc = this.getData("findHasRecordstartand", param);
		}else{
			rc = this.getData("findHasRecordstart", param);
		}
		
		return rc;

	}
}
