package com.zhichao.admin.controller.presite;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.core.util.FileCopy;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.presite.IPresiteService;
import com.zhichao.beans.guns.Presite;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.mapper.AreaMapper;
import com.zhichao.dal.mapper.PresiteMapper;
import com.zhichao.dal.mapper.RoadMapper;

/**
 * 预检站信息控制器
 *
 * @author fengshuonan
 * @Date 2018-01-03 16:20:35
 */
@Controller
@RequestMapping("/presite")
public class PresiteController extends BaseController {

    private String PREFIX = "/presite/presite/";

    @Autowired
    private IPresiteService presiteService;
    @Autowired
    private PresiteMapper pm;
    @Autowired
    private AreaMapper am  ;
    @Autowired
    private RoadMapper rm;
    @Autowired
	private IBsImageEntityService imageEntityService;
    /**
     * 跳转到预检站信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "presite.html";
    }

    /**
     * 跳转到添加预检站信息
     */
    @RequestMapping("/presite_add")
    public String presiteAdd() {
        return PREFIX + "presite_add.html";
    }

    /**
     * 跳转到修改预检站信息
     */
    @RequestMapping("/presite_update/{presiteId}")
    public String presiteUpdate(@PathVariable Integer presiteId, Model model) {
//        Presite presite = presiteService.selectById(presiteId);
//        model.addAttribute("item",presite);
//        LogObjectHolder.me().set(presite);

        String userDeptid = String.valueOf(ShiroKit.getUser().getDeptId());
    	List<Map<String, Object>> fli= pm.list(userDeptid, null,null,presiteId);
    	if(fli.size()==1){
    		 model.addAttribute("item",fli.get(0));
    	}else {
          Presite presite = presiteService.selectById(presiteId);
          model.addAttribute("item",presite);
          LogObjectHolder.me().set(presite);
          return PREFIX + "presite_edit.html";
    		
    	}
      //  List<Map<String, Object>> rli= rm.listSelect( null,null);
       // List<Map<String, Object>> ali= am.listSelect( null,null);
      //  model.addAttribute("ali",DictUtil.getArea(ali));
       // model.addAttribute("rli",DictUtil.getAreaRoad(rli));
        
        //model.addAttribute("dicts",DictUtil.getOptionByColname("sitelevel"));
        return PREFIX + "presite_edit.html";
    }

    /**
     * 获取预检站信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String sitename,String areacode) {
        //同步检定证书
        FileCopy.synchronizationCertificate();

        String userDeptid = String.valueOf(ShiroKit.getUser().getDeptId());

  	List<Map<String, Object>> fli= pm.list(userDeptid, sitename,areacode,null);
        
     	return fli;
    }


    /**
     * 新增预检站信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Presite presite ,
                      @RequestParam(value = "img_id", required = false) Integer imgId,
                      @RequestParam(value = "imagetype", required = true) String imageType,
                      @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        //获取到存储路径
        String fileSavePath = ParaUtil.getParaValue("certificate_url");
        //将文件存储到指定路径
        String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, file.getOriginalFilename());
        //将文件路径存入对象
        presite.setCertificateUrl(certificateUrl);

        PubUtil pub= new PubUtil();
    	Map<String,Object> map=pub.getSequence("sitecode","","0","sitecode","bs_presite");


    	if(map.containsKey("ERROR")){
    		return map;
    	}else if(map.containsKey("sequence")){
    		presite.setSitecode(map.get("sequence").toString());
        	if(pub.isExistsSeqFromTableForUpdate("bs_presite", "sitecode", map.get("sequence").toString(),presite.getId())){

        		return "系统中已经存在相同编码的记录，编码为："+map.get("sequence").toString()+",请检查编码生成规则";
        	}
    	}else {
    		return super.ERROR;
    	}

        boolean falg =  presiteService.insert(presite);
        //存储成功
        if (falg) {
			imageEntityService.insertImageEntity(imgId, presite.getId(), imageType);
		}

        //同步检定证书
        FileCopy.synchronizationCertificate();

        return super.SUCCESS_TIP;
    }

    /**
     * 删除预检站信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer presiteId) {
        presiteService.deleteById(presiteId);
        return SUCCESS_TIP;
    }

    /**
     * 修改预检站信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Presite presite,
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = true) String imageType,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if(ToolUtil.isNotEmpty(file)) {
            //获取到存储路径
            String fileSavePath = ParaUtil.getParaValue("certificate_url");
            //将文件存储到指定路径
            String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, file.getOriginalFilename());
            //将文件路径存入对象
            presite.setCertificateUrl(certificateUrl);
        }
    	if (presite != null) {
    		
        	String sitecode=presite.getSitecode();
        	if((new PubUtil()).isExistsSeqFromTableForUpdate("bs_presite", "sitecode", sitecode,presite.getId())){
        		
        		return "系统中已经存在相同编码的记录，编码为："+presite.getRoadcode();
        	}
    		boolean flag =  presiteService.updateById(presite);
    		
    		if (flag) {
				imageEntityService.updateEntityImage(imgId, presite.getId(), imageType);
			}
		}

		//同步检定证书
        FileCopy.synchronizationCertificate();
        return super.SUCCESS_TIP;
    }

    /**
     * 预检站信息详情
     */
    @RequestMapping(value = "/detail/{presiteId}")
    @ResponseBody
    public Object detail(@PathVariable("presiteId") Integer presiteId) {
        return presiteService.selectById(presiteId);
    }
}
