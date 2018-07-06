package com.zhichao.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Image;

/**
 * <p>
  * 存储营业执照,汽车图片等字段 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-01-02
 */
public interface ImageMapper extends BaseMapper<Image> {

	Integer insertImage(Image img);
}