package com.zhichao.generator.engine.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import com.sun.javafx.PlatformUtil;
import com.zhichao.common.util.ToolUtil;

/**
 * ADI项目模板生成 引擎
 *
 * @author fengshuonan
 * @date 2017-05-07 22:15
 */
public abstract class GunsTemplateEngine extends AbstractTemplateEngine {

	private GroupTemplate groupTemplate;

	public GunsTemplateEngine() {
		initBeetlEngine();
	}

	protected void initBeetlEngine() {
		Properties properties = new Properties();
		properties.put("RESOURCE.root", "");
		properties.put("DELIMITER_STATEMENT_START", "<%");
		properties.put("DELIMITER_STATEMENT_END", "%>");
		properties.put("HTML_TAG_FLAG", "##");
		Configuration cfg = null;
		try {
			cfg = new Configuration(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
		groupTemplate = new GroupTemplate(resourceLoader, cfg);
		groupTemplate.registerFunctionPackage("tool", new ToolUtil());
	}

	protected void configTemplate(Template template) {
		template.binding("controller", super.controllerConfig);
		template.binding("context", super.contextConfig);
		template.binding("dao", super.daoConfig);
		template.binding("service", super.serviceConfig);
		template.binding("sqls", super.sqlConfig);
		template.binding("table", super.tableInfo);
	}

	protected void generateFile(String template, String filePath) {
		Template pageTemplate = groupTemplate.getTemplate(template);
		configTemplate(pageTemplate);
		if (PlatformUtil.isWindows()) {
			filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
		} else {
			filePath = filePath.replaceAll("/+|\\\\+", "/");
		}
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		OutputStreamWriter oStreamWriter = null;
		// FileOutputStream fileOutputStream = null;
		try {
			//直接设置utf-8会生成带bom的utf-8文件，此处暂时将编码设置成GBK
			oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "GBK");
			// fileOutputStream = new FileOutputStream(file);
			pageTemplate.renderTo(oStreamWriter);
			//将文件编码转成utf-8
			codeConvert(file);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oStreamWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void codeConvert(File file) {
		BufferedReader br;
		BufferedWriter bw;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("GBK")));
			StringBuilder sb = new StringBuilder();
			String str;
			while ((str = br.readLine()) != null) {
				if(str.startsWith("?")){//第一行首位出现?处理
					str = str.replaceFirst("\\?", " ");
				}
				sb.append(str);
				sb.append("\n");
			}
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
			bw.write(sb.toString());
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
//			bw.close();
//			 br.close();
		}

	}

	public void start() {
		// 配置之间的相互依赖
		super.initConfig();

		// 生成模板
		if (super.contextConfig.getControllerSwitch()) {
			generateController();
		}
		if (super.contextConfig.getIndexPageSwitch()) {
			generatePageHtml();
		}
		if (super.contextConfig.getAddPageSwitch()) {
			generatePageAddHtml();
		}
		if (super.contextConfig.getEditPageSwitch()) {
			generatePageEditHtml();
		}
		if (super.contextConfig.getJsSwitch()) {
			generatePageJs();
		}
		if (super.contextConfig.getInfoJsSwitch()) {
			generatePageInfoJs();
		}
		if (super.contextConfig.getSqlSwitch()) {
			generateSqls();
		}
	}

	protected abstract void generatePageEditHtml();

	protected abstract void generatePageAddHtml();

	protected abstract void generatePageInfoJs();

	protected abstract void generatePageJs();

	protected abstract void generatePageHtml();

	protected abstract void generateController();

	protected abstract void generateSqls();

}
