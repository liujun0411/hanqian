package com.hanqian.form;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hanqian.business.DbinfoBuiness;
import com.hanqian.common.TableData;
import com.hanqian.util.SysUtil;



/**
 * 区域标准比例与医院实际比例
 * @author clczp
 *
 */
public class VOSetParam {
	/**
	 * log4g日志
	 */
	private static final Log logg = LogFactory.getLog(VOSetParam.class);
	private VOMyRatio bdata=new VOMyRatio();//标准对象	
	private List<VOHospRatio> rlsit=new ArrayList<VOHospRatio>();//医院实际对象集	
	private TableData tdata=new TableData();//汇总表格
	private DecimalFormat fm = new DecimalFormat("#0.00");	//数据格式化
	private DbinfoBuiness dao ;
	
	public VOMyRatio getBdata() {
		return bdata;
	}
	public List<VOHospRatio> getRlsit() {
		return rlsit;
	}
	public TableData getTdata() {
		return tdata;
	}
	
	
	
	/**
	 * 设置标准
	 * @param obj
	 */
	public void setBdata(Object obj) {
		try {
			List list = (List)obj;
			HashMap m=null;
			float value=0f;
			String id=null;
			for (int i = 0; i < list.size(); i++) {
				m = (HashMap)list.get(i);
				value = SysUtil.toFloat(m.get("datavalue")+"");
				id = m.get("seq")+"";
				if ("43".equals(id)) {
					bdata.getMz().setDatavalue(value);				
				}else if ("44".equals(id)) {
					bdata.getJz().setDatavalue(value);
				}else if ("45".equals(id)) {
					bdata.getZy().setDatavalue(value);
				}else if ("46".equals(id)) {
					bdata.getYj().setDatavalue(value);
				}else if ("47".equals(id)) {
					bdata.getBz().setDatavalue(value);
				}else if ("48".equals(id)) {
					bdata.getXz().setDatavalue(value);
				}else if ("49".equals(id)) {
					bdata.getKy().setDatavalue(value);
				}else if ("50".equals(id)) {
					bdata.getJy().setDatavalue(value);
				}else if ("51".equals(id)) {
					bdata.getSh().setDatavalue(value);
				}else if ("52".equals(id)) {
					bdata.getCk().setDatavalue(value);
				}else if ("53".equals(id)) {
					bdata.getQt().setDatavalue(value);
				}
			}
		} catch (Exception e) {
			logg.error("VOSetParam-->setBdata", e);

		}

	}
	
	/**
	 * 添加实际
	 * @param obj
	 */
	public void addRlsit(Object obj,String hospid,String hospName) {
		try {
			List list = (List)obj;
			
			VOHospRatio hos= new VOHospRatio();
			hos.setHosName(hospName);
			HashMap<String,Object> m=null;
			float sum=0f;
			float value=0f;
			float area=0f;
			String str=null;
			String id=null;
			
			for (int i = 0; i < list.size(); i++) {
				m = (HashMap<String,Object>)list.get(i);
				sum += SysUtil.toFloat(m.get("datavalue")+"");
			}
			for (int i = 0; i < list.size(); i++) {
				m = (HashMap<String,Object>)list.get(i);
				area = SysUtil.toFloat(m.get("datavalue")+"");
				value = SysUtil.toFloat(fm.format(area/sum*100));
				id = m.get("id")+"";
				
				if ("43".equals(id)) {
					hos.getMz().setDatavalue(value);
					hos.getMz().setArea(area);
				}else if ("44".equals(id)) {
					hos.getJz().setDatavalue(value);
					hos.getJz().setArea(area);
				}else if ("45".equals(id)) {
					hos.getZy().setDatavalue(value);
					hos.getZy().setArea(area);
				}else if ("46".equals(id)) {
					hos.getYj().setDatavalue(value);
					hos.getYj().setArea(area);
				}else if ("47".equals(id)) {
					hos.getBz().setDatavalue(value);
					hos.getBz().setArea(area);
				}else if ("48".equals(id)) {
					hos.getXz().setDatavalue(value);
					hos.getXz().setArea(area);
				}else if ("49".equals(id)) {
					hos.getKy().setDatavalue(value);
					hos.getKy().setArea(area);
				}else if ("50".equals(id)) {
					hos.getJy().setDatavalue(value);
					hos.getJy().setArea(area);
				}else if ("51".equals(id)) {
					hos.getSh().setDatavalue(value);
					hos.getSh().setArea(area);
				}else if ("52".equals(id)) {
					hos.getCk().setDatavalue(value);
					hos.getCk().setArea(area);
				}else if ("53d".equals(id)) {
					hos.getQt().setDatavalue(value);
					hos.getQt().setArea(area);
				}
			}
			
			rlsit.add(hos);
		} catch (Exception e) {
			logg.error("VOSetParam-->addRlsit", e);
			e.printStackTrace();
		}
	}
		
	
	/**
	 * 初始化表格数据
	 * @param title		表格标题
	 * @param issum		汇总
	 */
	public void initiTableData(String title,boolean issum) {
		try {
			this.tdata.setTitle(title);
			//字段
			List<String> keys=new ArrayList<String>();
			keys.add("医院");
			for (int i = 0; i < this.bdata.size(); i++) {
				keys.add(bdata.get(i).getName());
			}
			this.tdata.setKeys(keys);
			
			//记录
			List<Object> values=new ArrayList<Object>();
			List<Object> re=null;
			List<Object> rs=new ArrayList<Object>();
			rs.add("平均比例");
			
			float value=0f;
			for (int i = 0; i < this.rlsit.size(); i++) {
				re= new ArrayList<Object>();
				re.add(this.rlsit.get(i).getHosName());
				for (int j = 0; j < this.bdata.size(); j++) {
					value=this.rlsit.get(i).get(j).getDatavalue();
					if (rs.size() < j+2 ) {
						rs.add(value);
					}else{
						rs.set(j+1, SysUtil.toFloat(rs.get(j+1).toString())+value);
					}
					re.add(value);
				}
				//re.add(fm.format(sum/cl));
				values.add(re);
			}
			for (int i = 1; i < rs.size(); i++) {
				rs.set(i,fm.format(SysUtil.toFloat(rs.get(i).toString())/this.rlsit.size()));
			}
			if (rs.size()>1 && issum) {
				values.add(rs);
			}
			
			
			this.tdata.setValues(values);
		} catch (Exception e) {
			logg.error("VOSetParam-->initiTableData", e);
			// TODO: handle exception
		}
		
		
	}
	
	
	
	
	/**
	 * 获得医院名缩写
	 * @param hospid
	 * @return
	 */
//	private String getHospName(String hospid){
//		System.out.println(hospid);
//		System.out.println(dao);
//		return dao.findById(Integer.parseInt(hospid)).getHospName();
//	}
	public DecimalFormat getFm() {
		return fm;
	}
	public void setFm(DecimalFormat fm) {
		this.fm = fm;
	}
	public DbinfoBuiness getDao() {
		return dao;
	}
	public void setDao(DbinfoBuiness dao) {
		this.dao = dao;
	}
	public void setBdata(VOMyRatio bdata) {
		this.bdata = bdata;
	}
	public void setRlsit(List<VOHospRatio> rlsit) {
		this.rlsit = rlsit;
	}
	public void setTdata(TableData tdata) {
		this.tdata = tdata;
	}
	
}
