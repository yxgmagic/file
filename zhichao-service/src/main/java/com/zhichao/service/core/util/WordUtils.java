package com.zhichao.service.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import net.sf.json.JSONObject;
import org.springframework.util.ClassUtils;


public class WordUtils {
	public static String unfilled="";
	/**
	 * 根据模板生成新word文档
	 * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
	 * @param outPutUrl 新文档存放地址
	 * @param textMap 需要替换的信息集合
	 * @param tableList 需要插入的表格信息集合
	 * @return 成功返回true,失败返回false
	 */
	public static boolean changWord(String inputUrl, String outputUrl,
									Map<String, String> textMap, List<String[]> tableList) {
		unfilled="";

		//	项目的绝对路径
		/*String projectPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		projectPath = projectPath.substring(1);
		//	拼接存储检定证书的实际目录	因为测试环境硬盘分区总是变动
		String[] pathArr = projectPath.split(":");
		inputUrl = pathArr[0] + ":" + File.separator + inputUrl;
		outputUrl = projectPath + outputUrl;*/

//		System.out.println("inputUrl==>"+inputUrl);
//		System.out.println("outputUrl==>"+outputUrl);
//		System.out.println("textMap==>"+textMap.toString());
		//模板转换默认成功
		boolean changeFlag = true;
		try {
			//获取docx解析对象
			XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
//			List<String> list = new ArrayList();
//			list.addAll(WordUtils.getTextParas(document));
//			list.addAll(WordUtils.getTableParams(document));
//			System.out.println(list);
//			list =getNoRptList(list);
//			System.out.println(list);
			//解析替换文本段落对象
			WordUtils.changeText(document, textMap);

			//解析替换表格对象
			WordUtils.changeTable(document, textMap, tableList);

			//生成新的word
			File file = new File(outputUrl);
			FileOutputStream stream = new FileOutputStream(file);
			document.write(stream);
			stream.close();

			//            //生成新的html
			//            XHTMLOptions options = XHTMLOptions.create();  
			//            // 存放图片的文件夹  
			//            String imagePathStr ="/images";
			//            options.setExtractor(new FileImageExtractor(new File(imagePathStr)));  
			//            // html中图片的路径  
			//            options.URIResolver(new BasicURIResolver("image"));  
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outputUrl.replaceAll(".docx", ".html")), "utf-8");  
			XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();  
			//            xhtmlConverter.convert(document, outputStreamWriter, options);  
			xhtmlConverter.convert(document, outputStreamWriter, null);  
			outputStreamWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
			changeFlag = false;
		}

		return changeFlag;

	}

	/*
	 * List去掉重复
	 * 
	 */
	public static List<String> getNoRptList(List<String> list){
		Set set = new  HashSet(); 
		List<String> newList = new  ArrayList(); 
		for (String cd:list) {
			if(set.add(cd)){
				newList.add(cd);
			}
		}
		return newList;
	}
	/**
	 * 获取段落文本params
	 * @param document docx解析对象
	 * @param textMap 需要替换的信息集合
	 */
	public static List<String> getTextParas(XWPFDocument document ){
		//获取段落集合
		List<XWPFParagraph> paragraphs = document.getParagraphs();
		List<String> list = new ArrayList();
		for (XWPFParagraph paragraph : paragraphs) {

			//判断此段落时候需要进行替换
			String text = paragraph.getText();	

			if(checkText(text)){
				List<XWPFRun> runs = paragraph.getRuns();
				for (XWPFRun run : runs) {
					//替换模板原来位置
					// System.out.println(run.toString());
					if(checkText(run.toString())){
						list.add(returnValue(run.toString()));
					}
				}
			}
		}
		return list;

	}
	/**
	 * 替换段落文本
	 * @param document docx解析对象
	 * @param textMap 需要替换的信息集合
	 */
	public static void changeText(XWPFDocument document, Map<String, String> textMap){
		//获取段落集合
		List<XWPFParagraph> paragraphs = document.getParagraphs();

		for (XWPFParagraph paragraph : paragraphs) {

			//判断此段落时候需要进行替换
			String text = paragraph.getText();	

			if(checkText(text)){
				List<XWPFRun> runs = paragraph.getRuns();
				for (XWPFRun run : runs) {
					//替换模板原来位置
					String value=changeValue(run.toString(), textMap);
					 
					run.setText(value,0);
				}
			}
		}

	}

	/**
	 * 获取表格对象paras
	 * @param document docx解析对象
	 * @param textMap 需要替换的信息集合
	 * @param tableList 需要插入的表格信息集合
	 */
	public static List<String> getTableParams(XWPFDocument document ){

		//获取表格对象集合
		List<XWPFTable> tables = document.getTables();
		List<String> list = new ArrayList();
		for (int i = 0; i < tables.size(); i++) {
			//只处理行数大于等于2的表格，且不循环表头
			XWPFTable table = tables.get(i);

			if(table.getRows().size()>1){
				//判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入

				if(checkText(table.getText())){
					List<XWPFTableRow> rows = table.getRows();

					//遍历表格,并替换模板
					list.addAll(getEachTableParams(rows));
				}else{


				}
			}
		}
		return list;
	}
	/**
	 * 替换表格对象方法
	 * @param document docx解析对象
	 * @param textMap 需要替换的信息集合
	 * @param tableList 需要插入的表格信息集合
	 */
	public static void changeTable(XWPFDocument document, Map<String, String> textMap,
			List<String[]> tableList){

		//获取表格对象集合
		List<XWPFTable> tables = document.getTables();

		for (int i = 0; i < tables.size(); i++) {
			//只处理行数大于等于2的表格，且不循环表头
			XWPFTable table = tables.get(i);

			if(table.getRows().size()>1){
				//判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入

				if(checkText(table.getText())){
					List<XWPFTableRow> rows = table.getRows();

					//遍历表格,并替换模板
					eachTable(rows, textMap);
				}else{
					//                  System.out.println("插入"+table.getText());
					insertTable(table, tableList);
				}
			}
		}
	}



	/**
	 * 遍历表格获取params
	 * @param rows 表格行对象
	 * @param textMap 需要替换的信息集合
	 */
	public static List<String> getEachTableParams(List<XWPFTableRow> rows  ){
		List<String> list = new ArrayList();
		for (XWPFTableRow row : rows) {
			List<XWPFTableCell> cells = row.getTableCells();
			for (XWPFTableCell cell : cells) {
				//判断单元格是否需要替换
				if(checkText(cell.getText())){
					List<XWPFParagraph> paragraphs = cell.getParagraphs();
					for (XWPFParagraph paragraph : paragraphs) {
						List<XWPFRun> runs = paragraph.getRuns();
						for (XWPFRun run : runs) {
							if(checkText(run.toString())){
								//System.out.println("(run.toString()===>"+(run.toString()));

								list.add(returnValue(run.toString()));
							}
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 遍历表格
	 * @param rows 表格行对象
	 * @param textMap 需要替换的信息集合
	 */
	public static void eachTable(List<XWPFTableRow> rows ,Map<String, String> textMap){
		for (XWPFTableRow row : rows) {
			List<XWPFTableCell> cells = row.getTableCells();
			for (XWPFTableCell cell : cells) {
				//判断单元格是否需要替换
				if(checkText(cell.getText())){
					List<XWPFParagraph> paragraphs = cell.getParagraphs();
					for (XWPFParagraph paragraph : paragraphs) {
						List<XWPFRun> runs = paragraph.getRuns();
						for (XWPFRun run : runs) {
							String value=changeValue(run.toString(), textMap);
							 
							run.setText(value,0);
							 
						}
					}
				}
			}
		}
	}

	/**
	 * 为表格插入数据，行数不够添加新行
	 * @param table 需要插入数据的表格
	 * @param tableList 插入数据集合
	 */
	public static void insertTable(XWPFTable table, List<String[]> tableList){
		//创建行,根据需要插入的数据添加新行，不处理表头
		for(int i = 1; i < tableList.size(); i++){
			XWPFTableRow row =table.createRow();
		}
		//遍历表格插入数据
		List<XWPFTableRow> rows = table.getRows();
		for(int i = 1; i < rows.size(); i++){
			XWPFTableRow newRow = table.getRow(i);
			List<XWPFTableCell> cells = newRow.getTableCells();
			for(int j = 0; j < cells.size(); j++){
				XWPFTableCell cell = cells.get(j);
				cell.setText(tableList.get(i-1)[j]);
			}
		}

	}



	/**
	 * 判断文本中时候包含$
	 * @param text 文本
	 * @return 包含返回true,不包含返回false
	 */
	public static boolean checkText(String text){
		boolean check  =  false;
		if(text.indexOf("$")!= -1){
			check = true;
		}
		return check;

	}
	/**
	 * 匹配传入信息集合与模板
	 * @param value 模板需要替换的区域
	 * @param textMap 传入信息集合
	 * @return 模板需要替换区域信息集合对应值
	 */
	public static String returnValue(String value){

		Pattern p=Pattern.compile("\\$\\{(\\w+)\\}");
		Matcher m=p.matcher(value);
		while(m.find()){
			value=m.group(1);

		}
		// System.out.println("value====>"+value);
		return value;
	}
	/**
	 * 匹配传入信息集合与模板
	 * @param value 模板需要替换的区域
	 * @param textMap 传入信息集合
	 * @return 模板需要替换区域信息集合对应值
	 */
	public static String changeValue(String value, Map<String, String> textMap){
		Set<Entry<String, String>> textSets = textMap.entrySet();
		for (Entry<String, String> textSet : textSets) {
			//匹配模板与替换值 格式${key}
			String key = "${"+textSet.getKey()+"}";
			if(value.indexOf(key)!= -1){
				value = String.valueOf(textSet.getValue());
			}
		}
		//模板未匹配到区域替换为空
		if(checkText(value)){
			unfilled+="${"+returnValue(value)+"}";
			value = "  ";
		}
		return value;
	}


	public static void main(String[] args) {
		//模板文件地址
		String inputUrl = "/立案审批_3.docx";
		//新生产的模板文件
		String outputUrl = "/立案审批表_3_new.docx";
		 

		String fileSavePath = Class.class.getClass().getResource("/WEB-INF/view/lawdoc").getPath();
 
		fileSavePath=fileSavePath.substring(1, fileSavePath.length())+"/lawDocModelUpload";
		inputUrl=fileSavePath+inputUrl;
		outputUrl=fileSavePath+outputUrl;
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
		JSONObject json =JSONObject.fromObject(testMap);
		System.out.println("json==="+json);

		Map<String, String> resultMap =(Map<String, String>)json;
		String jsonstr="{";
		for (Object o : resultMap.keySet()) {  
			jsonstr +="\""+o+"\"" + ":"+"\"" + resultMap.get(o)+"\""+",";  
		}
		jsonstr=jsonstr.substring(0, jsonstr.length()-1);
		jsonstr+="}";
		System.out.println("jsonstr===>"+jsonstr);



		List<String[]> testList = new ArrayList<String[]>();


		WordUtils.changWord(inputUrl, outputUrl, testMap, testList);
	}
}