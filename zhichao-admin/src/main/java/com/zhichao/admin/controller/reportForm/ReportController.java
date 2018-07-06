package com.zhichao.admin.controller.reportForm;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhichao.dal.mapper.DeptMapper;
import com.zhichao.dal.mapper.UserMapper;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.beans.guns.Dept;
import com.zhichao.beans.guns.User;
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
@RequestMapping("/reportForm")
public class ReportController extends BaseController {

    private String PREFIX = "/reportForm/templates/";

 
    
    @Autowired
    private DruidProperties druidProperties;
    @Resource
    private UserMapper userMapper;
    @Resource
    DeptMapper deptMapper;

    
    @RequestMapping(value = "/jasperReport", produces = "application/pdf")
    @ResponseBody
    public Object jasperReport(String filename) throws SQLException {
 
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
    		 User user = this.userMapper.selectById(ShiroKit.getUser().getId());
     		parameters.put("deptid",user.getDeptid());
     		  Dept dept = deptMapper.selectById(user.getDeptid());
     		parameters.put("deptname",dept.getFullname());
    		
     		System.out.println("parameters==="+parameters);
    		bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters,connection);
    	} 
    	catch (JRException e) 
    	{
    		e.printStackTrace();
    	}      
    	if(null!=connection)connection.close();
    	return bytes;
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
        return PREFIX + "reportForm.html";
    }

   
}
