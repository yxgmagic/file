package com.zhichao.admin.controller.lawDoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.core.util.FileCopy;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.lawdoc.ILawDocService;
import com.zhichao.beans.guns.LawDoc;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.LawDocMapper;

import javax.annotation.Resource;

/**
 * 执法文书控制器
 *
 * @author fengshuonan
 * @Date 2018-01-19 14:50:41
 */
@Controller
@RequestMapping("/lawDoc")
public class LawDocController extends BaseController {

	private String PREFIX = "/lawdoc/lawDoc/";

	@Autowired
	private ILawDocService lawDocService;
	@Resource
	private LawDocMapper lm;



	@RequestMapping(method = RequestMethod.POST, path = "/lawDocUpload")
	@ResponseBody
	public Object upload(@RequestPart("file") MultipartFile file,
						 @RequestPart("ldname") String ldname,
						 @RequestPart("ldtype") String ldtype,
						 @RequestPart("id") String id) {
		String fileName = ldname+"_"+lm.getMaxLdno(ldtype)+"." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		try {

			//做了些修改,利用存储的方法进行存储,注意这里传进去的路径只需要相对路径,它会自动在项目的运行目录存储
			String fileSavePath =ParaUtil.getParaValue("lawDocModelUpload");
			//将文件存储到指定路径
			FileCopy.saveFile(file.getInputStream(), fileSavePath, fileName);


			//原来的存储文件的代码\

			/*String fileSavePath =ParaUtil.getParaValue("lawDocModelUpload");
			 fileSavePath=fileSavePath.replaceAll("/", "\\\\");
			 System.out.println("fileSavePath1===>"+fileSavePath);
			 File filemdk=new File(fileSavePath);
		       if (!filemdk.exists()) {
		    	   if(!filemdk.mkdirs()){
		    	     fileSavePath= Class.class.getClass().getResource("/").getPath();
					 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
					 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
					 fileSavePath=fileSavePath.substring(1, fileSavePath.length())+"/lawDocModelUpload/";
				       File absulefilemdk=new File(fileSavePath);
				       if (!absulefilemdk.exists()) {
				    	   absulefilemdk.mkdirs();
				       }
		    	   }
		       }

		       	
					
		      System.out.println("fileSavePath2===>"+fileSavePath);

			file.transferTo(new File(fileSavePath + fileName));


			FileInputStream input=new FileInputStream(fileSavePath + fileName);
			fileSavePath=ParaUtil.getParaValue("oefullLawDocWordFile");
			File filesavemdk=new File(fileSavePath);
			if (!filesavemdk.exists()) {
				if(!filesavemdk.mkdirs()){
					fileSavePath = Class.class.getClass().getResource("/static/modular/lawdoc/").getPath();
					fileSavePath=fileSavePath.substring(1, fileSavePath.length());

					File absulefileSavePath=new File(fileSavePath);
					if (!absulefileSavePath.exists()) {
						absulefileSavePath.mkdirs();
					}
				}
			}
			FileOutputStream output=new FileOutputStream(fileSavePath + fileName);
			try{

				int in=input.read();
				while(in!=-1){
					output.write(in);
					in=input.read();
				}

			}catch (IOException e){
				System.out.println(e.toString());
			}finally{
				if(null!=output){
					output.close();
				}
				if(null!=input){
					input.close();
				}
			}*/
			 
			if(null!=id&&!"".equals(id)&&!"-1".equals(id)){//代表修改
				LawDoc lawDoc = lawDocService.selectById(id);
 
				LawDoc lawDocnew = new LawDoc();
				PubUtil pub= new PubUtil();
				Map<String,Object> map=pub.getSequence("ldcode","","0","ldcode","bs_law_doc");
				if(map.containsKey("ERROR")){
					return map;
				}else if(map.containsKey("sequence")){
					lawDocnew.setLdcode(map.get("sequence").toString());

				} 
				lawDocnew.setLdfileurl("static/modular/lawdoc/"+fileName);
				lawDocnew.setLdname(ldname);
				lawDocnew.setLdno(lm.getMaxLdno(lawDoc.getLdtype()));
				lawDocnew.setLdstatus("1");
				lawDocnew.setLdtype(lawDoc.getLdtype());
				lawDocnew.setCrtuserid(ShiroKit.getUser().getId());
				lawDocService.insert(lawDocnew);
				lm.setLdStatus(lawDocnew.getLdtype(), lawDocnew.getId());
				
			}
			return msgMap(200, ("static/modular/lawdoc/"+ fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return msgMap(400, "上传文件异常！"+e.getMessage());
		}

	}

	/**
	 * 跳转到执法文书首页
	 */
	@RequestMapping("")
	public String index() {
		lawDocCopy();
		return PREFIX + "lawDoc.html";
	}

	/**
	 * 跳转到添加执法文书
	 */
	@RequestMapping("/lawDoc_add")
	public String lawDocAdd() {
		return PREFIX + "lawDoc_add.html";
	}
	/**
	 * 同步执法文书
	 */
	@RequestMapping("/lawDocCopy")
	public void lawDocCopy() {

		FileCopy.synchronizationFile("lawDocModelUpload", "oefullLawDocWordFile");

		//原来的同步代码
//		 String fileSavePath =ParaUtil.getParaValue("lawDocModelUpload");
//		 fileSavePath=fileSavePath.replaceAll("/", "\\\\");
//		 File filemdk=new File(fileSavePath);
//	       if (!filemdk.exists()) {
//	    	   if(!filemdk.mkdirs()){
//	    	     fileSavePath= Class.class.getClass().getResource("/").getPath();
//				 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
//				 fileSavePath=fileSavePath.substring(0, fileSavePath.lastIndexOf("/"));
//				 fileSavePath=fileSavePath.substring(1, fileSavePath.length())+"/lawDocModelUpload/";
//			       File absulefilemdk=new File(fileSavePath);
//			       if (!absulefilemdk.exists()) {
//			    	   absulefilemdk.mkdirs();
//			       }
//	    	   }
//	       }
//
//
//
//	      String from =fileSavePath;
//	      fileSavePath=ParaUtil.getParaValue("oefullLawDocWordFile");
//			File filesavemdk=new File(fileSavePath);
//			if (!filesavemdk.exists()) {
//				if(!filesavemdk.mkdirs()){
//					fileSavePath = Class.class.getClass().getResource("/static/modular/lawdoc/").getPath();
//					fileSavePath=fileSavePath.substring(1, fileSavePath.length());
//
//					File absulefileSavePath=new File(fileSavePath);
//					if (!absulefileSavePath.exists()) {
//						absulefileSavePath.mkdirs();
//					}
//				}
//			}
//			 String to =fileSavePath;
//		 FileCopy.corpFiles(from, to);
   }
 
	/**
	 * 跳转到修改执法文书
	 */
	@RequestMapping("/lawDoc_update/{lawDocId}")
	public String lawDocUpdate(@PathVariable Integer lawDocId, Model model) {
		LawDoc lawDoc = lawDocService.selectById(lawDocId);
		model.addAttribute("item",lawDoc);
		LogObjectHolder.me().set(lawDoc);
		return PREFIX + "lawDoc_edit.html";
	}

	/**
	 * 获取执法文书列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String ldname) {


		List<Map<String, Object>> fli= lm.listLawDoc( ldname,null);

		return fli;
	}

	/**
	 * 新增执法文书
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(LawDoc lawDoc) {


		PubUtil pub= new PubUtil();
		Map<String,Object> map=pub.getSequence("ldcode","","0","ldcode","bs_law_doc");

		if(map.containsKey("ERROR")){
			return map;
		}else if(map.containsKey("sequence")){
			lawDoc.setLdcode(map.get("sequence").toString());
			if(pub.isExistsSeqFromTableForUpdate("bs_law_doc", "ldcode", map.get("sequence").toString(),lawDoc.getId())){

				return "系统中已经存在相同编码的记录，编码为："+lawDoc.getLdcode()+",请检查编码生成规则";
			}
		}else {
			return super.ERROR;
		}

		if(lawDoc.getId()==-1){lawDoc.setId(null);}
		lawDoc.setLdno(lm.getMaxLdno(lawDoc.getLdtype()));
		lawDoc.setCrtuserid(ShiroKit.getUser().getId());
		lawDocService.insert(lawDoc);
		if("1".equals(lawDoc.getLdstatus())){
			lm.setLdStatus(lawDoc.getLdtype(), lawDoc.getId());
		}
		return super.SUCCESS_TIP;
	}

	/**
	 * 删除执法文书
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer lawDocId) {
		lawDocService.deleteById(lawDocId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改执法文书
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(LawDoc lawDoc) {
		if("1".equals(lawDoc.getLdstatus())){
			lm.setLdStatus(lawDoc.getLdtype(), lawDoc.getId());
		}
		lawDocService.updateById(lawDoc);
		return super.SUCCESS_TIP;
	}

	/**
	 * 执法文书详情
	 */
	@RequestMapping(value = "/detail/{lawDocId}")
	@ResponseBody
	public Object detail(@PathVariable("lawDocId") Integer lawDocId) {
		return lawDocService.selectById(lawDocId);
	}
	public Map<String, Object> msgMap(Integer code, Object obj) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("code", code);
		msg.put("msg", obj);
		return msg;
	}
}
