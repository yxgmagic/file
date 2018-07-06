package com.zhichao.admin.controller.reportForm;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.reportForm.ITemplatesService;
import com.zhichao.beans.guns.Templates;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.core.datasource.DruidProperties;

import cn.hutool.core.date.DateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * 自定义报表模块管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-14 09:05:55
 */
@Controller
@RequestMapping("/templates")
public class TemplatesController extends BaseController {

    private String PREFIX = "/reportForm/templates/";

    @Autowired
    private ITemplatesService templatesService;
    
    @Autowired
    private DruidProperties druidProperties;
    
    
    @RequestMapping(value = "/jasperReport2", produces = "application/pdf")
    @ResponseBody
    public Object jasperReport(String filename) throws SQLException {
    	System.out.println( "/jasperReport");
    	HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    	 Connection connection=null;
         DruidDataSource dataSource = new DruidDataSource();
         druidProperties.config(dataSource);
    	 connection =  dataSource.getConnection();
    	byte[] bytes = null;
    	try 
    	{
			String fileSavePath =ParaUtil.getParaValue("reportModelUpload");
			 fileSavePath=fileSavePath.replaceAll("/", "\\\\");
			 System.out.println("fileSavePath1===>"+fileSavePath);
			 File filemdk=new File(fileSavePath);  
		       if (!filemdk.exists()) {
		    	   if(!filemdk.mkdirs()){
		    	     fileSavePath= Class.class.getClass().getResource("/").getPath();
					 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
					 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
					 fileSavePath=fileSavePath.substring(1, fileSavePath.length())+"/reportModelUpload/";
				       File absulefilemdk=new File(fileSavePath);  
				       if (!absulefilemdk.exists()) {
				    	   absulefilemdk.mkdirs();
				       }
		    	   }
		       }
    		File reportFile = new File(fileSavePath+filename);
    		HashMap<String, Object> parameters = new HashMap<String, Object>();//给报表模板文件传参

    		//得到枚举类型的参数名称，参数名称若有重复的只能得到第一个--获取页面传来的参数，和模板中文件的sql参数名称一一对应
    		Enumeration<?> temp = req.getParameterNames();
    		while (temp.hasMoreElements()) 
    		{
    			String paramName = (String) temp.nextElement();
    			String paramValue = req.getParameter(paramName);
    			 
    			parameters.put(paramName, paramValue);
    		}
    		parameters.put("userid",ShiroKit.getUser().getId());
    		parameters.put("username",ShiroKit.getUser().getName());
    		parameters.put("deptid",ShiroKit.getUser().getDeptId());
    		parameters.put("deptname",ShiroKit.getUser().getDeptName());
    		parameters.put("today", DateUtil.today());
    		
    		bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters,connection);
    	} 
    	catch (JRException e) 
    	{
    		e.printStackTrace();
    	}      
    	if(null!=connection)connection.close();
    	return bytes;
    }
    
    
    @RequestMapping(method = RequestMethod.POST, path = "/reportUpload")
	@ResponseBody
	public Object upload(@RequestPart("file") MultipartFile file,@RequestPart("rfcode") String rfcode,@RequestPart("rfname") String rfname,@RequestPart("rfdesc") String rfdesc,@RequestPart("id") String id) {
		String fileName =  file.getOriginalFilename();
		try {
			String fileSavePath =ParaUtil.getParaValue("reportModelUpload");
			 fileSavePath=fileSavePath.replaceAll("/", "\\\\");
			 File filemdk=new File(fileSavePath);  
		       if (!filemdk.exists()) {
		    	   if(!filemdk.mkdirs()){
		    	     fileSavePath= Class.class.getClass().getResource("/").getPath();
					 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
					 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
					 fileSavePath=fileSavePath.substring(1, fileSavePath.length())+"/reportModelUpload/";
				       File absulefilemdk=new File(fileSavePath);  
				       if (!absulefilemdk.exists()) {
				    	   absulefilemdk.mkdirs();
				       }
		    	   }
		       }

		       	
					
			 
			file.transferTo(new File(fileSavePath + fileName));
 
 
			return msgMap(200, ( fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return msgMap(400, "上传文件异常！"+e.getMessage());
		}

	}
	public Map<String, Object> msgMap(Integer code, Object obj) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("code", code);
		msg.put("msg", obj);
		return msg;
	}
    /**
     * 跳转到自定义报表模块管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "templates.html";
    }

    /**
     * 跳转到添加自定义报表模块管理
     */
    @RequestMapping("/templates_add")
    public String templatesAdd() {
        return PREFIX + "templates_add.html";
    }

    /**
     * 跳转到修改自定义报表模块管理
     */
    @RequestMapping("/templates_update/{templatesId}")
    public String templatesUpdate(@PathVariable Integer templatesId, Model model) {
        Templates templates = templatesService.selectById(templatesId);
        model.addAttribute("item",templates);
        LogObjectHolder.me().set(templates);
        return PREFIX + "templates_edit.html";
    }

    /**
     * 获取自定义报表模块管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return templatesService.selectList(null);
    }

    /**
     * 新增自定义报表模块管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Templates templates) {
        templatesService.insert(templates);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除自定义报表模块管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer templatesId) {
        templatesService.deleteById(templatesId);
        return SUCCESS_TIP;
    }

    /**
     * 修改自定义报表模块管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Templates templates) {
        templatesService.updateById(templates);
        return super.SUCCESS_TIP;
    }

    /**
     * 自定义报表模块管理详情
     */
    @RequestMapping(value = "/detail/{templatesId}")
    @ResponseBody
    public Object detail(@PathVariable("templatesId") Integer templatesId) {
        return templatesService.selectById(templatesId);
    }
}
