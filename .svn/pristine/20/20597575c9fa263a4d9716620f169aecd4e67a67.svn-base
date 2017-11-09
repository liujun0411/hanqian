package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.pojo.DbEnergyBill;
import com.hanqian.util.RetCode;
import com.hanqian.util.StringUtil;

@Service
public class EnergyBillBusiness extends BaseBusiness {
	private static final Logger log = Logger
			.getLogger(EnergyBillBusiness.class);

	/**
	 * 查询能源账单 分页查询
	 * 
	 * @param 2013-8-27
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findEnergyBill(DbEnergyBill eneryBill, int currentPage,
			int pageSize) {
		RetCode rc = new RetCode();
		Map param = new HashMap();
		if (eneryBill != null) {
			if (eneryBill.getType() != null) {
				param.put("type", eneryBill.getType());
			} else {
				param.put("type", null);
			}
			if (eneryBill.getBillMonth() != null
					&& StringUtil.isNotEmpty(eneryBill.getBillMonth())) {
				param.put("billMonth", eneryBill.getBillMonth());
			} else {
				param.put("billMonth", null);
			}
			if (eneryBill.getBarCode() != null
					&& StringUtil.isNotEmpty(eneryBill.getBarCode())) {
				param.put("barCode", eneryBill.getBarCode());
			} else {
				param.put("barCode", null);
			}
		} else {
			param.put("type", null);
			param.put("billMonth", null);
			param.put("barCode", null);
		}
		rc = this.getPageData("findEnergyBill", param, currentPage, pageSize);
		return rc;
	}

	public RetCode findUpdateMonth(String id) {
		Map map = new HashMap();
		map.put("id", id);
		RetCode rc = this.getData("findUpdateMonth", map);
		return rc;
	}

	public RetCode findIsHasBill(Short energyType, Integer unitPriceId) {
		RetCode rc = new RetCode();
		Map param = new HashMap();
		param.put("energyType", energyType);
		param.put("unitPriceId", unitPriceId);
		rc = this.getData("findIsHasBill", param);
		return rc;
	}

	/**
	 * 查询能源账单 分页查询
	 * 
	 * @param 2013-8-27
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findEnergyBillById(String id) {
		RetCode rc = new RetCode();
		Map param = new HashMap();
		param.put("id", id);
		rc = this.getData("findEnergyBillById", param);
		return rc;
	}

	/**
	 * 根据类型,医院简编,查询已经使用过的用户账单
	 * 
	 * @param 2013-9-5
	 * @param @param typeId
	 * @param @param custmerCode
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findUsedBillMonth(String typeId, String custmerCode) {
		RetCode rc = new RetCode();
		if (StringUtil.isNotEmpty(typeId)) {
			Map param = new HashMap();
			param.put("typeId", typeId);
			param.put("custmerCode", custmerCode);
			rc = this.getData("findUsedBillMonth", param);
		}
		return rc;
	}

	/**
	 * 根据seq获取到对应的单位
	 * 
	 * @param 2014-10-21
	 * @param @param tableId
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findUnitBySeq(String tableId) {
		RetCode rc = new RetCode();
		if (StringUtil.isNotEmpty(tableId)) {
			Map param = new HashMap();
			param.put("tableId", tableId);
			rc = this.getData("findUnitBySeq", param);
		}
		return rc;
	}

	/**
	 * 根据seq获取到对应的类型
	 * 
	 * @param 2014-10-21
	 * @param @param tableId
	 * @param @return
	 * @return RetCode
	 */
	public RetCode findEnergyBySeq(String energyType) {
		RetCode rc = new RetCode();
		if (StringUtil.isNotEmpty(energyType)) {

			Map param = new HashMap();
			param.put("energyType", energyType);
			rc = this.getData("findEnergyBySeq", param);

		}
		return rc;
	}

	public RetCode findUpDatePrice(String typeId) {
		RetCode rc = new RetCode();
		StringBuffer strBuf = new StringBuffer();
		if (StringUtil.isNotEmpty(typeId)) {
			Map param = new HashMap();
			param.put("typeId", typeId);
			rc = this.getData("findUpDatePrice", param);
		}
		return rc;
	}

	public RetCode findUpdateByTypeId(String typeId) {

		Map param = new HashMap();
		param.put("typeId", typeId);
		RetCode rc = this.getData("findUpdateByTypeId", param);
		return rc;
	}

	/**
	 * 添加能源账单
	 * 
	 * @param 2013-8-27
	 * @param @param eneryBill
	 * @return void
	 */
	public boolean insertEnergyBill(DbEnergyBill eneryBill) {
		try {
			sqlSession.insert("insertEnergyBill", eneryBill);
			return true;
		} catch (Exception e) {
			log.error("EnergyBillBusiness-->insertEnergyBill", e);
			return false;
		}
	}

	/**
	 * 修改能源账单
	 * 
	 * @param 2013-8-27
	 * @param @param eneryBill
	 * @return void
	 */
	public void updateEnergyBill(DbEnergyBill eneryBill) {
		sqlSession.update("updateEnergyBill", eneryBill);
	}

	public boolean updateEnergyBillBySql(DbEnergyBill engrgyBill) {
		try {

			Map map = new HashMap();
			map.put("type", engrgyBill.getType());
			map.put("billmonth", engrgyBill.getBillMonth());
			map.put("sum", engrgyBill.getSum());
			map.put("customercode", engrgyBill.getCustomerCode());
			map.put("barcode", engrgyBill.getBarCode());
			map.put("used", engrgyBill.getUsed());
			map.put("des", engrgyBill.getDes());
			map.put("consumption", engrgyBill.getConsumption());
			map.put("id", engrgyBill.getId());
			sqlSession.update("updateEnergyBillBySql", map);

			return true;

		} catch (Exception e) {
			log.error("EnergyBillMgr-->updateEnergyBillBySql", e);
			return false;
		}
	}

	public boolean updateEnergyBillBySql(List id, Integer unitPriceId) {
		try {
			String ids = "";
			for (int i = 0; i < id.size(); i++) {
				if (i == id.size() - 1) {
					ids += id.get(i);
				} else {
					ids += id.get(i) + ",";
				}
			}

			Map map = new HashMap();
			map.put("unitPriceId", unitPriceId);
			map.put("ids", ids);
			sqlSession.update("updateEnergyBillBySqlUnitPriceId", map);
			return true;
		} catch (Exception e) {
			log.error("EnergyBillMgr-->updateEnergyBillBySql", e);
			return false;
		}
	}

	public boolean updateEnergyBillUnitPriceId(Integer beforeUnitPriceId,
			Integer afterUnitPriceId) {
		try {
			Map map = new HashMap();
			map.put("afterUnitPriceId", afterUnitPriceId);
			map.put("beforeUnitPriceId", beforeUnitPriceId);
			sqlSession.update("updateEnergyBillUnitPriceId", map);
			return true;
		} catch (Exception e) {
			log.error("EnergyBillMgr-->updateEnergyBillUnitPriceId", e);
			return false;
		}
	}

	public RetCode findEnergyType() {
		RetCode rc = new RetCode();
		rc = this.getData("findEnergyType", null);
		return rc;
	}

	public DbEnergyBill findEnergyBillById(Integer id) {
		Map map = new HashMap();
		map.put("id", id);
		DbEnergyBill dbEnergyBill = sqlSession.selectOne(
				"findEnergyBillByIdXXX", map);
		return dbEnergyBill;
	}

}
