package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


/**
 * DbMenus entity. @author MyEclipse Persistence Tools
 */

public class DbMenus  implements java.io.Serializable {


    // Fields    

     private Integer menuId;
     private String menuCode;
     private DbMenus dbMenus;
     private String name;
     private String menuUrl;
     private Integer status;
     private String images;
     private String remarks;
     private String depict;
     private Short sorts;
     private Integer menuLevel;
     private Date dueDate;
     private Set dbUserses = new HashSet(0);
     private Set dbUsersLogs = new HashSet(0);
     private Set dbRoleMenus = new HashSet(0);
     private Set<DbMenus> dbMenuses = new HashSet<DbMenus>(0);
//     private Set dbMenuses=new TreeSet();

    // Constructors

    /** default constructor */
    public DbMenus() {
    }

    
    /** full constructor */
    public DbMenus(DbMenus dbMenus, String name, String menuUrl, Integer status, String images, String remarks, String depict, Short sorts, Integer menuLevel, Date dueDate, Set dbUserses, Set dbUsersLogs, Set dbRoleMenus, Set dbMenuses) {
        this.dbMenus = dbMenus;
        this.name = name;
        this.menuUrl = menuUrl;
        this.status = status;
        this.images = images;
        this.remarks = remarks;
        this.depict = depict;
        this.sorts = sorts;
        this.menuLevel = menuLevel;
        this.dueDate = dueDate;
        this.dbUserses = dbUserses;
        this.dbUsersLogs = dbUsersLogs;
        this.dbRoleMenus = dbRoleMenus;
        this.dbMenuses = dbMenuses;
    }

   
    // Property accessors

    public Integer getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public DbMenus getDbMenus() {
        return this.dbMenus;
    }
    
    public void setDbMenus(DbMenus dbMenus) {
        this.dbMenus = dbMenus;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getMenuUrl() {
        return this.menuUrl;
    }
    
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }



    public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getImages() {
        return this.images;
    }
    
    public void setImages(String images) {
        this.images = images;
    }

    public String getRemarks() {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDepict() {
        return this.depict;
    }
    
    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Short getSorts() {
        return this.sorts;
    }
    
    public void setSorts(Short sorts) {
        this.sorts = sorts;
    }

    public Integer getMenuLevel() {
        return this.menuLevel;
    }
    
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Date getDueDate() {
        return this.dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Set getDbUserses() {
        return this.dbUserses;
    }
    
    public void setDbUserses(Set dbUserses) {
        this.dbUserses = dbUserses;
    }

    public Set getDbUsersLogs() {
        return this.dbUsersLogs;
    }
    
    public void setDbUsersLogs(Set dbUsersLogs) {
        this.dbUsersLogs = dbUsersLogs;
    }

    public Set getDbRoleMenus() {
        return this.dbRoleMenus;
    }
    
    public void setDbRoleMenus(Set dbRoleMenus) {
        this.dbRoleMenus = dbRoleMenus;
    }

    public Set getDbMenuses() {
        return this.dbMenuses;
    }
    
    public void setDbMenuses(Set dbMenuses) {
        this.dbMenuses = dbMenuses;
    }


	public String getMenuCode() {
		return menuCode;
	}


	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
   








}