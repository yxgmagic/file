package org.zhichao.jobCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
public class JobApplication extends WebMvcConfigurerAdapter {

    protected final static Logger logger = LoggerFactory.getLogger(JobApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
        logger.info("JobApplication is success!");
    }
}
