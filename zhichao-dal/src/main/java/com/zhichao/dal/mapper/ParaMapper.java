package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Para;

/**
 * <p>
  * 系统参数表 Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-01-15
 */
public interface ParaMapper extends BaseMapper<Para> {
	 List<Map<String, String>> listPara( );

}