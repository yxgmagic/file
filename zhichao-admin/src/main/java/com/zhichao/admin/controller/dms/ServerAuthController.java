package com.zhichao.admin.controller.dms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhichao.service.core.log.LogObjectHolder;
import com.zhichao.service.core.util.PubEncode;
import com.zhichao.service.dms.IServerAuthService;
import com.zhichao.beans.guns.ServerAuth;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.dal.mapper.ServerAuthMapper;
import com.zhichao.dal.mapper.ServerChainMapper;

import cn.hutool.core.date.DateUtil;
import net.sf.json.JSONObject;

/**
 * 数据服务注册控制器
 *
 * @author fengshuonan
 * @Date 2018-02-25 11:57:11
 */
@Controller
@RequestMapping("/serverAuth")
public class ServerAuthController extends BaseController {

    private String PREFIX = "/dms/serverAuth/";
    @Autowired
    private IServerAuthService serverAuthService;
    @Autowired
    private ServerAuthMapper sm;
    
    @Autowired
    private ServerChainMapper scm;

    /**
     * 跳转到数据服务注册首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "serverAuth.html";
    }

    /**
     * 跳转到添加数据服务注册
     */
    @RequestMapping("/serverAuth_add")
    public String serverAuthAdd() {
        return PREFIX + "serverAuth_add.html";
    }

    /**
     * 跳转到修改数据服务注册
     */
    @RequestMapping("/serverAuth_update/{serverAuthId}")
    public String serverAuthUpdate(@PathVariable Integer serverAuthId, Model model) {
        ServerAuth serverAuth = serverAuthService.selectById(serverAuthId);
        model.addAttribute("item",serverAuth);
        LogObjectHolder.me().set(serverAuth);
        return PREFIX + "serverAuth_edit.html";
    }

