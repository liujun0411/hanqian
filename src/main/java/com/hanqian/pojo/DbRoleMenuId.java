package com.hanqian.pojo;

import java.lang.Integer;


/**
 * DbRoleMenuId entity. @author MyEclipse Persistence Tools
 */

public class DbRoleMenuId  implements java.io.Serializable {


    // Fields    

     private String menuid;
     private Integer roleid;


    // Constructors

    /** default constructor */
    public DbRoleMenuId() {
    }

    
    /** full constructor */
    public DbRoleMenuId(String menuid, Integer roleid) {
        this.menuid = menuid;
        this.roleid = roleid;
    }

   
    // Property accessors

   

    public Integer getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
   



   public String getMenuid() {
		return menuid;
	}


	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}


public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DbRoleMenuId) ) return false;
		 DbRoleMenuId castOther = ( DbRoleMenuId ) other; 
         
		 return ( (this.getMenuid()==castOther.getMenuid()) || ( this.getMenuid()!=null && castOther.getMenuid()!=null && this.getMenuid().equals(castOther.getMenuid()) ) )
 && ( (this.getRoleid()==castOther.getRoleid()) || ( this.getRoleid()!=null && castOther.getRoleid()!=null && this.getRoleid().equals(castOther.getRoleid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMenuid() == null ? 0 : this.getMenuid().hashCode() );
         result = 37 * result + ( getRoleid() == null ? 0 : this.getRoleid().hashCode() );
         return result;
   }   





}