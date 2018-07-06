package com.zhichao.admin.controller.siteRegistration;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.common.util.YUtil;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.core.util.DictUtil;
import com.zhichao.service.core.util.FileCopy;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.siteRegistration.IFixedsiteService;
import com.zhichao.service.siteRegistration.IUnloadingService;
import com.zhichao.beans.guns.Fixedsite;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.common.util.ToolUtil;
import com.zhichao.dal.siteRegistration.FixedsiteDao;

import javax.annotation.Resource;

/**
 * 固定治超站控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 08:48:59
 */
@Controller
@RequestMapping("/fixedsite")	
public class FixedsiteController extends BaseController {

    private String PREFIX = "/siteRegistration/fixedsite/";

    @Autowired
    private IFixedsiteService fixedsiteService;
    
    @Resource
    private FixedsiteDao fixedsiteDao;
    
    @Autowired
	private IBsImageEntityService imageEntityService;
    
    @Autowired
    private IUnloadingService unloadingService;

    /**
     * 跳转到固定治超站首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "fixedsite.html";
    }

    /**
     * 跳转到添加固定治超站
     */
    @RequestMapping("/fixedsite_add")
    public String fixedsiteAdd() {
        return PREFIX + "fixedsite_add.html";
    }

    /**
     * 跳转到修改固定治超站
     */
    @RequestMapping("/fixedsite_update/{fixedsiteId}")
    public String fixedsiteUpdate(@PathVariable Integer fixedsiteId, Model model) {
        Fixedsite fixedsite = fixedsiteService.selectById(fixedsiteId);
        model.addAttribute("item",fixedsite);
        LogObjectHolder.me().set(fixedsite);
        return PREFIX + "fixedsite_edit.html";
    }

    /**
     * 获取固定治超站列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Map<String, Object>> list(@RequestParam(value="areacode", required=false) String areacode,
                                          @RequestParam(value="sitename", required=false) String sitename,
                                          @RequestParam(value="manager", required=false) String manager,
                                          @RequestParam(value="managertel", required=false) String managertel,
                                          @RequestParam(value="roadcode", required=false) String roadcode) {
        //同步检定证书
        FileCopy.synchronizationCertificate();
        //净化几个条件(删首尾空)
        areacode = YUtil.isNullOrEmptyReturnString(areacode,true);
        sitename = YUtil.isNullOrEmptyReturnString(sitename,true);
        manager = YUtil.isNullOrEmptyReturnString(manager,true);
        managertel = YUtil.isNullOrEmptyReturnString(managertel,true);
        roadcode = YUtil.isNullOrEmptyReturnString(roadcode,true);
        String userDeptid = String.valueOf(ShiroKit.getUser().getDeptId());

    	return fixedsiteDao.selList(userDeptid,areacode, sitename, manager, managertel,roadcode);
    	
    }

    /**
     * 新增固定治超站
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Fixedsite fixedsite, 
    		@RequestParam(value = "img_id", required = false) Integer imgId,
                      @RequestParam(value = "imagetype", required = true) String imageType,
                      @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        //设置文件名
        String fileName = file.getOriginalFilename();

        //获取到存储路径
        String fileSavePath = ParaUtil.getParaValue("certificate_url");
        //将文件存储到指定路径
        String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, fileName);
        //将文件路径存入对象
        fixedsite.setCertificateUrl(certificateUrl);

    	//标记基本信息是否存储成功
    	boolean falg = fixedsiteService.insert(fixedsite);
    	if (falg) {//成功则将图和站点信息相关联
    		imageEntityService.insertImageEntity(imgId, fixedsite.getId(), imageType);
            //同步检定证书
            FileCopy.synchronizationCertificate();
    		return super.SUCCESS_TIP;
    	} else {
 			return super.ERROR;
    	}
        
    }

    /**
     * 删除固定治超站
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer fixedsiteId) {
        fixedsiteService.deleteById(fixedsiteId);
        return SUCCESS_TIP;
    }

    /**
     * 修改固定治超站
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Fixedsite fixedsite,
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = true) String imageType,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if(!ToolUtil.isEmpty(file)) {
            //获取到存储路径
            String fileSavePath = ParaUtil.getParaValue("certificate_url");
            //将文件存储到指定路径
            String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, file.getOriginalFilename());
            //将文件路径存入对象
            fixedsite.setCertificateUrl(certificateUrl);
        }

        boolean flag = fixedsiteService.updateById(fixedsite);
        if (flag) {
            imageEntityService.updateEntityImage(imgId, fixedsite.getId(), imageType);
            //同步检定证书
            FileCopy.synchronizationCertificate();
            return super.SUCCESS_TIP;
        } else {
            return super.ERROR;
        }

        
    }

    /**
     * 固定治超站详情
     */
    @RequestMapping(value = "/detail/{fixedsiteId}")
    @ResponseBody
    public Object detail(@PathVariable("fixedsiteId") Integer fixedsiteId) {
        return fixedsiteService.selectById(fixedsiteId);
    }
    
    /**
     * 获取治超站等级列表用于动态显示在下拉框
     */
    @RequestMapping(value = "/getSiteLevelList")
    @ResponseBody
    public Object getSiteLevelList() {
    	
    	return DictUtil.selectListByColname("sitelevel");
    	
    }
    
    /**
     * 获取卸货场信息列表
     * @return
     */
    @RequestMapping(value = "/getUnloadingList")
    @ResponseBody
    public Object getUnloadingList() {
    	
    	return unloadingService.selectList(null);
    }
}
