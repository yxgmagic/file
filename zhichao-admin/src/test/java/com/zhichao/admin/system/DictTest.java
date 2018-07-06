package com.zhichao.admin.system;


import com.zhichao.admin.base.BaseJunit;
import com.zhichao.dal.system.DictDao;
import com.zhichao.service.system.IDictService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 字典服务测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class DictTest extends BaseJunit {

    @Resource
    IDictService dictService;

    @Resource
    DictDao dictDao;

}
