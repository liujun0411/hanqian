package com.hanqian.pojo;

import java.util.Date;

/**
 * @author lizhenhua
 *规则类
 */
public class Dbrule {
  /**
 * 规则类ID
 */
private Integer id;
  /**
 * 规则名
 */
private String rulename;
  /**
 * 最后发送时间
 */
private Date ruletime;
  /**
 * 规则类型  1 天气类 weather
 */
private String ruletype;
/**
 * 维护人员ID
 */
private Integer sermainseql;


/**
 * 维修人员姓名
 */
private String name;

/**
 * 规则Id
 */
private Integer ruleseq;
  
  public Integer getSermainseql() {
	return sermainseql;
}
public void setSermainseql(Integer sermainseql) {
	this.sermainseql = sermainseql;
}
public Integer getRuleseq() {
	return ruleseq;
}
public void setRuleseq(Integer ruleseq) {
	this.ruleseq = ruleseq;
}



public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getRulename() {
	return rulename;
}
public void setRulename(String rulename) {
	this.rulename = rulename;
}
public Date getRuletime() {
	return ruletime;
}
public void setRuletime(Date ruletime) {
	this.ruletime = ruletime;
}
public String getRuletype() {
	return ruletype;
}
public void setRuletype(String ruletype) {
	this.ruletype = ruletype;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
  
  
  
  
  
  
  
}
