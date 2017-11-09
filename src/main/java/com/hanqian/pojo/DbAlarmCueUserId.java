package com.hanqian.pojo;

import java.lang.Integer;


/**
 * DbAlarmCueUserId entity. @author MyEclipse Persistence Tools
 */

public class DbAlarmCueUserId  implements java.io.Serializable {


    // Fields    

     private Integer userId;
     private Integer cueId;
     private Integer seq;


    // Constructors

    /** default constructor */
    public DbAlarmCueUserId() {
    }

    
    /** full constructor */
    public DbAlarmCueUserId(Integer userId, Integer cueId, Integer seq) {
        this.userId = userId;
        this.cueId = cueId;
        this.seq = seq;
    }

   
    // Property accessors

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCueId() {
        return this.cueId;
    }
    
    public void setCueId(Integer cueId) {
        this.cueId = cueId;
    }

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DbAlarmCueUserId) ) return false;
		 DbAlarmCueUserId castOther = ( DbAlarmCueUserId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getCueId()==castOther.getCueId()) || ( this.getCueId()!=null && castOther.getCueId()!=null && this.getCueId().equals(castOther.getCueId()) ) )
 && ( (this.getSeq()==castOther.getSeq()) || ( this.getSeq()!=null && castOther.getSeq()!=null && this.getSeq().equals(castOther.getSeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getCueId() == null ? 0 : this.getCueId().hashCode() );
         result = 37 * result + ( getSeq() == null ? 0 : this.getSeq().hashCode() );
         return result;
   }   





}