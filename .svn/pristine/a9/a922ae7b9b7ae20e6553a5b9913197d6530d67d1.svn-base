package com.zhichao.admin.controller.oefullLawDoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.oefulllawdoc.IOefullLawdocService;
import com.zhichao.beans.guns.OefullLawdoc;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.LawDocMapper;
import com.zhichao.dal.mapper.OefullLawdocMapper;

import net.sf.json.JSONObject;

/**
 * 文书模板与业务数据关联表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-23 15:58:32
 */
@Controller
@RequestMapping("/oefullLawdoc")
public class OefullLawdocController extends BaseController {

    private String PREFIX = "/oefulllawdoc/oefullLawdoc/";

    @Autowired
    private IOefullLawdocService oefullLawdocService;
    @Autowired
    OefullLawdocMapper om;
    @Autowired
    LawDocMapper lm;
    
    
    /**
     * 跳转到文书模板与业务数据关联表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "oefullLawdoc.html";
    }

    /**
     * 跳转到添加文书模板与业务数据关联表
     */
    @RequestMapping("/oefullLawdoc_add")
    public String oefullLawdocAdd() {
        return PREFIX + "oefullLawdoc_add.html";
    }

    /**
     * 跳转到修改文书模板与业务数据关联表
     */
    @RequestMapping("/oefullLawdoc_update/{oefullLawdocId}")
    public String oefullLawdocUpdate(@PathVariable Integer oefullLawdocId, Model model) {
        OefullLawdoc oefullLawdoc = oefullLawdocService.selectById(oefullLawdocId);
        
        
        model.addAttribute("item",oefullLawdoc);
        LogObjectHolder.me().set(oefullLawdoc);
        return PREFIX + "oefullLawdoc_edit.html";
    }

    /**
     * 获取文书模板与业务数据关联表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String ldtype,String procstatus,Integer id) {
        return om.selectOefullInfo(ldtype, procstatus, null);
    }

    /**
     * 新增文书模板与业务数据关联表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OefullLawdoc oefullLawdoc) {
        oefullLawdocService.insert(oefullLawdoc);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除文书模板与业务数据关联表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer oefullLawdocId) {
        oefullLawdocService.deleteById(oefullLawdocId);
        return SUCCESS_TIP;
    }

    /**
     * 修改文书模板与业务数据关联表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OefullLawdoc oefullLawdoc) {
         oefullLawdocService.updateById(oefullLawdoc);
        
        
        return super.SUCCESS_TIP;
    }
    /**
     * 根据文书模板与业务数据关联表生成文书数据和文书word
     */
    @RequestMapping(value = "/addLawdocFromOefull")
    @ResponseBody
    public Object addLawdocFromOefull(Integer oefullId,String ldtype) {
    	String fileurl="";
    	Integer ldid= lm.getLdId(ldtype);
    	OefullLawdoc oefullLawdoc=om.getOefullLawdoc(oefullId, ldtype);
    	if(null==oefullLawdoc){
    	oefullLawdoc=new OefullLawdoc();
    	oefullLawdoc.setLdtype(ldtype);
    	oefullLawdoc.setLawdocid(ldid);
    	oefullLawdoc.setOefullid(oefullId);
    	}
    	 
    	List<Map<String, String>> list =  om.getoefullinfo(oefullId);
		if(list.size()==1){
    	Map<String,String> ldjson=(Map<String, String>)list.get(0);
		JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
		oefullLawdoc.setLawdocjson(ldjsonobj.toString());
		oefullLawdoc.setProcstatus("1");
		try {
			fileurl= oefullLawdocService.createOefullLawdoc(oefullLawdoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		}
		 return fileurl;
    }
    
    /**
     * 根据文书模板与业务数据关联表生成文书数据和文书word
     */
    @RequestMapping(value = "/addnewOefullLawdoc/{oefullLawdocId}")
    @ResponseBody
    public Object addnewOefullLawdoc(@PathVariable Integer oefullLawdocId) {
      
    	OefullLawdoc oefullLawdoc =oefullLawdocService.selectById(oefullLawdocId);
    	
    	List<Map<String, String>> list =  om.getoefullinfo(oefullLawdoc.getOefullid());
		if(list.size()==1){
			Map<String,String> ldjson=(Map<String, String>)list.get(0);
			JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
			Map<String, String> testMap = new HashMap<String, String>();
			 
		 
			testMap.put("drivername", "张三");
			testMap.put("sex", "男");
			testMap.put("year", "2018");
			testMap.put("month", "01");
			testMap.put("day", "22");
			testMap.put("age", "29");
			testMap.put("casesource", "路政巡查发现");
			testMap.put("driveraddress", "苏仙区塘溪乡塘溪村xx组");
			testMap.put("lawpersontel", "0731-8888888");
			testMap.put("drivertel", "0732-66666666");
			testMap.put("driverpersonid", "430124199909092456");
			testMap.put("lawpersonname", "李四");
			testMap.put("lawpersonaddress", "苏仙区塘溪乡");
			testMap.put("hour", "23");
			testMap.put("minute", "55");
			testMap.put("vechicleid", "湘Lxxxxx");
			testMap.put("axlesum", "3");
			testMap.put("cargoname", "铅锌矿");
			testMap.put("roadname", "S322线");
			testMap.put("enforcername1", "王五");
			testMap.put("enforcername2", "赵六");
			testMap.put("fctoalweight", "43.19");
			testMap.put("overlimited", "13.19");
			testMap.put("overlimitedrate", "43.9");

		 
			
			oefullLawdoc.setLawdocjson(ldjsonobj.toString());
			oefullLawdoc.setProcstatus("1");
			JSONObject inputjsonobj= JSONObject.fromObject(testMap);
			oefullLawdoc.setInputjson(inputjsonobj.toString());
			 
		}
 
    	String fileurl="";
		try {
			fileurl = oefullLawdocService.createOefullLawdoc(oefullLawdoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return fileurl;
    }
    
    
    /**
     * 根据文书模板与业务数据关联表重新生成历史数据的文书word
     */
    @RequestMapping(value = "/getOefullLawdoc/{oefullLawdocId}")
    @ResponseBody
    public Object getOefullLawdoc(@PathVariable Integer oefullLawdocId) {
      
    	OefullLawdoc oefullLawdoc =oefullLawdocService.selectById(oefullLawdocId);
 
    	String fileurl="";
		try {
			fileurl = oefullLawdocService.getOefullLawdoc(oefullLawdoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return fileurl;
    }
    
   
    /**
     * 文书模板与业务数据关联表详情
     */
    @RequestMapping(value = "/detail/{oefullLawdocId}")
    @ResponseBody
    public Object detail(@PathVariable("oefullLawdocId") Integer oefullLawdocId) {
        return oefullLawdocService.selectById(oefullLawdocId);
    }
}
