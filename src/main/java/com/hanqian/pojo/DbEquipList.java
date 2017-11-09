package com.hanqian.pojo;

import java.lang.Integer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * DbEquipList entity. @author MyEclipse Persistence Tools
 */

public class DbEquipList  implements java.io.Serializable {


    // Fields    

     private Integer equipId;
     private DbHospInfo dbHospInfo;
     private DbBuilding dbBuilding;
     private DbEnergyType dbEnergyType;
     private DbEquipType dbEquipType;
     private DbUsers dbUsers;
     private String equipCode;
     private String equipName;
     private String assetscode;
     private Double storey;
     private String place;
     private Short controlFlag;
     private String brand;
     private String unitType;
     private String production;
     private String field;
     private Date productionDate;
     private Date installDate;
     private Date useDate;
     private Short serviceLife;
     private Date limited;
     private Double purchase;
     private Integer serviceCycle;
     private String accessory;
     private Short status;
     private Date opertime;
     private Set dbEquipPicRels = new HashSet(0);
     private Set equipServiceEquipsForSerEquipId = new HashSet(0);
     private Set dbKeyEqs = new HashSet(0);
//     private Set dbEquipGroups = new HashSet(0);
     private Set dbEqParams = new HashSet(0);
     private Set dbMonitorlogs = new HashSet(0);
     private Set equipServiceEquipsForEquipId = new HashSet(0);
     private Set<EquipService> equipServices = new HashSet<EquipService>(0);
     private Set dbMaintenances = new HashSet(0);
     private Set dbRefTransformers = new HashSet(0);
     private Set dbPointEquips = new HashSet(0);
     private Set<DbEquipList> equipService = new HashSet<DbEquipList>(0);
     private Integer node_level;
     private Integer node_parent;

    // Constructors

    public Set<DbEquipList> getEquipService() {
		return equipService;
	}

	public void setEquipService(Set<DbEquipList> equipService) {
		this.equipService = equipService;
	}

	/** default constructor */
    public DbEquipList() {
    }

	/** minimal constructor */
    public DbEquipList(DbHospInfo dbHospInfo, DbEnergyType dbEnergyType, DbUsers dbUsers, String equipCode, String equipName, Double storey, String place, Short controlFlag, Date installDate, Date useDate, Short serviceLife, Integer serviceCycle, Short status, Date opertime) {
        this.dbHospInfo = dbHospInfo;
        this.dbEnergyType = dbEnergyType;
        this.dbUsers = dbUsers;
        this.equipCode = equipCode;
        this.equipName = equipName;
        this.storey = storey;
        this.place = place;
        this.controlFlag = controlFlag;
        this.installDate = installDate;
        this.useDate = useDate;
        this.serviceLife = serviceLife;
        this.serviceCycle = serviceCycle;
        this.status = status;
        this.opertime = opertime;
    }
    
    /** full constructor */
    public DbEquipList(DbHospInfo dbHospInfo, DbBuilding dbBuilding, DbEnergyType dbEnergyType, DbEquipType dbEquipType, DbUsers dbUsers, String equipCode, String equipName, String assetscode, Double storey, String place, Short controlFlag, String brand, String unitType, String production, String field, Date productionDate, Date installDate, Date useDate, Short serviceLife, Date limited, Double purchase, Integer serviceCycle, String accessory, Short status, Date opertime, Set dbEquipPicRels, Set equipServiceEquipsForSerEquipId, Set dbKeyEqs, Set dbEquipGroups, Set dbEqParams, Set dbMonitorlogs, Set equipServiceEquipsForEquipId, Set equipServices, Set dbMaintenances, Set dbRefTransformers, Set dbPointEquips) {
        this.dbHospInfo = dbHospInfo;
        this.dbBuilding = dbBuilding;
        this.dbEnergyType = dbEnergyType;
        this.dbEquipType = dbEquipType;
        this.dbUsers = dbUsers;
        this.equipCode = equipCode;
        this.equipName = equipName;
        this.assetscode = assetscode;
        this.storey = storey;
        this.place = place;
        this.controlFlag = controlFlag;
        this.brand = brand;
        this.unitType = unitType;
        this.production = production;
        this.field = field;
        this.productionDate = productionDate;
        this.installDate = installDate;
        this.useDate = useDate;
        this.serviceLife = serviceLife;
        this.limited = limited;
        this.purchase = purchase;
        this.serviceCycle = serviceCycle;
        this.accessory = accessory;
        this.status = status;
        this.opertime = opertime;
        this.dbEquipPicRels = dbEquipPicRels;
        this.equipServiceEquipsForSerEquipId = equipServiceEquipsForSerEquipId;
        this.dbKeyEqs = dbKeyEqs;
//        this.dbEquipGroups = dbEquipGroups;
        this.dbEqParams = dbEqParams;
        this.dbMonitorlogs = dbMonitorlogs;
        this.equipServiceEquipsForEquipId = equipServiceEquipsForEquipId;
        this.equipServices = equipServices;
        this.dbMaintenances = dbMaintenances;
        this.dbRefTransformers = dbRefTransformers;
        this.dbPointEquips = dbPointEquips;
    }

   
    // Property accessors

