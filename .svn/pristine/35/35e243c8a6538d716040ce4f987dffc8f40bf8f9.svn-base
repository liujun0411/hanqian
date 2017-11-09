package com.hanqian.util;


/**
 * 能源名
 * @author clczp
 *
 */
public class SysPower {
	private SysPower(){}
	
	/**
	 * 查询能源名
	 * @param powid		能源Id
	 * @return
	 */
	public static String getName(Short powid){
		String name="";
		if (1==powid) {
			name="水";
		}else if (2==powid) {
			name="电";
		}else if (3==powid) {
			name="油";
		}else if (4==powid) {
			name="气(医用)";
		}else if (5==powid) {
			name="气(燃气)";
		}else if (0==powid) {
			name="综合";
		}
		return name;
	}
	
	
	/**
	 * 能源单位(中文)
	 * @param powid	能源Id
	 * @return
	 */
	public static String getUnitName(Short powerId){
		String unit="";
		if (1==powerId) {
			unit="吨";
		}else if (2==powerId) {
			unit="度";
		}else if (3==powerId) {
			unit="千克";
		}else if (4==powerId) {
			unit="千克";
		}else if (5==powerId) {
			unit="立方米";
		}else if (0==powerId) {
			unit="吨标煤";
		}
		return unit;
	}
	
	/**
	 * 能源单位(英文缩词)
	 * @param powid	能源Id
	 * @return
	 */
	public static String getUnitEN(Short powid){
		String unit="";
		if (1==powid) {
			unit=" 吨";
		}else if (2==powid) {
			unit=" 度";
		}else if (3==powid) {
			unit="";
		}else if (4==powid) {
			unit="";
		}else if (5==powid) {
			unit="";
		}else if (0==powid) {
			unit="吨标煤";
		}
		return unit;
	}
	
}
