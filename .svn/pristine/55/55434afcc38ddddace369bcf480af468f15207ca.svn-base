package com.zhichao.service.common;

import com.baomidou.mybatisplus.service.IService;
import com.zhichao.beans.guns.BsImageEntity;
import com.zhichao.beans.guns.Image;

/**
 * <p>
 * 照片与照片内容实体关系表 服务类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-08
 */
public interface IBsImageEntityService extends IService<BsImageEntity> {

	Image findImg(Integer id, String imagetype);

	void updateByEntityIdAndImageType(Integer id, String imageType);

	/**
	 * 新增实体后建立实体与图片表中的关系
	 * 
	 * @param imgId
	 * imgId 默认为0表示没有选中图片上传
	 * imgId 不等于0 imgId=图片表中的id
	 * 
	 * @param entityId
	 * 新增实体的id
	 * 
	 * @param imageType
	 * 新增实体的类型,新增实体的表后缀.
	 * 
	 * @return
	 * return != null 操作成功
	 * 操作成功返回关系表中id
	 */
	Integer insertImageEntity(Integer imgId, Integer entityId, String imageType);
	
	/**
	 * 修改实体后建立实体与图片表中的新关系
	 * 
	 * @param imgId
	 * imgId 默认为0表示没有选中图片上传
	 * imgId 不等于0 imgId=图片表中的id
	 * 
	 * @param entityId
	 * 新增实体的id
	 * 
	 * @param imageType
	 * 新增实体的类型,新增实体的表后缀.
	 * 
	 * @return
	 * return != null 操作成功
	 * 操作成功返回关系表中id
	 */
	Integer updateEntityImage(Integer imgId, Integer entityId, String imageType);
	
}
