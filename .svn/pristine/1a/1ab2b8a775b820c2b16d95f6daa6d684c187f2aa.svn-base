package com.hanqian.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hanqian.form.VOSetParam;
import com.hanqian.pojo.DbHospInfo;
import com.hanqian.util.PerformSQLUtil;
import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;



/**
 * 区域标准比例与医院实际比例
 * @author clczp
 *
 */
@Service("StandardRatioBusiness")
public class StandardRatioBusiness   extends BaseBusiness{
	/**
	 * log4g日志
	 */
	public static final Logger logg = Logger.getLogger(StandardRatioBusiness.class);
	
//	private static final Log logg = LogFactory.getLog(StandardRatioBusiness.class);
//	private BuildRateMgr rateMgr;
//	private HospInfoMgr hosMgr;
	
	
	
 
	/**
	 * 标准比例与实际比例
	 * @param dbusers
	 * @return
	 */
	public RetCode findData(String selUnits){
		if (logg.isDebugEnabled())
			logg.debug("进入StandardRatioBusiness.findData!参数selUnits:"+selUnits);
		RetCode rt= new RetCode();
		try {
 
			//查询数据
			VOSetParam obj = new VOSetParam();
			//RetCode rts=rateMgr.findStandardRatio();//标准查询
			RetCode rts=this.getData("findStandardRatio", null);//标准查询
			obj.setBdata(rts.getObj());				//标准设置
			try {
				RetCode rtr=null;
				if (SysUtil.isNull(selUnits)) {
					//List<DbHospInfo> list=hosMgr.findAllHos();	
					List<DbHospInfo> list=sqlSession.selectList("findAllHosFindData");	
					for (int i = 0; i < list.size() && i<=24; i++) {
						//rtr=rateMgr.findHospRatio(list.get(i).getSeqHosp().toString());	 //医院实际查询
						Map mapvalue = new HashMap();
						mapvalue.put("hospid", list.get(i).getSeqHosp().toString());
						rtr = this.getData("findHospRatio", mapvalue);
						//获取医院名称
						//hosMgr.findById(list.get(i).getSeqHosp());
						
						
						obj.addRlsit(rtr.getObj(),list.get(i).getSeqHosp().toString(),"医院名称");	 //医际实际设置
						
					}
				}else{
					String[] hospids= selUnits.split(",");
					for (int i = 0; i < hospids.length; i++) {
//						rtr=rateMgr.findHospRatio(hospids[i]);	//医院实际查询
						Map mapvalue = new HashMap();
						mapvalue.put("hospid", hospids[i]);
						rtr = this.getData("findHospRatio", mapvalue);
						//DbHospInfo hospInfo=hosMgr.findById(Integer.parseInt(hospids[i]));
						int seqHosp = Integer.parseInt(hospids[i]);
						DbHospInfo hospInfo=sqlSession.selectOne("findByIdFindData", seqHosp);
						
						obj.addRlsit(rtr.getObj(),hospids[i],hospInfo.getHospName());	//医际实际设置						
					}
				}			
				
			} catch (Exception e) {
				logg.error("StandardRatioBusiness-->findData", e);
				e.printStackTrace();
			}
			
			//表格数据设置
			if (obj.getRlsit().size() ==1) {
//				obj.initiTableData("医院实际比例",false);
				obj.initiTableData("医院实际比例",false);
			}else{
				obj.initiTableData("汇总信息",true);			
			}
						
			rt.setCode(0);
			rt.setDesc("操作成功");
			rt.setDetail("操作成功");
			rt.setObj(obj);
		} catch (Exception e) {
			logg.error("StandardRatioBusiness-->findData", e);
			e.printStackTrace();
			rt.setCode(1004);
			rt.setDesc("异常出错");
			rt.setDetail("异常出错");
		}
		return rt;
	}
	
	public Map getFindDataSql() {
		return PerformSQLUtil.get("findHospRatio", this);
	}

	
//	public void setRateMgr(BuildRateMgr rateMgr) {
//		this.rateMgr = rateMgr;
//	}
//
//	public void setHosMgr(HospInfoMgr hosMgr) {
//		this.hosMgr = hosMgr;
//	}
}
