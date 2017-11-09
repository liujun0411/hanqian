package com.hanqian.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.aspectj.lang.annotation.SuppressAjWarnings;


/**
 * 
 * 描 述: 可查找性List
 * 前置条件: 
 * 后置条件:
 * 调用者 : 
 * 被调用者: 
 * 重载说明:
 * Copyright: Copyright (c) 2012
 * Company: SHANGHAI HANQIAN SOFTWARE CO.,LTD.
 * 
 * @author 
 * @version 1.4 2012-9-19
 * @see
 */
public class HashList<E> extends ArrayList{
	private HashMap<String, Integer> m = new HashMap<String, Integer>();
	public boolean add(String key,E obj){
		if (m.get(key) == null) {
			m.put(key, this.size());
			return this.add(obj);
		}
		
		this.set(m.get(key),obj);
		return true;
		
	}
	
	
	public E find(Integer key){
		E e = null;
		try {
			e = (E)this.get(m.get(key));
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		return e;		
	}
}
