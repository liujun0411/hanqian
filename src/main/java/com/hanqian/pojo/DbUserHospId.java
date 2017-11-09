package com.hanqian.pojo;

import java.lang.Integer;


/**
 * DbUserHospId entity. @author MyEclipse Persistence Tools
 */

public class DbUserHospId  implements java.io.Serializable {


    // Fields    

     private Integer seqHosp;
     private Integer seq;
     private Integer dept;


    // Constructors

    /** default constructor */
    public DbUserHospId() {
    }

    
    /** full constructor */
    public DbUserHospId(Integer seqHosp, Integer seq, Integer dept) {
        this.seqHosp = seqHosp;
        this.seq = seq;
        this.dept = dept;
    }

   
    // Property accessors

    public Integer getSeqHosp() {
        return this.seqHosp;
    }
    
    public void setSeqHosp(Integer seqHosp) {
        this.seqHosp = seqHosp;
    }

    public Integer getSeq() {
        return this.seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getDept() {
        return this.dept;
    }
    
    public void setDept(Integer dept) {
        this.dept = dept;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DbUserHospId) ) return false;
		 DbUserHospId castOther = ( DbUserHospId ) other; 
         
		 return ( (this.getSeqHosp()==castOther.getSeqHosp()) || ( this.getSeqHosp()!=null && castOther.getSeqHosp()!=null && this.getSeqHosp().equals(castOther.getSeqHosp()) ) )
 && ( (this.getSeq()==castOther.getSeq()) || ( this.getSeq()!=null && castOther.getSeq()!=null && this.getSeq().equals(castOther.getSeq()) ) )
 && ( (this.getDept()==castOther.getDept()) || ( this.getDept()!=null && castOther.getDept()!=null && this.getDept().equals(castOther.getDept()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSeqHosp() == null ? 0 : this.getSeqHosp().hashCode() );
         result = 37 * result + ( getSeq() == null ? 0 : this.getSeq().hashCode() );
         result = 37 * result + ( getDept() == null ? 0 : this.getDept().hashCode() );
         return result;
   }   





}