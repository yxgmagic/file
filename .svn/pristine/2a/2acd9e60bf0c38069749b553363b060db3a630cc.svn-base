package com.zhichao.generator.action;

import com.zhichao.generator.action.config.GunsGeneratorConfig;
import com.zhichao.generator.action.config.MyGeneratorConfig;

/**
 * 代码生成器,可以生成实体,dao,service,controller,html,js
 *
 * @author stylefeng
 * @Date 2017/5/21 12:38
 */
public class GunsCodeGenerator {

    public static void main(String[] args) {

        /**
         * Mybatis-Plus的代码生成器:
         *      mp的代码生成器可以生成实体,mapper,mapper对应的xml,service
         */
    	GunsGeneratorConfig gunsGeneratorConfig = new GunsGeneratorConfig();
    	
        gunsGeneratorConfig.doMpGeneration();

        /**
         * adi的生成器:
         *      adi的代码生成器可以生成controller,html页面,页面对应的js
         */
        gunsGeneratorConfig.doAdiGeneration();
    }

}