package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;


/**
 * DbHospDetail entity. @author MyEclipse Persistence Tools
 */

public class DbHospDetail  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private DbHospInfo dbHospInfo;
     private DbUsers dbUsers;
     private Double buildareas;
     private Double innerareas;
     private Double outareas;
     private Double leaseareas;
     private Double setupareas;
     private Double demolishareas;
     private Double buildamount;
     private Double landareas;
     private Double landamount;
     private Double plotratio;
     private Double afforestation;
     private Double hireareas;
     private Double hire;
     private Double rentareas;
     private Double rent;
     private String principal;
     private String landdept;
     private String medicalareas;
     private Integer bedspace;
     private Integer bedcheck;
     private Short carplaceUp;
     private Short carplaceDown;
     private Double buildsumareas;
     private Date inputtime;
     private Date opertime;


    // Constructors

    /** default constructor */
    public DbHospDetail() {
    }

    
    /** full constructor */
    public DbHospDetail(DbHospInfo dbHospInfo, DbUsers dbUsers, Double buildareas, Double innerareas, Double outareas, Double leaseareas, Double setupareas, Double demolishareas, Double buildamount, Double landareas, Double landamount, Double plotratio, Double afforestation, Double hireareas, Double hire, Double rentareas, Double rent, String principal, String landdept, String medicalareas, Integer bedspace, Integer bedcheck, Short carplaceUp, Short carplaceDown, Double buildsumareas, Date inputtime, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbUsers = dbUsers;
        this.buildareas = buildareas;
        this.innerareas = innerareas;
        this.outareas = outareas;
        this.leaseareas = leaseareas;
        this.setupareas = setupareas;
        this.demolishareas = demolishareas;
        this.buildamount = buildamount;
        this.landareas = landareas;
        this.landamount = landamount;
        this.plotratio = plotratio;
        this.afforestation = afforestation;
        this.hireareas = hireareas;
        this.hire = hire;
        this.rentareas = rentareas;
        this.rent = rent;
        this.principal = principal;
        this.landdept = landdept;
        this.medicalareas = medicalareas;
        this.bedspace = bedspace;
        this.bedcheck = bedcheck;
        this.carplaceUp = carplaceUp;
        this.carplaceDown = carplaceDown;
        this.buildsumareas = buildsumareas;
        this.inputtime = inputtime;
        this.opertime = opertime;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public Double getBuildareas() {
        return this.buildareas;
    }
    
    public void setBuildareas(Double buildareas) {
        this.buildareas = buildareas;
    }

    public Double getInnerareas() {
        return this.innerareas;
    }
    
    public void setInnerareas(Double innerareas) {
        this.innerareas = innerareas;
    }

    public Double getOutareas() {
        return this.outareas;
    }
    
    public void setOutareas(Double outareas) {
        this.outareas = outareas;
    }

    public Double getLeaseareas() {
        return this.leaseareas;
    }
    
    public void setLeaseareas(Double leaseareas) {
        this.leaseareas = leaseareas;
    }

    public Double getSetupareas() {
        return this.setupareas;
    }
    
    public void setSetupareas(Double setupareas) {
        this.setupareas = setupareas;
    }

    public Double getDemolishareas() {
        return this.demolishareas;
    }
    
    public void setDemolishareas(Double demolishareas) {
        this.demolishareas = demolishareas;
    }

    public Double getBuildamount() {
        return this.buildamount;
    }
    
    public void setBuildamount(Double buildamount) {
        this.buildamount = buildamount;
    }

    public Double getLandareas() {
        return this.landareas;
    }
    
    public void setLandareas(Double landareas) {
        this.landareas = landareas;
    }

    public Double getLandamount() {
        return this.landamount;
    }
    
    public void setLandamount(Double landamount) {
        this.landamount = landamount;
    }

    public Double getPlotratio() {
        return this.plotratio;
    }
    
    public void setPlotratio(Double plotratio) {
        this.plotratio = plotratio;
    }

    public Double getAfforestation() {
        return this.afforestation;
    }
    
    public void setAfforestation(Double afforestation) {
        this.afforestation = afforestation;
    }

    public Double getHireareas() {
        return this.hireareas;
    }
    
    public void setHireareas(Double hireareas) {
        this.hireareas = hireareas;
    }

    public Double getHire() {
        return this.hire;
    }
    
    public void setHire(Double hire) {
        this.hire = hire;
    }

    public Double getRentareas() {
        return this.rentareas;
    }
    
    public void setRentareas(Double rentareas) {
        this.rentareas = rentareas;
    }

    public Double getRent() {
        return this.rent;
    }
    
    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getPrincipal() {
        return this.principal;
    }
    
    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getLanddept() {
        return this.landdept;
    }
    
    public void setLanddept(String landdept) {
        this.landdept = landdept;
    }

    public String getMedicalareas() {
        return this.medicalareas;
    }
    
    public void setMedicalareas(String medicalareas) {
        this.medicalareas = medicalareas;
    }

    public Integer getBedspace() {
        return this.bedspace;
    }
    
    public void setBedspace(Integer bedspace) {
        this.bedspace = bedspace;
    }

    public Integer getBedcheck() {
        return this.bedcheck;
    }
    
    public void setBedcheck(Integer bedcheck) {
        this.bedcheck = bedcheck;
    }

    public Short getCarplaceUp() {
        return this.carplaceUp;
    }
    
    public void setCarplaceUp(Short carplaceUp) {
        this.carplaceUp = carplaceUp;
    }

    public Short getCarplaceDown() {
        return this.carplaceDown;
    }
    
    public void setCarplaceDown(Short carplaceDown) {
        this.carplaceDown = carplaceDown;
    }

    public Double getBuildsumareas() {
        return this.buildsumareas;
    }
    
    public void setBuildsumareas(Double buildsumareas) {
        this.buildsumareas = buildsumareas;
    }

    public Date getInputtime() {
        return this.inputtime;
    }
    
    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }
   








}