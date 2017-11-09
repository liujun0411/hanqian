package com.hanqian.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.aspectj.lang.annotation.SuppressAjWarnings;


/**
 * 可查找性List
 * @author wyy
 *
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
	
	
	public E find(String key){
		E e = null;
		try {
			e = (E)this.get(m.get(key));
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		return e;		
	}
}
