package com.zhichao.service.perforevalue;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.Indicator;

/**
 * <p>
 * 指标管理表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-02-27
 */
public interface IIndicatorService extends IService<Indicator> {

    List<Indicator> selList(String indicName, String indicType);
}
