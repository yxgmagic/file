package com.zhichao.admin.controller.lawEnforcement;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zhichao.common.constants.SystemConstants;
import com.zhichao.common.exception.BusinessException;
import com.zhichao.service.detecManage.ILscinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.service.common.constant.factory.PageFactory;
import com.zhichao.common.util.YUtil;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.ParaUtil;
import com.zhichao.service.lawEnforcement.IOefullinfoService;
import com.zhichao.dal.siteRegistration.FixedsiteDao;
import com.zhichao.beans.guns.Dict;
import com.zhichao.beans.guns.Fixedsite;
import com.zhichao.beans.guns.Lscinfo;
import com.zhichao.beans.guns.Oefullinfo;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.BOseinfoMapper;
import com.zhichao.dal.mapper.BsOsesiteMapper;
import com.zhichao.dal.mapper.LscinfoMapper;
import com.zhichao.dal.mapper.VehicleMapper;

/**
 * 立案办理控制器
 *
 * @author fengshuonan
 * @Date 2018-01-24 18:56:30
 */
@Controller
@RequestMapping("/oefullinfo")
public class OefullinfoController extends BaseController {
	//oefullinfo/oefullinfo_info

    private String PREFIX = "/lawEnforcement/oefullinfo/";

    @Autowired
    private IOefullinfoService oefullinfoService;

    @Autowired
    private ILscinfoService lscService;

    /**
     * 跳转到立案办理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "oefullinfo.html";
    }

    /**
     * 跳转到添加立案办理
     */
    @RequestMapping("/oefullinfo_add")
    public String oefullinfoAdd() {
        return PREFIX + "oefullinfo_add.html";
    }

    /**
     * 跳转到修改立案办理
     * 根据id查询精检站，根据车牌号查询源头企业信息
     * @param id 精检站业务id
     */
    @RequestMapping("/oefullinfo_update/{id}")
    public String oefullinfoUpdate(@PathVariable(value = "id") Integer id,
                                   Model model) {
        Oefullinfo oefullinfo = oefullinfoService.findOefullInfoByLscId(id, SystemConstants.Case.TURNDOWN);
        // 驳回案件
        if (null != oefullinfo){
            model.addAttribute("oefullinfo", oefullinfo);
            return PREFIX + "oefullinfo_turndown.html";
        }

        // 精检站初次立案
        Map<String, Object> lscAndFixInfo = lscService.findLscAndFixInfo(id);
        model.addAttribute("lscAndFixInfo", lscAndFixInfo);
        return PREFIX + "oefullinfo_lsc.html";
    }

    /**
     * 获取精检站与非现场数据列表
     * @param fctime 案件办理时间区间
     * @param vehicleid 车牌号
     * @param caseno 案件号
     * @param prostatus 案件状态
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "casetime", required = false) String fctime,
                       @RequestParam(value = "vehicleid", required = false) String vehicleid,
                       @RequestParam(value = "caseno", required = false) String caseno,
                       @RequestParam(value = "prostatus", required = false) Integer prostatus) {

        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> result = oefullinfoService.selOefullList(page, fctime, vehicleid, caseno, prostatus);

        page.setRecords(result);
        return super.packForBT(page);
    }

    /**
     * version 2.0
     * 获取所有执法文书列表
     * @return
     */
    @RequestMapping("/getAllLawdocTypeList")
    public @ResponseBody List<Map<String, Object>> getAllLawdocTypeList() {
        return oefullinfoService.getAllLawdocTypeList();
    }

    /**
     * 立案
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Integer add(Oefullinfo oefullinfo) throws BusinessException {

        //向违章表插入数据
        oefullinfoService.insertPendingApproval(oefullinfo);

        return oefullinfo.getId();
    }

    /**
     * 重新发起立案请求(驳回后)
     */
    @RequestMapping(value = "/reFiling")
    public @ResponseBody Object reFiling(Oefullinfo oefullinfo) throws BusinessException {

        //更新违章表中的业务数据和状态
        oefullinfoService.updateOefullinfoAndprostatus(oefullinfo);

        return SUCCESS_TIP;
    }

    /**
     * version 2.0
     * 创建并下载文书
     * @param oeid 违章表id
     * @param ldtypes 文书列表
     * @param req req
     * @return
     */
    @RequestMapping("/createAndDownloadLawdoc")
    public ResponseEntity<FileSystemResource> createAndDownloadLawdoc(@RequestParam("oeid") Integer oeid,
                                                                     @RequestParam("ldtypes[]") Integer[] ldtypes,
                                                                     HttpServletRequest req) throws Exception {
        return oefullinfoService.createAndDownloadLawdoc(oeid,ldtypes,req);
    }

    /**----------------------------------------------附件------------------------------------------------------------------------*/


    /**
     * 上传附件
     * @param oeid 违章表id
     * @param file 附件
     * @return
     */
    @PostMapping("uploadAnnex")
    public @ResponseBody Object uploadAnnex(@RequestParam("oeid") Integer oeid,
                                            @RequestParam("file") MultipartFile file) throws FileNotFoundException {
        return oefullinfoService.uploadAnnex(oeid, file);
    }

