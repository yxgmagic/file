package com.zhichao.admin.config;


import java.io.IOException;

import org.flowable.spring.SpringAsyncExecutor;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhichao.admin.config.properties.GunsFlowableProperties;
import com.zhichao.service.core.flowable.GunsDefaultProcessDiagramGenerator;
import com.zhichao.core.datasource.DruidProperties;

/**
 * 工作流配置
 *
 * @author fengshuonan
 * @date 2017-12-02 22:28
 */
@Configuration
public class FlowableConfig extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    GunsFlowableProperties gunsFlowableProperties;

    /**
     * flowable单独的数据源
     */
    private DruidDataSource flowableDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        gunsFlowableProperties.config(dataSource);
        return dataSource;
    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            PlatformTransactionManager transactionManager,
            SpringAsyncExecutor springAsyncExecutor) throws IOException {

        SpringProcessEngineConfiguration configuration = this.baseSpringProcessEngineConfiguration(flowableDataSource(), transactionManager, springAsyncExecutor);
        configuration.setActivityFontName("宋体");
        configuration.setLabelFontName("宋体");
        configuration.setProcessDiagramGenerator(new GunsDefaultProcessDiagramGenerator());
        return configuration;
    }
}
