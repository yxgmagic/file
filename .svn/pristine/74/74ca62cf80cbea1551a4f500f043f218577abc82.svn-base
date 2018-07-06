package com.zhichao.service.oefulllawdoc.impl;

import java.io.File;
import java.util.Map;

import com.zhichao.service.core.util.FileCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.core.util.WordUtils;
import com.zhichao.service.oefulllawdoc.IOefullLawdocService;
import com.zhichao.beans.guns.LawDoc;
import com.zhichao.beans.guns.OefullLawdoc;
import com.zhichao.dal.mapper.LawDocMapper;
import com.zhichao.dal.mapper.OefullLawdocMapper;
import com.zhichao.service.core.util.ParaUtil;

import net.sf.json.JSONObject;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zqf
 * @since 2018-01-23
 */
@Service
public class OefullLawdocServiceImpl extends ServiceImpl<OefullLawdocMapper, OefullLawdoc> implements IOefullLawdocService {
	@Autowired
	LawDocMapper lm;
	@Autowired
	OefullLawdocMapper om;
	@Override
	public String getOefullLawdoc(OefullLawdoc oefullLawdoc) throws Exception {
		Map<String, String> resultMap =(Map<String, String> )JSONObject.fromObject(oefullLawdoc.getLawdocjson());
		resultMap.putAll((Map<String, String> )JSONObject.fromObject(oefullLawdoc.getInputjson()));
//		LawDoc lawdoc = lm.selectById(oefullLawdoc.getLawdocid());
		LawDoc lawdoc = lm.getLawDocByTp(String.valueOf(oefullLawdoc.getLdtype()));

		String inputUrl = lawdoc.getLdfileurl();
		inputUrl=inputUrl.substring(inputUrl.lastIndexOf("/")+1,inputUrl.length());	//moban.doc
		String outputUrl = inputUrl.replaceAll(".docx", "_new.docx");	//moban-xin.doc
		String lawdocModelPath =ParaUtil.getParaValue("lawDocModelUpload"); //   static/dddd
		String driveName = FileCopy.getDriveName(); //c:/
//		lawdocModelPath=lawdocModelPath.replaceAll("/", "\\\\");

//		File filemdk=new File(lawdocModelPath);
//		if (!filemdk.exists()) {
//			lawdocModelPath = Class.class.getClass().getResource("/").getPath();
//
//			lawdocModelPath=lawdocModelPath.substring(0, lawdocModelPath.lastIndexOf("/"));
//			lawdocModelPath=lawdocModelPath.substring(0, lawdocModelPath.lastIndexOf("/"));
//			lawdocModelPath=lawdocModelPath.substring(1, lawdocModelPath.length())+"/lawDocModelUpload/";
//		}

		inputUrl = driveName + lawdocModelPath + inputUrl;	//模板路径
		String fileSavePath=ParaUtil.getParaValue("oefullLawDocWordFile");	// static/dddd
//		File filesavemdk=new File(fileSavePath);
//		if (!filesavemdk.exists()) {
//			fileSavePath = Class.class.getClass().getResource("/static/modular/lawdoc/").getPath();
//			fileSavePath=fileSavePath.substring(1, fileSavePath.length());
//
//		}

		outputUrl= driveName + fileSavePath + outputUrl;
		//下面开始找模板文件在不在，不在就报错,先不写报错


		if(WordUtils.changWord(inputUrl, outputUrl, resultMap, null)){
			oefullLawdoc.setUnfilled(WordUtils.unfilled);
			String lawDocShowType=ParaUtil.getParaValue("lawDocShowType");	//doc
			outputUrl=(outputUrl.substring(outputUrl.lastIndexOf("/static/modular/lawdoc/"), outputUrl.length())).replaceAll(".docx", "");
			oefullLawdoc.setFileurl(outputUrl);

			if("docx".equals(lawDocShowType))
				return outputUrl+ ".docx";
			else if("html".equals(lawDocShowType))
				return outputUrl+ ".html";

		}else{System.out.println("生成文件失败=================>");}



		return "";
	}
	@Override
	public String createOefullLawdoc(OefullLawdoc oefullLawdoc) throws Exception{

		String fileurl=getOefullLawdoc(oefullLawdoc);
		if(!"".equals(fileurl)){

			if(!super.insertOrUpdate(oefullLawdoc)){

				System.out.println("更新失败=================>返回统一报错的页面");
				return "";
			}


		}else{System.out.println("生成文件失败=================>返回统一报错的页面");}



		return fileurl;
	}
	