    /**
     *根据违章表主键找到所有的附件
     * @param oeid 违章表主键
     * @return
     */
    @PostMapping("getAllAnnexByOeid/{oeid}")
    public @ResponseBody Object getAllAnnexByOeid(@PathVariable("oeid") Integer oeid){
        return oefullinfoService.getAllAnnexByOeid(oeid);
    }

    @PostMapping("delAnnex/{id}")
    public @ResponseBody Object delAnnex(@PathVariable("id") Integer id){
        oefullinfoService.delAnnex(id);
        return SUCCESS_TIP;
    }

    @GetMapping("downloadAnnex/{id}")
    public ResponseEntity<FileSystemResource> downloadAnnex(@PathVariable("id") Integer id) throws UnsupportedEncodingException {
        return oefullinfoService.downloadAnnex(id);
    }

    /**----------------------------------------------附件------------------------------------------------------------------------*/


    /**
     * 公共方法
     * 根据参数表的key获取参数表的value
     * @param key key
     * @return
     */
    @PostMapping("getParaValue/{key}")
    public @ResponseBody Object getParaValue(@PathVariable("key") String key){
        return ParaUtil.getParaValue(key);
    }


    /**
     * 公共方法
     * 获取执法人员列表
     * @return
     */
    @PostMapping("getLawEnforceMan")
    public @ResponseBody Object getLawEnforceMan(){
        return oefullinfoService.getLawEnforceMan();
    }











    /**----------------------------------------------页面跳转------------------------------------------------------------------------*/

    /**
     * 跳转到选择暂扣单号
     * @return
     */
    @RequestMapping("/selwithholdno")
    public String selwithholdno(){
        return PREFIX + "cargoHandling.html";
    }

    /**
     * 跳转到附件页面
     * @return
     */
    @RequestMapping("/annexPage")
    public String annexPage(){
        return PREFIX + "annex.html";
    }

    /**
     * 跳转到文书页面
     * @return
     */
    @RequestMapping("/lawdocPage")
    public String lawdocPage(){
        return PREFIX + "lawdoc.html";
    }


    /**
     * 跳转到短信发送页面
     * @return
     */
    @RequestMapping("sendMsgPage")
    public String slendMsgPage(){
        return PREFIX + "sendMsg.html";
    }
    /**
     * 跳转到短信发送页面
     * @return
     */
    @RequestMapping("carImages")
    public String carImages(){
        return PREFIX + "car_images.html";
    }
    /**----------------------------------------------页面跳转------------------------------------------------------------------------*/



    /**
     * version 1.0
     * 获取执法文书类型列表
     * @param oeid 违章表主键
     * @return
     */
    @RequestMapping("/getIdTypeList/{oeid}")
    public @ResponseBody List<Dict> getIdTypeList(@PathVariable(value = "oeid") Integer oeid) {
        List<Dict> idtypeList = oefullinfoService.selIdTypeList(oeid);
        return idtypeList;
    }

    /**
     * version 1.0
     * 获取下载文书列表
     * @param oeid
     * @return
     */
    @PostMapping("/getOefullLawdocList")
    public @ResponseBody List<Map<String, Object>> getOefullLawdocList(@RequestParam(value = "oefullLawdocId") Integer oeid){

        return oefullinfoService.getOefullLawdocList(oeid);
    }

    /**
     * version 1.0
     * 根据检查单号查询违章表中的主键id
     * @param checkno 检测单号
     * @return
     */
    @PostMapping("/getOeidByCheckno")
    public @ResponseBody Integer getOeidByCheckno(@RequestParam("checkno") String checkno){
        return oefullinfoService.getOefullinfoIdByCheckno(checkno);
    }

    /**
     * 删除立案
     */
    @Deprecated
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer oefullinfoId) {
        oefullinfoService.deleteById(oefullinfoId);
        return SUCCESS_TIP;
    }

    /**
     * 立案办理详情
     */
    @Deprecated
    @RequestMapping(value = "/detail/{oefullinfoId}")
    @ResponseBody
    public Object detail(@PathVariable("oefullinfoId") Integer oefullinfoId) {
        return oefullinfoService.selectById(oefullinfoId);
    }
    /**
     * 跳转到案件信息<br/>
     * 这个可能会要调整,暂时只有精检站(固定治超站)和非现场的
     */
    @RequestMapping("/oefullinfo_info/{oefullinfoId}/{caseType}")
    public String oefullinfoInfo(@PathVariable Integer oefullinfoId, @PathVariable String caseType, Model model) {
    	Map<String,Object> map = oefullinfoService.findOefullInfoBrowse(oefullinfoId, null, caseType) ;
    	model.addAttribute("item", map);
        LogObjectHolder.me().set(map);
        return PREFIX + "oefullinfo_info.html";
    }
}
