package com.zhichao.admin.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.dal.mapper.CartypeMapper;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.system.ICartypeService;
import com.zhichao.beans.guns.Cartype;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.beans.node.ZTreeNode;

/**
 * 车型分类信息控制器
 *
 * @author fengshuonan
 * @Date 2018-01-09 11:14:05
 */
@Controller
@RequestMapping("/cartype")
public class CartypeController extends BaseController {

    private String PREFIX = "/system/cartype/";

    @Autowired
    private ICartypeService cartypeService;
    
    @Resource
    private CartypeMapper carDao;

    /**
     * 跳转到车型分类信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cartype.html";
    }

    /**
     * 跳转到添加车型分类信息
     */
    @RequestMapping("/cartype_add")
    public String cartypeAdd() {
        return PREFIX + "cartype_add.html";
    }

    /**
     * 跳转到修改车型分类信息
     */
    @RequestMapping("/cartype_update/{cartypeId}")
    public String cartypeUpdate(@PathVariable Integer cartypeId, Model model) {
        Cartype cartype = cartypeService.selectById(cartypeId);
        model.addAttribute("item",cartype);
        LogObjectHolder.me().set(cartype);
        return PREFIX + "cartype_edit.html";
    }

    /**
     * 获取车型分类信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return cartypeService.selectList(null);
    }

    /**
     * 新增车型分类信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Cartype cartype) {
        cartypeService.insert(cartype);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除车型分类信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cartypeId) {
        cartypeService.deleteById(cartypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改车型分类信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Cartype cartype) {
        cartypeService.updateById(cartype);
        return super.SUCCESS_TIP;
    }

    /**
     * 车型分类信息详情
     */
    @RequestMapping(value = "/detail/{cartypeId}")
    @ResponseBody
    public Object detail(@PathVariable("cartypeId") Integer cartypeId) {
        return cartypeService.selectById(cartypeId);
    }
    
    /**
     * 获取车型的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.carDao.tree();
        return tree;
    }
}