	/*
	@Override
	public String createOefullLawdoc(OefullLawdoc oefullLawdoc){
		List<Map<String, String>> list =om.getoefullinfo(oefullLawdoc.getOefullid());
		if(list.size()==1){
			Map<String,String> ldjson=(Map<String, String>)list.get(0);
			JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
			Map<String, String> testMap = new HashMap<String, String>();
			String  testDocMap =ParaUtil.getParaValue("lawDocModelUpload");
			if("true".equals(testDocMap)){
			testMap.put("drivername", "张三");
			testMap.put("sex", "男");
			testMap.put("year", "2018");
			testMap.put("month", "01");
			testMap.put("day", "22");
			testMap.put("age", "29");
			testMap.put("casesource", "路政巡查发现");
			testMap.put("driveraddress", "苏仙区塘溪乡塘溪村xx组");
			testMap.put("lawpersontel", "0731-8888888");
			testMap.put("drivertel", "0732-66666666");
			testMap.put("driverpersonid", "430124199909092456");
			testMap.put("lawpersonname", "李四");
			testMap.put("lawpersonaddress", "苏仙区塘溪乡");
			testMap.put("hour", "23");
			testMap.put("minute", "55");
			testMap.put("vechicleid", "湘Lxxxxx");
			testMap.put("axlesum", "3");
			testMap.put("cargoname", "铅锌矿");
			testMap.put("roadname", "S322线");
			testMap.put("enforcername1", "王五");
			testMap.put("enforcername2", "赵六");
			testMap.put("fctoalweight", "43.19");
			testMap.put("overlimited", "13.19");
			testMap.put("overlimitedrate", "43.9");

			}
			
			oefullLawdoc.setLawdocjson(ldjsonobj.toString());
			Map<String, String> resultMap =(Map<String, String> )ldjsonobj;
			oefullLawdoc.setProcstatus("1");
			JSONObject inputjsonobj=JSONObject.fromObject("{}");
			if("true".equals(testDocMap)){
			  inputjsonobj=JSONObject.fromObject(testMap);

			}else{  inputjsonobj=JSONObject.fromObject(testMap);}
			oefullLawdoc.setInputjson(inputjsonobj.toString());
			resultMap.putAll((Map<String, String> )inputjsonobj);
			LawDoc lawdoc = lm.selectById(oefullLawdoc.getLawdocid());
			String inputUrl = lawdoc.getLdfileurl();
			inputUrl=inputUrl.substring(inputUrl.lastIndexOf("/")+1,inputUrl.length());
			String outputUrl = inputUrl.replaceAll(".docx", "_new.docx");
			String  lawdocModelPath =ParaUtil.getParaValue("lawDocModelUpload");
			lawdocModelPath=lawdocModelPath.replaceAll("/", "\\\\");
			System.out.println("lawdocModelPath===>"+lawdocModelPath);
			File filemdk=new File(lawdocModelPath);  
			if (!filemdk.exists()) {
				lawdocModelPath = Class.class.getClass().getResource("/").getPath();
				lawdocModelPath=lawdocModelPath.substring(0, lawdocModelPath.lastIndexOf("/"));
				lawdocModelPath=lawdocModelPath.substring(0, lawdocModelPath.lastIndexOf("/"));
				lawdocModelPath=lawdocModelPath.substring(1, lawdocModelPath.length())+"/lawDocModelUpload/";
			}

			String fileSavePath=ParaUtil.getParaValue("oefullLawDocWordFile");
			File filesavemdk=new File(fileSavePath);  
			if (!filesavemdk.exists()) {
				fileSavePath = Class.class.getClass().getResource("/static/modular/lawdoc").getPath();
				fileSavePath=fileSavePath.substring(1, fileSavePath.length());
				if(fileSavePath.endsWith("/")){
					fileSavePath=fileSavePath.substring(0, fileSavePath.length()-1);
				}
			}

			inputUrl=lawdocModelPath+inputUrl;
			outputUrl=fileSavePath+outputUrl;
			if(WordUtils.changWord(inputUrl, outputUrl, resultMap, null)){
				oefullLawdoc.setUnfilled(WordUtils.unfilled);
				String lawDocShowType=ParaUtil.getParaValue("lawDocShowType");
				outputUrl=lawdoc.getLdfileurl().replaceAll(".docx", "");
				oefullLawdoc.setFileurl(outputUrl);
				if(!super.insertOrUpdate(oefullLawdoc)){

					System.out.println("更新失败=================>");
					return "";
				}
				if("docx".equals(lawDocShowType))
					return outputUrl+ "_new.docx";
				else if("html".equals(lawDocShowType))
					return outputUrl+ "_new.html";

			}else{System.out.println("生成文件失败=================>");}


		}
		return "";
	}
	*/
	/*
	@Override
	public void saveOefullLawdoc(Integer oefullId,String ldtype,String inputjson){
		List<Map<String, String>> list =om.getoefullinfo(oefullId);
		if(list.size()==1){
			Map<String,String> ldjson=(Map<String, String>)list.get(0);
//			  String jsonstr="{";
//	        for (Object o : ldjson.keySet()) {  
//	        	jsonstr +="\""+o+"\"" + ":"+"\"" + String.valueOf(ldjson.get(o))+"\""+",";  
//	       }
//	        jsonstr=jsonstr.substring(0, jsonstr.length()-1);
//	        jsonstr+="}";
//		 System.out.println(jsonstr);
//		saveOefullLawdoc(oefullId,ldtype,jsonstr,inputjson);
			JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
			saveOefullLawdoc(oefullId,ldtype,ldjsonobj.toString(),inputjson);
		}

	}
	@Override
	public void saveOefullLawdoc(OefullLawdoc oefullLawdoc){
		List<Map<String, String>> list =om.getoefullinfo(oefullLawdoc.getOefullid());
		if(list.size()==1){
			Map<String,String> ldjson=(Map<String, String>)list.get(0);
			JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
	         Map<String, String> testMap = new HashMap<String, String>();
	         testMap.put("drivername", "张三");
	         testMap.put("sex", "男");
	         testMap.put("year", "2018");
	         testMap.put("month", "01");
	         testMap.put("day", "22");
	         testMap.put("age", "29");
	         testMap.put("casesource", "路政巡查发现");
	         testMap.put("driveraddress", "苏仙区塘溪乡塘溪村xx组");
	         testMap.put("lawpersontel", "0731-8888888");
	         testMap.put("drivertel", "0732-66666666");
	         testMap.put("driverpersonid", "430124199909092456");
	         testMap.put("lawpersonname", "李四");
	         testMap.put("lawpersonaddress", "苏仙区塘溪乡");
	         testMap.put("hour", "23");
	         testMap.put("minute", "55");
	         testMap.put("vechicleid", "湘Lxxxxx");
	         testMap.put("axlesum", "3");
	         testMap.put("cargoname", "铅锌矿");
	         testMap.put("roadname", "S322线");
	         testMap.put("enforcername1", "王五");
	         testMap.put("enforcername2", "赵六");
	         testMap.put("fctoalweight", "43.19");
	         testMap.put("overlimited", "13.19");
	         testMap.put("overlimitedrate", "43.9");
	         JSONObject inputjsonobj=JSONObject.fromObject(testMap);
			oefullLawdoc.setLawdocjson(ldjsonobj.toString());
			oefullLawdoc.setInputjson(inputjsonobj.toString());
			oefullLawdoc.setProcstatus("1");
			if(!updateById(oefullLawdoc)){

				System.out.println("更新失败=================>");

			}
		}
	}
	@Override
	public void saveOefullLawdoc(Integer oefullId,String ldtype,String ldjson,String inputjson){
		Integer lowdocId=lm.getLdId(ldtype);
		if(null==lowdocId||lowdocId==0){
			lowdocId=lm.getMaxLdId(ldtype);
		}
		OefullLawdoc oef = new OefullLawdoc();
		oef.setLawdocid(lowdocId);
		oef.setOefullid(oefullId);
		oef.setLawdocjson(ldjson);
		oef.setInputjson(inputjson);
		oef.setProcstatus("1");
		oef.setLdtype(ldtype);

		if(!insert(oef)){
			System.out.println("新增失败=================>");
		}
	}
	@Override
	public void saveOefullLawdoc(Integer oefullId,String ldtype,Map<String,String> ldjson,Map<String,String> inputjson){
		JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
		JSONObject inputjsonobj=JSONObject.fromObject(inputjson);

		saveOefullLawdoc(oefullId,ldtype,ldjsonobj.toString(),inputjsonobj.toString());
	}
	@Override
	public void saveOefullLawdoc(Integer oefullId,String ldtype,Map<String,String> inputjson){
		List<Map<String, String>> list =om.getoefullinfo(oefullId);
		if(list.size()==1){
			Map<String,String> ldjson=(Map<String, String>)list.get(0);
			JSONObject ldjsonobj=JSONObject.fromObject(ldjson);
			JSONObject inputjsonobj=JSONObject.fromObject(inputjson);
			saveOefullLawdoc(oefullId,ldtype,ldjsonobj.toString(),inputjsonobj.toString());
		}

	}
	@Override
	public String getWord(Integer oefullId,String ldtype){
		OefullLawdoc oef=om.getOefullLawdoc(oefullId, ldtype);
		Map<String, String> resultMap =(Map<String, String> )JSONObject.fromObject(oef.getLawdocjson());
		resultMap.putAll((Map<String, String> )JSONObject.fromObject(oef.getInputjson()));
		LawDoc lawdoc = lm.selectById(oef.getLawdocid());
        String inputUrl = lawdoc.getLdfileurl();
        String outputUrl = inputUrl.replaceAll(".docx", "_new.docx");
        String fileSavePath = Class.class.getClass().getResource("/").getPath();
        fileSavePath=fileSavePath.substring(1, fileSavePath.length());
        if(fileSavePath.endsWith("/")){
            fileSavePath=fileSavePath.substring(0, fileSavePath.length()-1);
        }
        inputUrl=fileSavePath+inputUrl;
        outputUrl=fileSavePath+outputUrl;
		if(WordUtils.changWord(inputUrl, outputUrl, resultMap, null)){
			String lawDocShowType=ParaUtil.getParaValue("lawDocShowType");
			if("docx".equals(lawDocShowType))
			return lawdoc.getLdfileurl().replaceAll(".docx", "_new.docx");
			else if("html".equals(lawDocShowType))
				return lawdoc.getLdfileurl().replaceAll(".docx", "_new.html");
		}
		return null;

	}
	 */

}
