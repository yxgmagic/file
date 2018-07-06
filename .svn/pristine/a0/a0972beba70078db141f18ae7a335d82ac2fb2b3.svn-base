package com.zhichao.admin.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.dal.mapper.SequenceMapper;
import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.PubUtil;
import com.zhichao.service.system.ISequenceService;
import com.zhichao.beans.guns.Sequence;
import com.zhichao.core.base.controller.BaseController;

/**
 * 编码规则生成器控制器
 *
 * @author zqf
 * @Date 2018-01-09 08:18:21
 */
@Controller
@RequestMapping("/sequence")
public class SequenceController extends BaseController {
	private String PREFIX = "/system/sequence/";

	@Autowired
	private ISequenceService sequenceService;
	@Autowired
	private SequenceMapper sm;

 
	/**
	 * 跳转到编码规则生成器首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "sequence.html";
	}

	/**
	 * 跳转到添加编码规则生成器
	 */
	@RequestMapping("/sequence_add")
	public String sequenceAdd() {
		return PREFIX + "sequence_add.html";
	}

	/**
	 * 跳转到修改编码规则生成器
	 */
	@RequestMapping("/sequence_update/{sequenceId}")
	public String sequenceUpdate(@PathVariable Integer sequenceId, Model model) {
		Sequence sequence = sequenceService.selectById(sequenceId);
		model.addAttribute("item",sequence);
		LogObjectHolder.me().set(sequence);
		return PREFIX + "sequence_edit.html";
	}
	@RequestMapping("/getseq")
	@ResponseBody
	public Object getseq( String seqtype,  String seqdept, String roadnum, String seqcol, String seqtab) {
		PubUtil pub= new PubUtil();
    	Map<String,Object> map=pub.getSequence(seqtype,seqdept,roadnum,seqcol,seqtab);
     
    	return map;
	}
	/**
	 * 获取编码规则生成器列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String seqname,String seqtype,String seqcol,String seqtab,String seqdept) {
		return sm.selectAll(seqname, seqtype, seqcol, seqtab, seqdept);
		//return sequenceService.selectList(null);
	}

	/**
	 * 新增编码规则生成器
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Sequence sequence) {
		sequenceService.insert(sequence);
		return super.SUCCESS_TIP;
	}

	/**
	 * 删除编码规则生成器
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer sequenceId) {
		sequenceService.deleteById(sequenceId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改编码规则生成器
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Sequence sequence) {
		sequenceService.updateById(sequence);
		return super.SUCCESS_TIP;
	}

	/**
	 * 编码规则生成器详情
	 */
	@RequestMapping(value = "/detail/{sequenceId}")
	@ResponseBody
	public Object detail(@PathVariable("sequenceId") Integer sequenceId) {
		return sequenceService.selectById(sequenceId);
	}
	 
	
	
 
	
}