    public Integer getEquipId() {
        return this.equipId;
    }
    
    public void setEquipId(Integer equipId) {
        this.equipId = equipId;
    }

    public DbHospInfo getDbHospInfo() {
        return this.dbHospInfo;
    }
    
    public void setDbHospInfo(DbHospInfo dbHospInfo) {
        this.dbHospInfo = dbHospInfo;
    }

    public DbBuilding getDbBuilding() {
        return this.dbBuilding;
    }
    
    public void setDbBuilding(DbBuilding dbBuilding) {
        this.dbBuilding = dbBuilding;
    }

    public DbEnergyType getDbEnergyType() {
        return this.dbEnergyType;
    }
    
    public void setDbEnergyType(DbEnergyType dbEnergyType) {
        this.dbEnergyType = dbEnergyType;
    }

    public DbEquipType getDbEquipType() {
        return this.dbEquipType;
    }
    
    public void setDbEquipType(DbEquipType dbEquipType) {
        this.dbEquipType = dbEquipType;
    }

    public DbUsers getDbUsers() {
        return this.dbUsers;
    }
    
    public void setDbUsers(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    public String getEquipCode() {
        return this.equipCode;
    }
    
    public void setEquipCode(String equipCode) {
        if("".equals(equipCode))
    		equipCode=null;
        this.equipCode = equipCode;
    }

    public String getEquipName() {
        return this.equipName;
    }
    
    public void setEquipName(String equipName) {
        if("".equals(equipName))
    		equipName=null;
        this.equipName = equipName;
    }

    public String getAssetscode() {
        return this.assetscode;
    }
    
    public void setAssetscode(String assetscode) {
        if("".equals(assetscode))
    		assetscode=null;
        this.assetscode = assetscode;
    }

    public Double getStorey() {
        return this.storey;
    }
    
    public void setStorey(Double storey) {
        this.storey = storey;
    }

    public String getPlace() {
        return this.place;
    }
    
    public void setPlace(String place) {
        if("".equals(place))
    		place=null;
        this.place = place;
    }

    public Short getControlFlag() {
        return this.controlFlag;
    }
    
    public void setControlFlag(Short controlFlag) {
        if("".equals(controlFlag))
    		controlFlag=null;
        this.controlFlag = controlFlag;
    }

    public String getBrand() {
        return this.brand;
    }
    
    public void setBrand(String brand) {
        if("".equals(brand))
    		brand=null;
        this.brand = brand;
    }

    public String getUnitType() {
        return this.unitType;
    }
    
    public void setUnitType(String unitType) {
       if("".equals(unitType))
    		unitType=null;
        this.unitType = unitType;
    }

    public String getProduction() {
        return this.production;
    }
    
    public void setProduction(String production) {
        if("".equals(production))
    		production=null;
        this.production = production;
    }

    public String getField() {
        return this.field;
    }
    
    public void setField(String field) {
        if("".equals(field))
    		field=null;
        this.field = field;
    }

    public Date getProductionDate() {
        return this.productionDate;
    }
    
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getInstallDate() {
        return this.installDate;
    }
    
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Date getUseDate() {
        return this.useDate;
    }
    
    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Short getServiceLife() {
        return this.serviceLife;
    }
    
    public void setServiceLife(Short serviceLife) {
        this.serviceLife = serviceLife;
    }

    public Date getLimited() {
        return this.limited;
    }
    
    public void setLimited(Date limited) {
        this.limited = limited;
    }

    public Double getPurchase() {
        return this.purchase;
    }
    
    public void setPurchase(Double purchase) {
        this.purchase = purchase;
    }

    public Integer getServiceCycle() {
        return this.serviceCycle;
    }
    
    public void setServiceCycle(Integer serviceCycle) {
        this.serviceCycle = serviceCycle;
    }

    public String getAccessory() {
        return this.accessory;
    }
    
    public void setAccessory(String accessory) {
        if("".equals(accessory))
    		accessory=null;
        this.accessory = accessory;
    }

    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getOpertime() {
        return this.opertime;
    }
    
    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public Set getDbEquipPicRels() {
        return this.dbEquipPicRels;
    }
    
    public void setDbEquipPicRels(Set dbEquipPicRels) {
        this.dbEquipPicRels = dbEquipPicRels;
    }

    public Set getEquipServiceEquipsForSerEquipId() {
        return this.equipServiceEquipsForSerEquipId;
    }
    
    public void setEquipServiceEquipsForSerEquipId(Set equipServiceEquipsForSerEquipId) {
        this.equipServiceEquipsForSerEquipId = equipServiceEquipsForSerEquipId;
    }

    public Set getDbKeyEqs() {
        return this.dbKeyEqs;
    }
    
    public void setDbKeyEqs(Set dbKeyEqs) {
        this.dbKeyEqs = dbKeyEqs;
    }

//    public Set getDbEquipGroups() {
//        return this.dbEquipGroups;
//    }
//    
//    public void setDbEquipGroups(Set dbEquipGroups) {
//        this.dbEquipGroups = dbEquipGroups;
//    }

    public Set getDbEqParams() {
        return this.dbEqParams;
    }
    
    public void setDbEqParams(Set dbEqParams) {
        this.dbEqParams = dbEqParams;
    }

    public Set getDbMonitorlogs() {
        return this.dbMonitorlogs;
    }
    
    public void setDbMonitorlogs(Set dbMonitorlogs) {
        this.dbMonitorlogs = dbMonitorlogs;
    }

    public Set getEquipServiceEquipsForEquipId() {
        return this.equipServiceEquipsForEquipId;
    }
    
    public void setEquipServiceEquipsForEquipId(Set equipServiceEquipsForEquipId) {
        this.equipServiceEquipsForEquipId = equipServiceEquipsForEquipId;
    }

    public Set getEquipServices() {
        return this.equipServices;
    }
    
    public void setEquipServices(Set equipServices) {
        this.equipServices = equipServices;
    }

    public Set getDbMaintenances() {
        return this.dbMaintenances;
    }
    
    public void setDbMaintenances(Set dbMaintenances) {
        this.dbMaintenances = dbMaintenances;
    }

    public Set getDbRefTransformers() {
        return this.dbRefTransformers;
    }
    
    public void setDbRefTransformers(Set dbRefTransformers) {
        this.dbRefTransformers = dbRefTransformers;
    }

    public Set getDbPointEquips() {
        return this.dbPointEquips;
    }
    
    public void setDbPointEquips(Set dbPointEquips) {
        this.dbPointEquips = dbPointEquips;
    }

	public Integer getNode_level() {
		return node_level;
	}

	public void setNode_level(Integer node_level) {
		this.node_level = node_level;
	}

	public Integer getNode_parent() {
		return node_parent;
	}

	public void setNode_parent(Integer node_parent) {
		this.node_parent = node_parent;
	}
   
    







}