    /**
     * 获取数据服务注册列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return serverAuthService.selectList(null);
    }

    /**
     * 新增数据服务注册
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ServerAuth serverAuth) {
        serverAuthService.insert(serverAuth);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除数据服务注册
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer serverAuthId) {
        serverAuthService.deleteById(serverAuthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改数据服务注册
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ServerAuth serverAuth) {
        serverAuthService.updateById(serverAuth);
        return super.SUCCESS_TIP;
    }

    /**
     * 数据服务注册详情
     */
    @RequestMapping(value = "/detail/{serverAuthId}")
    @ResponseBody
    public Object detail(@PathVariable("serverAuthId") Integer serverAuthId) {
        return serverAuthService.selectById(serverAuthId);
    }
    @RequestMapping(value = "/severAuthLogin")
    @ResponseBody
    public Object severAuthLogin(
    		@RequestParam String alias,
    		@RequestParam String alias_name,
    		@RequestParam String url,
    		@RequestParam String port
    		,@RequestParam String computer_name 
    		,@RequestParam String diskno 
    		,@RequestParam String macno 
    		,@RequestParam String begindt 
    		,@RequestParam String enddt 
    		,@RequestParam String cdkey 
    		,@RequestParam String usercode 
    		,@RequestParam String userpassword,
    		@RequestParam Integer creatid ,
    		@RequestParam Integer deptid) {
    	if("".equals(diskno)||"00-00-00-00-00-00".equals(diskno)){return "存在异常，无法绑定服务，请检查网卡硬件！="+diskno;}
     
    	List<Map<String, Object>> fli= sm.list( alias,diskno,macno,null);
    	String today= DateUtil.today();
    	if(fli.size()==0){
    		ServerAuth sa = new ServerAuth();
    		sa.setAlias(alias);
    		sa.setAliasName(alias_name);
    		sa.setBegindt(begindt);
    		sa.setCdkey(cdkey);
    		sa.setComputerName(computer_name);
    		sa.setCreatdt(today);
    		sa.setCreatid(creatid);
    		sa.setDeptid(deptid);
    		sa.setDiskno(diskno);
    		sa.setEnddt(enddt);
    		sa.setMacno(macno);
    		sa.setUrl(url);
    		sa.setUsercode(usercode);
    		sa.setUserpassword(userpassword);
    		serverAuthService.insert(sa);
    		return "请先申请license注册数据交换服务，当前未注册无法登录！";
    	}
    	else if(fli.size()==1){
    		ServerAuth sa=serverAuthService.selectById(Integer.parseInt(fli.get(0).get("id").toString()));
    		if("0".equals(sa.getAuditStatus())){return "该数据服务暂时未通过审核，无法登录";}
    		if("0".equals(sa.getStatus())){return "该数据服务已经处于失效状态，无法登录";}
    		String privatekey=sm.getPrivatekey();
    		if(!checkLicense(today,sa,privatekey)){
    			return "当前时间:"+today+ "下，该数据服务的注册码有效期为"+sa.getBegindt()+"至"+sa.getEnddt()+"，已经无效，无法登录";
    		}
//握手协议，生成加密密钥对，保存公钥，派发私钥    	
    		try{
    			String str[]=makeLicense(sa.getCdkey());
    			cdkey=str[0];
    			sa.setCdkey(cdkey);
    			sm.updateCdkey(sa.getId(), cdkey);
    			Map<String,String> ldjson=new HashMap<String,String>();
    			ldjson.put("license", str[1]);
    			ldjson.put("id", String.valueOf(sa.getId()));
    			ldjson.put("status",sa.getStatus());
    			ldjson.put("auditStatus",sa.getAuditStatus());
    			JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
    			return ldjsonobj;
    		}catch(Exception e){
    			
    			return "握手协议，生成加密密钥对失败！"+e.getMessage();
    		}
    		  
    	 	  
    	}else {
    		 return "已存在相同数据交换服务，无法登录！";
    	}
        
    }
    public static boolean checkLicense(String today,ServerAuth sa,String privatekey){
    	String lincense =sa.getLicense();
    	if("".equals(lincense)) return false;
    	boolean check =false;
    	try{
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		Date dt=df.parse(today);
    		Date db=df.parse(sa.getBegindt());
    		Date de=df.parse(sa.getEnddt());
    		if(dt.getTime()<db.getTime()) return false;
    		if(dt.getTime()>de.getTime()) return false;
    		check= PubEncode.privateDecryptCheck(PubEncode.toByteArray(lincense), sa.getBegindt(), sa.getEnddt(), sa.getAlias(), sa.getDiskno(), sa.getMacno(),privatekey);
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    	return check;
    }
    
    public  static String[] makeLicense( String s ) throws Exception {
    	try{
    	  String str[]=PubEncode.PublicEnrypt(s);
    	  return str;
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e;
    	}
    	 
    }
    @RequestMapping(value = "/getSeverConfig")
    @ResponseBody
    public Object getSeverConfig(@RequestParam Integer authid){
    	Map<String,Object> serverAuth = new HashMap<String,Object>();
    	Map<String,Object> serverList = new HashMap<String,Object>();
    	Map<String,Object> actionList = new HashMap<String,Object>();
    	Map<String,Object> actparaList = new HashMap<String,Object>();
    	Map<String,Object> list_chain = new HashMap<String,Object>();
 
    	Map<String,Object> serverparaList = new HashMap<String,Object>();
    	String publickey=sm.getAuthPublickey(authid);
    	
    	
    	List<Map<String, Object>> list =null;
    	List<Map<String, Object>> list2 =null;
    	List<Map<String, Object>> list3 =null;
    	list =sm.list_server_list(authid);
    	try{
    	for(int i=0;i<list.size();i++){
    		String password = list.get(i).get("dbpassword").toString();
    		if(null!=password&&!"".equals(password)){
    			password=PubEncode.getPublicEnrypt(password, publickey);
    			Map<String, Object> server =list.get(i);
    			server.put("dbpassword", password);
    			list.set(i, server);
    		}
    		serverList.put("serverlistid"+list.get(i).get("serverlistid"), list.get(i));
    	}
    	}catch(Exception e){
    	
    }
    	list = sm.list_action_list(authid);
    	for(int i=0;i<list.size();i++){
    		actionList.put("actid"+list.get(i).get("actid"), list.get(i));
    	}
    	
    	list = sm.list_action_para(authid);
    	for(int i=0;i<list.size();i++){
    		actparaList.put("actionparaid"+list.get(i).get("actionparaid"), list.get(i));
    	}
    	list=sm.list_chain(authid);
    	for(int i=0;i<list.size();i++){
    		list_chain.put("list_chain"+i, list.get(i));
    	}
 
    	list=sm.list_sever_para(authid);
    	for(int i=0;i<list.size();i++){
    		serverparaList.put("serverparaList"+i, list.get(i));
    	}
    	
    	list = sm.list_server_auth_chain(authid);
    	list2 =sm.list_server_chain(authid);
    	list3 = sm.list_chain_action(authid);
    	Map<String,Object> authchainList = new HashMap<String,Object>();
    	for(int i=0;i<list.size();i++){
    		authchainList.put("serverauthchainid"+list.get(i).get("serverauthchainid"), list.get(i));
    	}
    	serverAuth.put("authchainList",authchainList);
    	
    	Map<String,Object> serverchainList = new HashMap<String,Object>();
    	for(int i=0;i<list2.size();i++){
    		serverchainList.put("serverchainid"+list2.get(i).get("serverchainid"), list2.get(i));
    	}
    	serverAuth.put("serverchainList",serverchainList);
    	
    	Map<String,Object> chainactionList = new HashMap<String,Object>();
    	for(int i=0;i<list3.size();i++){
    		chainactionList.put("chainactionid"+list3.get(i).get("chainactionid"), list3.get(i));
    	}
    	serverAuth.put("chainactionList",chainactionList);
    	serverAuth.put("serverparaList",serverparaList);
    	
//    	Map<String,Object> authchain = new HashMap<String,Object>();
//    	for(int i=0;i<list.size();i++){
//    		authchain.put("serverauthChain"+i, list.get(i));
//    		Map<String,Object> serverChain = new HashMap<String,Object>();
//    		for(int k=0;k<list2.size();k++){
//    			if(list.get(i).get("chainid").equals(list2.get(k).get("chainid"))){
//    				serverChain.put("serverChain"+k,list2.get(k));
//    				Map<String,Object> chainaction = new HashMap<String,Object>();
//    				for(int j=0;j<list3.size();j++){
//    					if(list2.get(k).get("serverchainid").equals(list3.get(j).get("serverchainid"))){
//    						chainaction.put("chainaction"+j ,list3.get(j));
//    					}
//    				}
//    				System.out.println("chainaction"+chainaction.size()+chainaction.toString());
//    				serverChain.put("chainactionList"+k,chainaction);
//    			}
//    		}
//    		System.out.println("serverChain"+serverChain.size()+serverChain.toString());
//    		authchain.put("serverChainList"+i, serverChain);
//    		
//    	}
//    	serverauthChain.put("authchainList", authchain);

    	
//    	serverAuth.putAll(serverauthChain);
    	serverAuth.put("serverList",serverList);
    	serverAuth.put("actionList",actionList);
    	serverAuth.put("actparaList",actparaList);
    	serverAuth.put("list_chain",list_chain);
    	 
    	JSONObject js= JSONObject.fromObject(serverAuth);
    	 
    	return js;
    }

	 
}
