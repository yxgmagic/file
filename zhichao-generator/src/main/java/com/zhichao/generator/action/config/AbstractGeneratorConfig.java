package com.zhichao.generator.action.config;

import java.io.File;
import java.util.List;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.zhichao.core.util.FileUtil;
import com.zhichao.generator.engine.SimpleTemplateEngine;
import com.zhichao.generator.engine.base.GunsTemplateEngine;
import com.zhichao.generator.engine.config.ContextConfig;
import com.zhichao.generator.engine.config.SqlConfig;

/**
 * 代码生成的抽象配置
 *
 * @author fengshuonan
 * @date 2017-10-28-下午8:22
 */
public abstract class AbstractGeneratorConfig {

	String[] models;
	
    public String[] getModels() {
		return models;
	}

	public void setModels(String[] models) {
		this.models = models;
	}

	/**
     * mybatis-plus代码生成器配置
     */

    GlobalConfig globalConfig = new GlobalConfig();

    DataSourceConfig dataSourceConfig = new DataSourceConfig();

    StrategyConfig strategyConfig = new StrategyConfig();

    PackageConfig packageConfig = new PackageConfig();

    TableInfo tableInfo = null;

    /**
     * Guns代码生成器配置
     */
    ContextConfig contextConfig = new ContextConfig();

    SqlConfig sqlConfig = new SqlConfig();

    protected abstract void config(String model);

    public void init(String model) {
        config(model);
    }

    /**
     * 删除不必要的代码
     */
    public void destory() {
        String outputDir = globalConfig.getOutputDir() + "/TTT";
        FileUtil.deleteDir(new File(outputDir));
    }

    public AbstractGeneratorConfig() {
    }

    public void doMpGeneration() {
    	for (String model : models) {
    		AutoGenerator autoGenerator = new AutoGenerator();
    		init(model);
            autoGenerator.setGlobalConfig(globalConfig);
            autoGenerator.setDataSource(dataSourceConfig);
            autoGenerator.setStrategy(strategyConfig);
            autoGenerator.setPackageInfo(packageConfig);
            TemplateConfig templateConfig = new TemplateConfig();
            if(!contextConfig.getDaoSwitch()){//dal-java
            	templateConfig.setController(null);
            	templateConfig.setEntity(null);
            	templateConfig.setService(null);
            	templateConfig.setServiceImpl(null);
            	templateConfig.setXml(null);
            	contextConfig.setDaoSwitch(true);
            } 
            if (!contextConfig.getJsSwitch()) {//dal-xml
            	templateConfig.setController(null);
            	templateConfig.setEntity(null);
            	templateConfig.setService(null);
            	templateConfig.setServiceImpl(null);
            	templateConfig.setMapper(null);
            	contextConfig.setJsSwitch(true);
            } 
            if (!contextConfig.getEntitySwitch()) {//beans
            	templateConfig.setController(null);
            	templateConfig.setService(null);
            	templateConfig.setServiceImpl(null);
            	templateConfig.setMapper(null);
            	templateConfig.setXml(null);
            	contextConfig.setEntitySwitch(true);
            } 
            if (!contextConfig.getServiceSwitch()) {//service
            	templateConfig.setController(null);
            	templateConfig.setMapper(null);
            	templateConfig.setXml(null);
            	templateConfig.setEntity(null);
            	contextConfig.setServiceSwitch(true);
            } 
            autoGenerator.setTemplate(templateConfig);
            autoGenerator.execute();
            destory();
            //获取table信息,用于guns代码生成
            List<TableInfo> tableInfoList = autoGenerator.getConfig().getTableInfoList();
            if (tableInfoList != null && tableInfoList.size() > 0) {
                this.tableInfo = tableInfoList.get(0);
            }
        }
    }

    public void doAdiGeneration() {
        GunsTemplateEngine GunsTemplateEngine = new SimpleTemplateEngine();
        GunsTemplateEngine.setContextConfig(contextConfig);
        sqlConfig.setConnection(dataSourceConfig.getConn());
        GunsTemplateEngine.setSqlConfig(sqlConfig);
        GunsTemplateEngine.setTableInfo(tableInfo);
        GunsTemplateEngine.start();
    }
}
