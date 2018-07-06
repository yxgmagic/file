package com.zhichao.admin.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * flow的配置
 *
 * @author fengshuonan
 * @date 2017-12-02 23:18
 */
@Configuration
@ConfigurationProperties(prefix = GunsTestMultipleDataSourceProperties.GUNS_Test_DATASOURCE)
public class GunsTestMultipleDataSourceProperties {

    public static final String GUNS_Test_DATASOURCE = "guns.testds.datasource";

    //默认多数据源的链接
    private String url = "jdbc:mysql://127.0.0.1:3306/testdb?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false";

    //默认多数据源的数据库账号
    private String username = "root";

    //默认多数据源的数据库密码
    private String password = "root";

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}