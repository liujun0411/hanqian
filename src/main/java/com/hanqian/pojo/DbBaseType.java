package com.hanqian.pojo;

import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;


/**
 * DbBaseType entity. @author MyEclipse Persistence Tools
 */

public class DbBaseType  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private String operTable;
     private String field1;
     private String field2;
     private Set dbBaseComms = new HashSet(0);


    // Constructors

    /** default constructor */
    public DbBaseType() {
    }

    
    /** full constructor */
    public DbBaseType(String operTable, String field1, String field2, Set dbBaseComms) {
        this.operTable = operTable;
        this.field1 = field1;
        this.field2 = field2;
        this.dbBaseComms = dbBaseComms;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getOperTable() {
        return this.operTable;
    }
    
    public void setOperTable(String operTable) {
        this.operTable = operTable;
    }

    public String getField1() {
        return this.field1;
    }
    
    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return this.field2;
    }
    
    public void setField2(String field2) {
        this.field2 = field2;
    }

    public Set getDbBaseComms() {
        return this.dbBaseComms;
    }
    
    public void setDbBaseComms(Set dbBaseComms) {
        this.dbBaseComms = dbBaseComms;
    }
   








}