package com.zhichao.admin.controller.common;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zhichao.beans.guns.VideoServer;
import com.zhichao.beans.node.ZTreeNodeEntity;
import com.zhichao.core.base.controller.BaseController;
import com.zhichao.core.util.MD5Util;
import com.zhichao.dal.mapper.VideoDeviceMapper;
import com.zhichao.dal.mapper.VideoServerMapper;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/comm")
public class CommController extends BaseController {
	
	private String PREFIX = "/video/";
	
	@Resource 
	private VideoServerMapper videoServerDao;

	@Resource
	private VideoDeviceMapper videoDeviceDao;
	
	@RequestMapping("/video/{id}")
	public String video(@PathVariable Integer id,Model model){
		String title="";
		String index="";
		String url="video.html";
		
		if (id == 1) {
			title = "实时视频";
			index = "" + id;
			url="video.html";
		}else if (id == 5) {
			title = "历史视频";
			index = "" + id;
			url="video.html";
		}else if (id == 22) {
			title = "设备管理";
			index = "" + id;
		}else if (id == 25) {
			title = "组织管理";
			index = "" + id;
		}else{
			model.addAttribute("title",title);
			model.addAttribute("uname", "");
			model.addAttribute("upass", "");
			model.addAttribute("address", "");
			model.addAttribute("port", "");
			model.addAttribute("index", "");
			return PREFIX + url;
		}
		
		VideoServer entity= videoServerDao.selectById(1);
		String uname="admin",upass="12345",address="192.168.20.12",port="3000";
		if(entity!=null){
			uname=entity.getUname();
			upass=entity.getUpass();
			address=entity.getServerip();
			port=entity.getServerport();
		}
		
		model.addAttribute("title",title);
		model.addAttribute("uname", uname);
		model.addAttribute("upass", upass);
		model.addAttribute("address", address);
		model.addAttribute("port", port);
		model.addAttribute("index", index);
		
		return PREFIX + url;
	}
	
	@RequestMapping("/show")
	public String videoShow(@RequestParam(required = true) String title,@RequestParam(required = true) int idx,@RequestParam(required = true) int flag,@RequestParam(required = true) int ptz,@RequestParam(required = true) int site,@RequestParam(required = true) String code,Model model) throws UnsupportedEncodingException{
		VideoServer entity= videoServerDao.selectById(1);
		String uname="admin",upass="12345",address="192.168.20.12",port="3000";
		title= java.net.URLDecoder.decode(title,"UTF-8");
		code=java.net.URLDecoder.decode(code,"UTF-8");
		if(entity!=null){
			uname=entity.getUname();
			upass=entity.getUpass();
			address=entity.getServerip();
			port=entity.getServerport();
		}
		
		model.addAttribute("uname", uname);
		model.addAttribute("upass", upass);
		model.addAttribute("address", address);
		model.addAttribute("port", port);
		model.addAttribute("flag", flag);
		model.addAttribute("ptz", ptz);
		model.addAttribute("site", site);
		model.addAttribute("title",title);
		model.addAttribute("index",idx);
		
		List<String> list=new ArrayList<String>();
		
		
		List<Map<String, Object>> vl = videoServerDao.getVideoList(site, code);

		for (Map<String, Object> obj : vl) {
			list.add(obj.getOrDefault("deviceId", "").toString());
		}
		
		model.addAttribute("code",list);
		model.addAttribute("count",list.size());
		
		return PREFIX + "videoShow.html";
	}
	
	@RequestMapping("/play")
	public String videoPlay(@RequestParam(required = true) String title,@RequestParam(required = true) int flag,@RequestParam(required = true) String st,@RequestParam(required = true) String et,@RequestParam(required = true) int site,@RequestParam(required = true) String code,Model model) throws UnsupportedEncodingException{
		VideoServer entity= videoServerDao.selectById(1);
		String uname="admin",upass="12345",address="192.168.20.12",port="3000";
		title= java.net.URLDecoder.decode(title,"UTF-8");
		code=java.net.URLDecoder.decode(code,"UTF-8");
		if(entity!=null){
			uname=entity.getUname();
			upass=entity.getUpass();
			address=entity.getServerip();
			port=entity.getServerport();
		}
		
		model.addAttribute("uname", uname);
		model.addAttribute("upass", upass);
		model.addAttribute("address", address);
		model.addAttribute("port", port);
		model.addAttribute("flag", flag);
		
		model.addAttribute("site", site);
		model.addAttribute("title",title);
		
		model.addAttribute("st",st);
		model.addAttribute("et",et);

		List<String> list=new ArrayList<String>();
		
		List<Map<String, Object>> vl = videoServerDao.findVideoList(code);

		for (Map<String, Object> obj : vl) {
			list.add(obj.getOrDefault("deviceId", "").toString());
		}
		
		model.addAttribute("code",list);
		model.addAttribute("count",list.size());
		
		return PREFIX + "videoPlay.html";
	}
	
	
	@RequestMapping("/showSiteVideo")
	public String showSiteVideo(@RequestParam(required = true) String title,@RequestParam(required = true) int idx
			,@RequestParam(required = true) int flag,@RequestParam(required = true) int ptz,@RequestParam(required = true) int site
			,@RequestParam(required = true) String code,Model model) throws UnsupportedEncodingException{
		VideoServer entity= videoServerDao.selectById(1);
		String uname="admin",upass="12345",address="192.168.20.12",port="3000";
		title= java.net.URLDecoder.decode(title,"UTF-8");
		code=java.net.URLDecoder.decode(code,"UTF-8");
		if(entity!=null){
			uname=entity.getUname();
			upass=entity.getUpass();
			address=entity.getServerip();
			port=entity.getServerport();
		}
		
		model.addAttribute("uname", uname);
		model.addAttribute("upass", upass);
		model.addAttribute("address", address);
		model.addAttribute("port", port);
		model.addAttribute("flag", flag);
		model.addAttribute("ptz", ptz);
		model.addAttribute("site", site);
		model.addAttribute("title",title);
		model.addAttribute("index",idx);
		
		List<String> list=new ArrayList<String>();
		
		code="HN0001";//测试用站点ID
		List<Map<String, Object>> vl = videoServerDao.findVideoList(code);

		for (Map<String, Object> obj : vl) {
			list.add(obj.getOrDefault("deviceId", "").toString());
		}
		
		model.addAttribute("code",list);
		model.addAttribute("count",list.size());
		
		return PREFIX + "videoShow.html";
	}
	
	
	@RequestMapping("/test")
	public String videoTest( Model model){
		model.addAttribute("title","admin");
		model.addAttribute("address", "192.168.10.110");
		
		return PREFIX + "WebOCX.html";
	}
	
