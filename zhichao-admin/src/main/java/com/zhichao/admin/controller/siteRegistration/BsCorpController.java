package com.zhichao.admin.controller.siteRegistration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.FileCopy;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.service.siteRegistration.IBsCorpService;
import com.zhichao.beans.guns.BsCorp;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.common.util.ToolUtil;

/**
 * 源头企业管理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 10:39:41
 */
@Controller
@RequestMapping("/bsCorp")
public class BsCorpController extends BaseController {

    private String PREFIX = "/siteRegistration/bsCorp/";

    @Autowired
    private IBsCorpService bsCorpService;
    @Autowired
	private IBsImageEntityService imageEntityService;
    

    /**
     * 跳转到源头企业管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bsCorp.html";
    }

    /**
     * 跳转到添加源头企业管理
     */
    @RequestMapping("/bsCorp_add")
    public String bsCorpAdd() {
        return PREFIX + "bsCorp_add.html";
    }

    /**
     * 跳转到修改源头企业管理
     */
    @RequestMapping("/bsCorp_update/{bsCorpId}")
    public String bsCorpUpdate(@PathVariable Integer bsCorpId, Model model) {
        BsCorp bsCorp = bsCorpService.selectById(bsCorpId);
        model.addAttribute("item",bsCorp);
        LogObjectHolder.me().set(bsCorp);
        return PREFIX + "bsCorp_edit.html";
    }

    /**
     * 获取源头企业管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    		@RequestParam(value = "corpname", required = false) String corpname, 
    		@RequestParam(value = "areacode", required = false) String areacode) {

        return bsCorpService.queryCorpByCondition(corpname, areacode);
    }

    /**
     * 新增源头企业管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(
    		BsCorp bsCorp, 
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = false) String imageType,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        //获取到存储路径
        String fileSavePath = ParaUtil.getParaValue("certificate_url");
        //将文件存储到指定路径
        String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, file.getOriginalFilename());
        //将文件路径存入对象
        bsCorp.setCertificateUrl(certificateUrl);
        //同步检定证书
        FileCopy.synchronizationCertificate();
        boolean flag = bsCorpService.insert(bsCorp);
        //存储成功
        if (flag) {
			imageEntityService.insertImageEntity(imgId, bsCorp.getId(), imageType);
		}
        return SUCCESS_TIP;
    }

    /**
     * 删除源头企业管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bsCorpId) {
        bsCorpService.deleteById(bsCorpId);
        return SUCCESS_TIP;
    }

    /**
     * 修改源头企业管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(
    		BsCorp bsCorp,
    		@RequestParam(value = "img_id", required = false) Integer imgId,
    		@RequestParam(value = "imagetype", required = true) String imageType,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
    	
    	if (bsCorp != null) {
            if(ToolUtil.isNotEmpty(file)) {
                //获取到存储路径
                String fileSavePath = ParaUtil.getParaValue("certificate_url");
                //将文件存储到指定路径
                String certificateUrl = FileCopy.SaveFileFromInputStream(file.getInputStream(), fileSavePath, file.getOriginalFilename());
                //将文件路径存入对象
                bsCorp.setCertificateUrl(certificateUrl);
            }
            //同步检定证书
            FileCopy.synchronizationCertificate();
    		boolean flag = bsCorpService.updateById(bsCorp);
    		
    		if (flag) {
				imageEntityService.updateEntityImage(imgId, bsCorp.getId(), imageType);
			}
		}
        return SUCCESS_TIP;
    }

    /**
     * 源头企业管理详情
     */
    @RequestMapping(value = "/detail/{bsCorpId}")
    @ResponseBody
    public Object detail(@PathVariable("bsCorpId") Integer bsCorpId) {
        return bsCorpService.selectById(bsCorpId);
    }
    
    /**
     * 营业执照唯一性校验
     */
    @RequestMapping(value = "/corpIsExist")
    @ResponseBody
    public Object corpIsExist(
    		@RequestParam(value = "corpcode", required = false) String corpcode,
    		@RequestParam(value = "id", required = false) Integer id,
    		@RequestParam(value = "managerid", required = false) String managerid) {
    	
    	return bsCorpService.corpIsExist(corpcode,id,managerid);
    }
    
}
