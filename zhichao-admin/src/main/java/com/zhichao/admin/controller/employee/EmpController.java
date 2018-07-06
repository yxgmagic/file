package com.zhichao.admin.controller.employee;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.beans.employee.Emp;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.employee.EmpService;


/**
 * 员工信息控制器
 * @author yanxingui
 * @Date 2018-06-26 09:22
 */
@Controller
@RequestMapping("/employee")
public class EmpController extends BaseController {
	private String PREFIX = "/employee/";
	
	private static Logger logger = LoggerFactory.getLogger(EmpController.class);
	//通过使用@Value("${参数名}")符号来获取yml配置文件中值
	@Value(value="${upload.path}")
	public String path;
	
	
	@Autowired
	private EmpService empService;
	
	/**
	 * 跳转到员工管理首页
	 */
	@RequestMapping("")
	public String index() {
		logger.warn("路径的值: " + path);
		return PREFIX + "emp.html";
	}
	
	/**
	 * 跳转到添加员工
	 */
	@RequestMapping("emp_add")
	public String empAdd() {
		return PREFIX + "emp_add.html";
	}
	
	/**
	 * 跳转到修改员工
	 */
	@RequestMapping("/emp_update/{empno}")
	public String empUpdate(@PathVariable Integer empno,Model model) {
		System.out.println(empno);
		Emp emp = empService.selectById(empno);
		model.addAttribute("item", emp);
		LogObjectHolder.me().set(emp);
		return PREFIX + "emp_edit.html";
	}
	
	/**
	 * 获取员工列表
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public Object list(String condition) {
			return empService.selectListByEname(condition);
	}
	
	/**
	 * 新增员工
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Object add(Emp emp) {
		System.out.println(">>>>>>>>>获得页面的数据: "+emp);
		empService.insert(emp);
		return super.SUCCESS_TIP;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object update(@RequestParam Integer empno) {
		System.out.println("删除掉的empno:　" + empno);
		if(empno != null) {
			//  empService.selectById(empno);
			Emp emp = new Emp();
			emp.setStatus(EmpFinal.REMOVE);
			emp.setEmpno(empno);
			System.out.println("删除开始>>>>>>>>>>>>>>>>>>>>>>");
			empService.updateById(emp);
			System.out.println("删除结束>>>>>>>>>>>>>>>>>>>>>>");
		}
		return SUCCESS_TIP;
	}
	
	/**
	 * 修改员工信息
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Emp emp) {
		System.out.println(">>>>>>>>>修改之后的数据: " + emp);
		empService.updateById(emp);
		return super.SUCCESS_TIP;
	}
	
	/**
	 * 员工信息详情
	 */
	@RequestMapping(value = "/detail/{empno}")
	@ResponseBody
	public Object detail(@PathVariable("empno") Integer empno) {
		return empService.selectById(empno);
	}
    
    @RequestMapping(method = RequestMethod.POST, path = "/empUpload")
	@ResponseBody
	public Object upload(@RequestPart("file") MultipartFile file,
						 @RequestPart("ename") String ename,
						 @RequestPart("status") String status,
						 @RequestPart("id") String id) {
		String fileName = ename+"_" + status +"." + 
						 file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		try {
			//获得项目的绝对路径
			String url = ResourceUtils.getURL("classpath:").getPath();
			logger.info(">>>>>>绝对路径: " + url);
			//做了些修改,利用存储的方法进行存储,注意这里传进去的路径只需要相对路径,它会自动在项目的运行目录存储
			String fileSavePath =ParaUtil.getParaValue("empUpload");//查询系统参数看有没有对应的路径值
			if(fileSavePath == null || "".equals(fileSavePath)) {
				fileSavePath = path;
			}
			//将文件存储到指定路径
			String uploadUrl = FileCopy.saveFile(file.getInputStream(), fileSavePath, fileName);
			logger.info(">>>>>>>>>文件存储的路径>>>>>>>>>>>>" + uploadUrl);

//			if(null!=id&&!"".equals(id)&&!"-1".equals(id)){//代表修改
//				LawDoc lawDoc = lawDocService.selectById(id);
// 
//				LawDoc lawDocnew = new LawDoc();
//				PubUtil pub= new PubUtil();
//				Map<String,Object> map=pub.getSequence("ldcode","","0","ldcode","bs_law_doc");
//				if(map.containsKey("ERROR")){
//					return map;
//				}else if(map.containsKey("sequence")){
//					lawDocnew.setLdcode(map.get("sequence").toString());
//
//				} 
//				lawDocnew.setLdfileurl("static/modular/lawdoc/"+fileName);
//				lawDocnew.setLdname(ldname);
//				lawDocnew.setLdno(lm.getMaxLdno(lawDoc.getLdtype()));
//				lawDocnew.setLdstatus("1");
//				lawDocnew.setLdtype(lawDoc.getLdtype());
//				lawDocnew.setCrtuserid(ShiroKit.getUser().getId());
//				lawDocService.insert(lawDocnew);
//				lm.setLdStatus(lawDocnew.getLdtype(), lawDocnew.getId());
//				
//			}
			return msgMap(200, (path+ fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return msgMap(400, "上传文件异常！"+e.getMessage());
		}

	}
	
	/**
	 * 
	 * @author yanxingui
	 *
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/empUpload1")
	@ResponseBody
	public Object upload(@RequestPart("file") MultipartFile file) {
		String fileName = 123 + "_" +12+ "."+
						 file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		logger.info(">>>>>>>>>>>>>>>>>>" + file.getOriginalFilename());
		logger.info(fileName);
		try {
			//查询系统参数是否有对应的路径
			String fileSavePath = ParaUtil.getParaValue("empUpload");
			if(fileSavePath == null || "".equals(fileSavePath)) {
				fileSavePath = path;
			}
			//将文件存储到指定路径
			FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, fileName);
			return msgMap(200, fileSavePath+fileName) ;
		} catch (IOException e) {
			e.printStackTrace();
			return msgMap(400,"上传文件异常!" + e.getMessage());
		}
	}
	

	public Map<String, Object> msgMap(Integer code, Object obj) {
		Map<String, Object> msg = new HashMap<>();
		msg.put("code", code);
		msg.put("msg", obj);
		return msg;
	}
	
	interface EmpFinal{
		static final  Integer  ENABLE = 1;//启用
		static final Integer DISABLE = 2;//禁用
		static final Integer REMOVE = 3;//删除
	}
	
}
