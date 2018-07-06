package com.zhichao.service.core.util;


import java.util.List;

import com.zhichao.dal.mapper.DictMapper;
import com.zhichao.beans.guns.Dict;
import com.zhichao.core.util.SpringContextHolder;

public class DictList {
   
	 private    DictList(){}
	  private   List<Dict> getDictList()
	    {
		  DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
		  List<Dict> list =dictMapper.selectList(null);

		  return  list;
	    }
	    
	   private  static List<Dict> ld ;
	    
	    //3.提供一个用于获取实例的方法,使用public static
	    public static List<Dict> getInstance()
	    {
	        //第一个人来的时候提示没有实例程序就创建一个实例，第二个人来的时候一判断程序有实力对象了就不在创建实例对象，保证实例唯一性
	        if(ld==null)
	        {
	        	ld = (new DictList()).getDictList();
	        }
	        return ld;
	    }
	    //4.重新加载字典
	    public static List<Dict>  regetInstance(String ox)
	    {
	    	 
	    	//ld=null;
	    	ld = (new DictList()).getDictList();
	    	return ld;
	         
	    }
 
}