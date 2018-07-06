package com.zhichao.admin.core;

import com.zhichao.admin.core.KaptchaUtil;
import com.zhichao.common.util.ToolUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import com.zhichao.service.core.beetl.ShiroExt;
import com.zhichao.service.core.util.SelectDictTag;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {

        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
        groupTemplate.registerTag("SelectDict", SelectDictTag.class);

    }

}
