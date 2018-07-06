package com.zhichao.admin.controller.siteRegistration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhichao.service.common.constant.factory.ConstantFactory;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.FileCopy;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.siteRegistration.ILawEnforcecarService;
import com.zhichao.beans.guns.LawEnforcecar;
import com.zhichao.beans.guns.Road;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.LawEnforcecarMapper;
import com.zhichao.dal.mapper.RoadMapper;
import com.zhichao.dal.system.DeptDao;


/**
 * 执法车信息控制器
 *
 * @author fengshuonan
 * @Date 2018-01-03 11:31:45
 */
@Controller
@RequestMapping("/lawEnforcecar")
public class LawEnforcecarController extends BaseController {

	private String PREFIX = "/siteRegistration/lawEnforcecar/";

	@Autowired
	private ILawEnforcecarService lawEnforcecarService;

	@Resource
	private LawEnforcecarMapper lawEnforcecarDao;

	@Resource
	private DeptDao deptDao;

	@Autowired
	private IBsImageEntityService imageEntityService;
	
	@Resource
	private RoadMapper roadDao;

	/**
	 * 跳转到执法车信息首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "lawEnforcecar.html";
	}

	/**
	 * 跳转到添加执法车信息
	 */
	@RequestMapping("/lawEnforcecar_add")
	public String lawEnforcecarAdd() {
		return PREFIX + "lawEnforcecar_add.html";
	}

	/**
	 * 跳转到修改执法车信息
	 */
	@RequestMapping("/lawEnforcecar_update/{lawEnforcecarId}")
	public String lawEnforcecarUpdate(@PathVariable Integer lawEnforcecarId, Model model) {
		LawEnforcecar lawEnforcecar = lawEnforcecarService.selectById(lawEnforcecarId);
		model.addAttribute("item",lawEnforcecar);
		model.addAttribute("pName",ConstantFactory.me().getDeptName(lawEnforcecar.getDeptid()));
		model.addAttribute("cName",ConstantFactory.me().getCarTypeName(lawEnforcecar.getVehicletype().toString()));
		model.addAttribute("aName",ConstantFactory.me().getAreaName(lawEnforcecar.getAreacode()));

		model.addAttribute("roadList", getList());
		
		LogObjectHolder.me().set(lawEnforcecar);
		return PREFIX + "lawEnforcecar_edit.html";
	}

	@RequestMapping(value = "/getList")
	@ResponseBody
	public List<Road> getList(){
		return roadDao.selectList(null);
	}
	
	/**
	 * 获取执法车信息列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String deptName,@RequestParam(required = false) String carNumber) {
		List<Map<String, Object>> list = lawEnforcecarDao.findList(deptName, carNumber);

		return list;
	}

	/**
	 * 新增执法车信息
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(LawEnforcecar lawEnforcecar,
			@RequestParam(value = "img_id", required = false) Integer imgId,
			@RequestParam(value = "imagetype", required = true) String imageType,
					  @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

		//获取到存储路径
		String fileSavePath = ParaUtil.getParaValue("certificate_url");
		//将文件存储到指定路径
		String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, file.getOriginalFilename());
		//将文件路径存入对象
		lawEnforcecar.setCertificateUrl(certificateUrl);
		//同步检定证书
		FileCopy.synchronizationCertificate();

		if(lawEnforcecar !=null){
			EntityWrapper<LawEnforcecar> ep=new EntityWrapper<>();
			Wrapper<LawEnforcecar> wrapper = ep.eq("vehicleid", lawEnforcecar.getVehicleid());

			int count =lawEnforcecarService.selectCount(wrapper);
			
			if(count>0){
				return "{\"flag\":false,\"msg\":\"执法车辆牌号已添加,请重新输入!\"}";
				
				//ErrorTip err=new ErrorTip(100,"dd");
				//return err;
			}
			
			ep=new EntityWrapper<>();
			wrapper = ep.eq("vehicleno", lawEnforcecar.getVehicleno());

			count =lawEnforcecarService.selectCount(wrapper);
			
			System.out.println(count+"");
			
			if(count>0){
				return "{\"flag\":false,\"msg\":\"车辆编号已添加,请重新输入!\"}";
				
				//ErrorTip err=new ErrorTip(100,"dd");
				//return err;
			}
			
			boolean flag = lawEnforcecarService.insert(lawEnforcecar);

			//存储成功
			if (flag) {
				imageEntityService.insertImageEntity(imgId, lawEnforcecar.getId(), imageType);
			}
		}
		
		return "{\"flag\":true,\"msg\":\"添加成功!\"}";
		//return super.SUCCESS_TIP;
	}

	/**
	 * 删除执法车信息
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer lawEnforcecarId) {
		lawEnforcecarService.deleteById(lawEnforcecarId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改执法车信息
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(LawEnforcecar lawEnforcecar,
			@RequestParam(value = "img_id", required = false) Integer imgId,
			@RequestParam(value = "imagetype", required = true) String imageType,
						 @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		
		if(lawEnforcecar !=null){

			if(ToolUtil.isNotEmpty(file)) {
				//获取到存储路径
				String fileSavePath = ParaUtil.getParaValue("certificate_url");
				//将文件存储到指定路径
				String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, file.getOriginalFilename());
				//将文件路径存入对象
				lawEnforcecar.setCertificateUrl(certificateUrl);
			}
			//同步检定证书
			FileCopy.synchronizationCertificate();

			EntityWrapper<LawEnforcecar> ep=new EntityWrapper<>();
			Wrapper<LawEnforcecar> wrapper = ep.eq("vehicleid", lawEnforcecar.getVehicleid());

			int count =lawEnforcecarService.selectCount(wrapper);
			
			if(count>0){
				LawEnforcecar lawEnforcecar_old = lawEnforcecarService.selectById(lawEnforcecar.getId());
				lawEnforcecar.setVehicleid(lawEnforcecar_old.getVehicleid());
			}
			
			wrapper = ep.eq("vehicleno", lawEnforcecar.getVehicleno());

			count =lawEnforcecarService.selectCount(wrapper);
			
			if(count>0){
				LawEnforcecar lawEnforcecar_old = lawEnforcecarService.selectById(lawEnforcecar.getId());
				lawEnforcecar.setVehicleno(lawEnforcecar_old.getVehicleno());
			}
			
			boolean flag= lawEnforcecarService.updateById(lawEnforcecar);
			//存储成功
			if (flag) {
				imageEntityService.insertImageEntity(imgId, lawEnforcecar.getId(), imageType);
			}
		}
		return super.SUCCESS_TIP;
	}

	/**
	 * 执法车信息详情
	 */
	@RequestMapping(value = "/detail/{lawEnforcecarId}")
	@ResponseBody
	public Object detail(@PathVariable("lawEnforcecarId") Integer lawEnforcecarId) {
		return lawEnforcecarService.selectById(lawEnforcecarId);
	}
}
