package com.hanqian.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hanqian.business.UsersBusiness;

/**
 * 描 述: 
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author wyy
 * @version 1.4 2012-12-5
 * @see
 */
public class PhoneUtil {
	
	private UsersBusiness usersMgr;
	int count=1;
	public void text(){
		count++;
		//RetCode r=usersMgr.findBySQL(usersMgr.getDbUsersDAO(), "select username from db_users u where u.userid='czy'");
		RetCode r = usersMgr.findPhoneUtil();
				
		
		List lst=(List)r.getObj();
		Map m=(Map)lst.get(0);
		String name=m.get("username").toString();
		if(name.equals("操作员"))
			System.out.println(count+"次调用 方法 现在时间:"+new Date().toLocaleString());
		else
			System.out.println("异常"+new Date().toLocaleString());
	}

	
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("足球"+i);
				Thread.sleep(1000);
 
			}
			for (int i = 0; i < 100; i++) {
				System.out.println("篮球"+i);
				 
 
			}
		} catch (Exception e) {
			 
		}
		 
		System.out.println("123");
	}
	
	public UsersBusiness getUsersMgr() {
		return usersMgr;
	}

	public void setUsersMgr(UsersBusiness usersMgr) {
		this.usersMgr = usersMgr;
	}
 
}
