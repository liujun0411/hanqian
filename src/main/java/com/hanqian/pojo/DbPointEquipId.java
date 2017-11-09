package com.hanqian.pojo;

import java.lang.Integer;


/**
 * DbPointEquipId entity. @author MyEclipse Persistence Tools
 */

public class DbPointEquipId  implements java.io.Serializable {


    // Fields    

     private Integer seq;
     private Integer equipId;
     private Integer hospId;


    // Constructors

    /** default constructor */
    public DbPointEquipId() {
    }

    
    /** full constructor */
    public DbPointEquipId(Integer seq, Integer equipId, Integer hospId) {
        this.seq = seq;
        this.equipId = equipId;
        this.hospId = hospId;
    }

   
    // Property accessors

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getEquipId() {
        return this.equipId;
    }
    
    public void setEquipId(Integer equipId) {
        this.equipId = equipId;
    }

    public Integer getHospId() {
        return this.hospId;
    }
    
    public void setHospId(Integer hospId) {
        this.hospId = hospId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DbPointEquipId) ) return false;
		 DbPointEquipId castOther = ( DbPointEquipId ) other; 
         
		 return ( (this.getSeq()==castOther.getSeq()) || ( this.getSeq()!=null && castOther.getSeq()!=null && this.getSeq().equals(castOther.getSeq()) ) )
 && ( (this.getEquipId()==castOther.getEquipId()) || ( this.getEquipId()!=null && castOther.getEquipId()!=null && this.getEquipId().equals(castOther.getEquipId()) ) )
 && ( (this.getHospId()==castOther.getHospId()) || ( this.getHospId()!=null && castOther.getHospId()!=null && this.getHospId().equals(castOther.getHospId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSeq() == null ? 0 : this.getSeq().hashCode() );
         result = 37 * result + ( getEquipId() == null ? 0 : this.getEquipId().hashCode() );
         result = 37 * result + ( getHospId() == null ? 0 : this.getHospId().hashCode() );
         return result;
   }   





}