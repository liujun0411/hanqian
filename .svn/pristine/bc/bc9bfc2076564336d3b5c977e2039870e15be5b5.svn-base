package com.hanqian.util;

public class RetCode {
    // 参照数据表DB_ERRORCODE
    long      code;  // 错误代码

    String    desc;  // 错误类型

    String    detail; // 错误详细描述 &&&

    Object obj = null; //列表信息
    
    Page page;  //分页信息    
    
    
  
	public RetCode() {

       
    }

    public RetCode(long code, String desc, String detail) {

        super();

        this.code = code;

        this.desc = desc;

        this.detail = detail;
    }

    public RetCode(long code, String desc, String detail,  Object obj) {

        super();

        this.code = code;

        this.desc = desc;

        this.detail = detail;
        
        this.obj = obj;
    }
    
    
    
    
    public RetCode(long code, String desc, String detail, Object obj, Page page) {
    	
		this.code = code;
		this.desc = desc;
		this.detail = detail;
		this.obj = obj;
		this.page = page;
	}


    /**
     * @return Returns the code.
     */
    public long getCode() {
        return code;
    }

    /**
     * @return Returns the desc.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return Returns the detail.
     */
    public String getDetail() {
        return detail;
    }

    public static RetCode Exp2RtnCode(Exception e) {
        RetCode ret = new RetCode(1003, "未知错误", "未知错误");

        String cause = e.getClass().getName();

        if (cause.equals("java.lang.ClassCastException")) {

            ret = new RetCode(1001, "类型转换错误:java.lang.ClassCastException", e
                    .getMessage());
        }

        return ret;

    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
    
   

	/**
	 * @param code The code to set.
	 */
	public void setCode(long code) {
		this.code = code;
	}

	/**
	 * @param desc The desc to set.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param detail The detail to set.
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	} 

   
}

