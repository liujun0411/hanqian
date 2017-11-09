package com.hanqian.form;

import java.io.File;
import java.util.Date;



/**
 *  医院信息
 * @author czpsky
 *
 */
public class VOHospInfo {
	
	private int sequence;	//医院ID
	private String name;
	private Integer levels;
	private Date buildtime;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String tel1;
	private String tel2;
	private String tel3;
	private String fax1;
	private String fax2;
	private String zipcode;
	private String principal1;
	private String images1;
	private String images2;
	private String images3;
	private String principal2;
	private String principal3;
	private String principal4;
	private String landsdart;
	private String hospinfo;
	private File myFile;
	private String savePath;
	
	
	
	
	
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getHospinfo() {
		return hospinfo;
	}
	public void setHospinfo(String hospinfo) {
		this.hospinfo = hospinfo;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLevels() {
		return levels;
	}
	public void setLevels(Integer levels) {
		this.levels = levels;
	}
	public Date getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getAddress4() {
		return address4;
	}
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getFax1() {
		return fax1;
	}
	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}
	public String getFax2() {
		return fax2;
	}
	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPrincipal1() {
		return principal1;
	}
	public void setPrincipal1(String principal1) {
		this.principal1 = principal1;
	}

	public String getImages1() {
		return images1;
	}
	public void setImages1(String images1) {
		this.images1 = images1;
	}
	public String getImages2() {
		return images2;
	}
	public void setImages2(String images2) {
		this.images2 = images2;
	}
	public String getImages3() {
		return images3;
	}
	public void setImages3(String images3) {
		this.images3 = images3;
	}
	public String getPrincipal2() {
		return principal2;
	}
	public void setPrincipal2(String principal2) {
		this.principal2 = principal2;
	}
	public String getPrincipal3() {
		return principal3;
	}
	public void setPrincipal3(String principal3) {
		this.principal3 = principal3;
	}
	public String getPrincipal4() {
		return principal4;
	}
	public void setPrincipal4(String principal4) {
		this.principal4 = principal4;
	}
	public String getLandsdart() {
		return landsdart;
	}
	public void setLandsdart(String landsdart) {
		this.landsdart = landsdart;
	}

	
	
}
