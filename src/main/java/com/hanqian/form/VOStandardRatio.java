package com.hanqian.form;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import com.hanqian.util.RetCode;
import com.hanqian.util.SysUtil;


/**
 * 区域标准比例与医院实际比例 VO
 * @author clczp
 *
 */
public class VOStandardRatio {
	private VOMyRatio  bRatio = new VOMyRatio();	//标准数据
	private VOMyRatio  sRatio = new VOMyRatio();	//实际数据
	private DecimalFormat fm = new DecimalFormat("#0.00");	//数据格式化
	
	/**
	 * 初始化数据
	 * @param obj
	 * @param list
	 */
	private void setdataBRatio(List<HashMap<String,Object>> list){
		HashMap<String,Object> m=null;
		float value=0f;
		String str=null;
		String id=null;
		
		for (int i = 0; i < list.size(); i++) {
			m = (HashMap<String,Object>)list.get(i);
			value = SysUtil.toFloat(m.get("datavalue")+"");
			str = (m.get("comparechar")+"").trim();
			id = m.get("id")+"";
			
			
			if ("1".equals(id)) {
				bRatio.getMz().setComparechar(str);
				bRatio.getMz().setDatavalue(value);				
			}else if ("2".equals(id)) {
				bRatio.getJz().setComparechar(str);
				bRatio.getJz().setDatavalue(value);
			}else if ("3".equals(id)) {
				bRatio.getZy().setComparechar(str);
				bRatio.getZy().setDatavalue(value);
			}else if ("4".equals(id)) {
				bRatio.getYj().setComparechar(str);
				bRatio.getYj().setDatavalue(value);
			}else if ("5".equals(id)) {
				bRatio.getBz().setComparechar(str);
				bRatio.getBz().setDatavalue(value);
			}else if ("6".equals(id)) {
				bRatio.getXz().setComparechar(str);
				bRatio.getXz().setDatavalue(value);
			}else if ("7".equals(id)) {
				bRatio.getKy().setComparechar(str);
				bRatio.getKy().setDatavalue(value);
			}else if ("8".equals(id)) {
				bRatio.getJy().setComparechar(str);
				bRatio.getJy().setDatavalue(value);
			}else if ("9".equals(id)) {
				bRatio.getSh().setComparechar(str);
				bRatio.getSh().setDatavalue(value);
			}else if ("10".equals(id)) {
				bRatio.getCk().setComparechar(str);
				bRatio.getCk().setDatavalue(value);
			}else if ("11".equals(id)) {
				bRatio.getQt().setComparechar(str);
				bRatio.getQt().setDatavalue(value);
			}
		}
	}
	/**
	 * 初始化数据
	 * @param obj
	 * @param list
	 */
	private void setdataSRatio(List<HashMap<String,Object>> list){
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
			
			if ("1".equals(id)) {
				sRatio.getMz().setDatavalue(value);
				sRatio.getMz().setArea(area);
			}else if ("2".equals(id)) {
				sRatio.getJz().setDatavalue(value);
				sRatio.getJz().setArea(area);
			}else if ("3".equals(id)) {
				sRatio.getZy().setDatavalue(value);
				sRatio.getZy().setArea(area);
			}else if ("4".equals(id)) {
				sRatio.getYj().setDatavalue(value);
				sRatio.getYj().setArea(area);
			}else if ("5".equals(id)) {
				sRatio.getBz().setDatavalue(value);
				sRatio.getBz().setArea(area);
			}else if ("6".equals(id)) {
				sRatio.getXz().setDatavalue(value);
				sRatio.getXz().setArea(area);
			}else if ("7".equals(id)) {
				sRatio.getKy().setDatavalue(value);
				sRatio.getKy().setArea(area);
			}else if ("8".equals(id)) {
				sRatio.getJy().setDatavalue(value);
				sRatio.getJy().setArea(area);
			}else if ("9".equals(id)) {
				sRatio.getSh().setDatavalue(value);
				sRatio.getSh().setArea(area);
			}else if ("10".equals(id)) {
				sRatio.getCk().setDatavalue(value);
				sRatio.getCk().setArea(area);
			}else if ("11".equals(id)) {
				sRatio.getQt().setDatavalue(value);
				sRatio.getQt().setArea(area);
			}
		}
	}
	
	public VOStandardRatio() {
		super();		
	}
	
	public VOStandardRatio(RetCode rtb,RetCode rts){
		List blist=null;
		List slist=null;
		if (null != rtb.getObj()) {
			blist =(List)rtb.getObj();
		}
		if (null != rts.getObj()) {
			slist =(List)rts.getObj();
		}
		
		//标准
		if (null != blist) {
			this.setdataBRatio(blist);			
		}		
		//实际
		if (null != slist) {
			this.setdataSRatio(slist);
		}
	}
	
	public VOMyRatio getbRatio() {
		return bRatio;
	}

	public VOMyRatio getsRatio() {
		return sRatio;
	}

	public void setbRatio(VOMyRatio bRatio) {
		this.bRatio = bRatio;
	}

	public void setsRatio(VOMyRatio sRatio) {
		this.sRatio = sRatio;
	}

	
}