	@RequestMapping("/set")
	public String videoSet( Model model){
		//model.addAttribute("title","admin");
		//model.addAttribute("address", "192.168.10.110");
		
		return PREFIX + "videoDevice.html";
	}
	
	/**
     * 获取视频设备列表
     */
	@RequestMapping(value = "/tree")
	@ResponseBody
    public List<ZTreeNodeEntity> tree() {
        List<ZTreeNodeEntity> tree = videoDeviceDao.deviceTree();
        
        ZTreeNodeEntity zTreeNode = new ZTreeNodeEntity();
		zTreeNode.setChecked(true);
		zTreeNode.setId("{979017CB-33A0-41EF-8C99-7C74B4E57D2F}");
		zTreeNode.setName("设备");
		zTreeNode.setOpen(true);
		zTreeNode.setpId("#");
        tree.add(0,zTreeNode);
        
        return tree;
    }
	
	/**
     * 获取视频设备列表
     */
	@RequestMapping(value = "/tree/{siteCode}")
	@ResponseBody
    public List<ZTreeNodeEntity> treeByCode(@PathVariable String siteCode) {
        List<ZTreeNodeEntity> tree = videoDeviceDao.deviceTreeBySiteCode(siteCode);
        return tree;
    }
	
	@RequestMapping(value = "/post")
	@ResponseBody
	public String postTest(@RequestBody String param){       
        com.alibaba.fastjson.JSONObject jo=new com.alibaba.fastjson.JSONObject();  
          
        //如果页面传的是json字符串，用下列方式解析  
		Map<String, Object> m = (Map<String, Object>) jo.parse(param); // string转map
		System.out.println(m);//
		com.alibaba.fastjson.JSONObject parseObject = jo.parseObject(param); // string转json
		System.out.println(parseObject);  
		
		String url="http://localhost:8012/auth";
		
		List<NameValuePair> params = Lists.newArrayList();  
        params.add(new BasicNameValuePair("userName", "admin"));  
        params.add(new BasicNameValuePair("password", "admin"));  
		
		return post(url,params);
	}
	
	public String post(String URL,List<NameValuePair> params) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        
        //post.setHeader("Content-Type", "application/json");
        //post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";
        
        try {
        	//System.out.println(json.toString());
            /*StringEntity s = new StringEntity(json.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
            post.setEntity(s);*/
        	
        	post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));  
        	
            // 发送请求
            HttpResponse httpResponse = client.execute(post);

			if (httpResponse != null) {

				// 获取响应输入流
				InputStream inStream = httpResponse.getEntity().getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
				StringBuilder strber = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null)
					strber.append(line + "\n");
				inStream.close();

				result = strber.toString();
				//System.out.println(result);

				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					 JSONObject obj =JSONObject.fromObject(result);
					 String randomKey =obj.getString("randomKey");
					 String token =obj.getString("token");
					 
					 
					 JSONObject o = new JSONObject();
					 o.put("pageIndex", 0);
					 o.put("pageSize", 10);
					 o.put("flag", 1);
					 
					 //String ttt=postR("http://localhost:8012/hello",obj,getE(obj));
					 result=postR("http://localhost:8012/hello/json",obj,getE(randomKey,o)).toString();
					 
					//System.out.println(ttt+obj.toString());
					//System.out.println("请求服务器成功，做相应处理");

				} else {

					System.out.println("请求服务端失败");

				}
			}

        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return result;
    }
	
	
	public Object postR(String URL,JSONObject json,String o) {
		
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        
        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+json.getString("token"));
        String result = "";
        
        try {
            StringEntity s = new StringEntity(o, "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

			if (httpResponse != null) {
				// 获取响应输入流
				InputStream inStream = httpResponse.getEntity().getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
				StringBuilder strber = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null)
					strber.append(line + "\n");
				inStream.close();

				result = strber.toString();
				//System.out.println(result);

				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					 //JSONObject obj =JSONObject.fromObject(result);
					 //String randomKey =obj.getString("randomKey");
					 //String token =obj.getString("token");
					return JSONObject.fromObject(result);
					
					//System.out.println(result);
					//System.out.println("请求服务器成功，做相应处理");

				} else {

					System.out.println("请求服务端失败");

				}
			}

        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return result;
    }
	
	private String getE(String randomKey,JSONObject json){
        String salt = randomKey;

        String jsonString = JSON.toJSONString(json);
        String encode = new Base64SecurityAction().doAction(jsonString);
        String md5 = MD5Util.encrypt(encode + salt);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));
		return JSON.toJSONString(baseTransferEntity);
	}
}
