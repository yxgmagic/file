package com.zhichao.service.core.util;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhichao.beans.guns.Dict;
 
 
/**
 *  
 *
 * @author zqf
 * @Date 2018-12-29 08:18:21
 */
public class DictUtil {
	 protected final static Logger logger = LoggerFactory.getLogger(DictUtil.class);
    public static List<Dict> dl =DictList.getInstance();
 
    public static void reflashDictList(String ox){
    	dl= DictList.regetInstance(ox);
    }
    
    /**
     * 改写原文，根据主键返回单一字典对象，或一条记录
     */
    public static Dict selectById(Integer dictId){
    	 Dict dict =null;
    	 for (Dict item : dl) {
             if (item. getId() != null && item. getId().equals(dictId)) {
            	 return item;
             }
         }
    	 
    	 return dict;
    }
    
    /**
     * 改写原文，name代表字典名称，返回ID作为下一次查询的PID
     */
    public static Integer selectIdByName(String name){
   
   	 for (Dict item : dl) {
            if (item.getName() != null && item.getName().equals(name)) {
           	 return item.getId();
            }
        }
   	 
   	 return -1;
   }
    
    /**
     * 
     */
    public static String selectNameByEnameNum(String pname,String num){
    	   
      	 for (Dict item : dl) {
               if (item.getPname() != null && item.getPname().equals(pname)&&item.getNum() != null && item.getNum().equals(num)) {
              	 return item.getName();
               }
           }
      	 
      	 return "0";
      }
    
    /**
     * 根据PID返回字典列表
     */
    public static List<Dict> selectListByPid(Integer pid){
    	List<Dict> li =new ArrayList();
    
	   	 for (Dict item : dl) {
	            if (item.getPid() != null && item.getPid().equals(pid)) {
	           	   li.add(item);
	           	 
	            }
	        }
    
   	 return li;
   }
    /**根据列名返回字典列表
     * 
     */
    public static List<Dict> selectListByColname(String pname){
    	List<Dict> li =new ArrayList();
    	 
   	 for (Dict item : dl) {
            if (item.getPname() != null && item.getPname().equals(pname)) {
           	   li.add(item);
            }
        }
   	 
   	 return li;
   }
    /**根据列名返回字典列表生成下拉框选项
     * <option value="1">是</option>
     */
    public static String getOptionByColname(String pname){
    	String  str="";
    	 
   	 for (Dict item : dl) {
            if (item.getPname() != null && item.getPname().equals(pname)&&item.getPid()!=0) {
           	    str+="<option value=\""+item.getNum()+"\">"+item.getName()+"</option> \n";
            }
        }
   	 
   	 return str;
   }
   

    /**
     * 根据父ID和取值返回字典名称
     */
    public static String getDictsByPidNum(Integer pid, String num) {
    	 for (Dict item : dl) {
             if (item.getPid() != null && item.getPid().equals(pid)&&item.getNum() != null && item.getNum().equals(num)) {
            	 return item.getName();
             }
         }
    	 return "";
    }
    
//    public  static JSONArray getArea(List<Map<String, Object>> ali){
//   	 JSONArray json = new JSONArray();
//   	 try{
//   	        
//   	     
//   	        for(int i=0; i<ali.size();i++)
//   	        {
//   	        	JSONObject jo = new JSONObject();
//   	        	 jo.put("areacode", ali.get(i).get("areacode"));
//   	        	 jo.put("areaname", ali.get(i).get("areaname"));
//   	            
//   	             json.put(jo);
//   	        	
//   	        }
//   	       
//   	        }catch(Exception e){
//   	        	e.printStackTrace();
//   	        }
//  	 
//  	 return json;
//  }
//    
//    
//    /**返回行政区域路网json
//     *  
//     */
//    public static  JSONArray getAreaRoad(List<Map<String, Object>> rli){
//    	  JSONArray rlijson = new JSONArray();
//    	 try{
//    		 
//    	        for(int i=0; i<rli.size();i++)
//    	        {
//    	        	JSONObject jo = new JSONObject();
//    	        	 jo.put("areacode", rli.get(i).get("areacode"));
//    	        	 jo.put("roadcode", rli.get(i).get("roadcode"));
//    	        	 jo.put("roadname", rli.get(i).get("roadname"));
//    	        	 rlijson.put(jo);
//    	        	
//    	        }
//    	        }catch(Exception e){
//    	        	
//    	        }
//   	 
//   	 return rlijson;
//   }
    
}