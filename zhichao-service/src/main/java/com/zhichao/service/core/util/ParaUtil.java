package com.zhichao.service.core.util;


import java.util.List;
import java.util.Map;

import com.zhichao.dal.mapper.ParaMapper;
import com.zhichao.core.util.SpringContextHolder;

public class ParaUtil {
   
	 private    ParaUtil(){}
	  private   List<Map<String, String>> getParaList()
	    {
		  ParaMapper paraMapper = SpringContextHolder.getBean(ParaMapper.class);
		  List<Map<String, String>> list =paraMapper.listPara();
		
		  return  list;
	    }
	    
	   private  static List<Map<String, String>> ld ;
	    
	    //3.提供一个用于获取实例的方法,使用public static
	    public static List<Map<String, String>> getInstance()
	    {
	        //第一个人来的时候提示没有实例程序就创建一个实例，第二个人来的时候一判断程序有实力对象了就不在创建实例对象，保证实例唯一性
	        if(ld==null)
	        {
	        	ld = (new ParaUtil()).getParaList();
	        }
	        return ld;
	    }
	    //4.重新加载参数表
	    public static List<Map<String, String>>  regetInstance(String ox)
	    {
	    	 
	    	 
	    	ld = (new ParaUtil()).getParaList();
	    	return ld;
	         
	    }
	    public static String getParaValue(String paracode){
	    	String paraValue="";
	    	if(ld==null||ld.size()==0)
	    	{
	    		ld = (new ParaUtil()).getParaList();
	    	} 
	    		 
	    		for(int i=0;i<ld.size();i++){
	    			if(paracode.equals(ld.get(i).get("paracode"))){
	    				paraValue= ld.get(i).get("paravalue");
	    				break;
	    			}

	    		}
	    	 
	    	return paraValue;
	    }
 
}