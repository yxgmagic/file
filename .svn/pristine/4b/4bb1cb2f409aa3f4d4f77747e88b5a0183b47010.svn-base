package com.zhichao.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.zhichao.jobCenter.JobApplication;

import com.zhichao.common.CommonApplication;
import com.zhichao.core.CoreApplication;
import com.zhichao.service.ServiceApplication;

/**
 * admin Web程序启动类
 *
 * @author fengshuonan
 * @date 2017-05-21 9:43
 */
public class GunsServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    	Object[] objs = new Object[5];
    	objs[0] = CommonApplication.class;
    	objs[1] = CoreApplication.class;
    	objs[2] = GunsApplication.class;
    	objs[3] = ServiceApplication.class;
    	objs[4] = JobApplication.class;
        return builder.sources(objs);
    }

}
