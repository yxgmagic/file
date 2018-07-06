package com.zhichao.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.BsImageEntity;
import com.zhichao.beans.guns.Image;

/**
 * <p>
  * 照片与照片内容实体关系表 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-01-08
 */
public interface BsImageEntityMapper extends BaseMapper<BsImageEntity> {

	List<Image> findImg(@Param("id") Integer id, @Param("imagetype") String imagetype);

	void updateByEntityIdAndImageType(@Param("id") Integer id, @Param("imageType") String imageType);

}