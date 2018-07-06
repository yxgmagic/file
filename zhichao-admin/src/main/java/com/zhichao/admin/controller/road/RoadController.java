
package com.zhichao.admin.controller.road;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.dal.mapper.AreaMapper;
import com.zhichao.dal.mapper.RoadMapper;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.beans.node.ZTreeNodeEntity;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.road.IRoadService;
import com.zhichao.beans.guns.Road;
import com.zhichao.core.base.controller.BaseController;

/**
 * 路网信息控制器
 *
 * @author fengshuonan
 * @Date 2018-01-03 15:51:32
 */
@Controller
@RequestMapping("/road")
public class RoadController extends BaseController {

    private String PREFIX = "/road/road/";

    @Autowired
    private IRoadService roadService;
    @Autowired
    private RoadMapper rm;
    @Autowired
    private AreaMapper am  ;
    /**
     * 跳转到路网信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "road.html";
    }

    /**
     * 跳转到添加路网信息
     */
    @RequestMapping("/road_add")
    public String roadAdd(Model model) {
        PubUtil pub = new PubUtil();
        Map<String, Object> resMap = pub.getSequence("roadcode", "", 0, "roadcode", "bs_road");
        model.addAttribute("resMap", resMap);
        return PREFIX + "road_add.html";
    }

    /**
     * 跳转到修改路网信息
     */
    @RequestMapping("/road_update/{roadId}")
    public String roadUpdate(@PathVariable Integer roadId, Model model) {
        Road road = roadService.selectById(roadId);
        model.addAttribute("item",road);
        LogObjectHolder.me().set(road);
//        List<Map<String, Object>> ali= am.listSelect( null,null);
//        model.addAttribute("ali",DictUtil.getArea(ali));
        return PREFIX + "road_edit.html";
    }

    /**
     * 获取路网信息列表
     */
    /**
     * 获取路网信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String roadname,String areacode) {
    	List<Map<String, Object>> rli= rm.list( roadname,areacode);
       // return roadService.selectList(null);
    	return rli;
    }

    /**
     * 新增路网信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Road road) {
    	PubUtil pub= new PubUtil();
    	Map<String,Object> map=pub.getSequence("roadcode","","0","roadcode","bs_road");
     
    	if(map.containsKey("ERROR")){
    		return map;
    	}else if(map.containsKey("sequence")){
    		road.setRoadcode(map.get("sequence").toString());
        	if(pub.isExistsSeqFromTableForUpdate("bs_road", "roadcode", map.get("sequence").toString(),road.getId())){
        		
        		return "系统中已经存在相同编码的记录，编码为："+map.get("sequence").toString()+",请检查编码生成规则";
        	}
    	}else {
    		return super.ERROR;
    	}
   
        roadService.insert(road);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除路网信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer roadId) {
        roadService.deleteById(roadId);
        return SUCCESS_TIP;
    }

    /**
     * 修改路网信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Road road) {
    	String roadcode=road.getRoadcode();
    	if((new PubUtil()).isExistsSeqFromTableForUpdate("bs_road", "roadcode", roadcode,road.getId())){
    		
    		return "系统中已经存在相同编码的记录，编码为："+road.getRoadcode();
    	}
        roadService.updateById(road);
        return super.SUCCESS_TIP;
    }

    /**
     * 路网信息详情
     */
    @RequestMapping(value = "/detail/{roadId}")
    @ResponseBody
    public Object detail(@PathVariable("roadId") Integer roadId) {
        return roadService.selectById(roadId);
    }
    
    /**返回行政区域路网json
     *  
     */
    @RequestMapping(value = "/roadlist")
    @ResponseBody
    public Object getRoadlist(String roadcode,String areacode){
    	
    	
    	return rm.listSelect( roadcode,areacode);
    }
    /**
     * 获取道路的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNodeEntity> tree() {
    	List<ZTreeNodeEntity> tree = this.rm.tree();
    	return tree;
    }
 
}
