package com.zhichao.service.siteRegistration;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.BsOsesite;

/**
 * <p>
 * 非现场执法信息表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-03-29
 */
public interface IBsOsesiteService extends IService<BsOsesite> {

    List<Map<String, Object>> list(String sitename, String roadcode, String areacode, Integer id);
}
