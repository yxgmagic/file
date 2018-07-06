package com.zhichao.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.service.common.IBsImageEntityService;
import com.zhichao.beans.guns.BsImageEntity;
import com.zhichao.beans.guns.Image;
import com.zhichao.dal.mapper.BsImageEntityMapper;

/**
 * <p>
 * 照片与照片内容实体关系表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-08
 */
@Service
public class BsImageEntityServiceImpl extends ServiceImpl<BsImageEntityMapper, BsImageEntity> implements IBsImageEntityService {

	
	@Autowired
	private BsImageEntityMapper mapper;
	
	@Override
	public Image findImg(Integer id, String imagetype) {
		List<Image> img = mapper.findImg(id, imagetype);
		if(img.size() > 0) {
			return img.get(0);
		}
		return null;
	}

	@Override
	public void updateByEntityIdAndImageType(Integer id, String imageType) {
		mapper.updateByEntityIdAndImageType(id, imageType);
	}

	@Override
	public Integer insertImageEntity(Integer imgId, Integer entityId, String imageType) {
		
		if (imgId != null && imgId > 0 && entityId != null && imageType != null) {
			
			//向关系表中存入数据
			BsImageEntity imageEntity = new BsImageEntity();
			
			imageEntity.setImageid(imgId);
			imageEntity.setEntityid(entityId);
			imageEntity.setImagetype(imageType);
			imageEntity.setUserid(ShiroKit.getUser().getId());
			//有效标记,1=有效.0=无效
			imageEntity.setValidflag("1");
			
			Integer flag = mapper.insert(imageEntity);
			return flag;
		}
		return null;
	}

	@Override
	public Integer updateEntityImage(Integer imgId, Integer entityId, String imageType) {
		
		
		if (imgId != null && imgId > 0 && entityId != null && imageType != null) {
			
			//将关系表中的所有与entityId有关的关系全置为无效
			mapper.updateByEntityIdAndImageType(entityId, imageType);
			
			//向关系表中插入新的企业营业执照
			BsImageEntity imageEntity = new BsImageEntity();
			
			imageEntity.setImageid(imgId);
			imageEntity.setEntityid(entityId);
			imageEntity.setImagetype(imageType);
			imageEntity.setUserid(ShiroKit.getUser().getId());
			imageEntity.setValidflag("1");
			
			Integer flag = mapper.insert(imageEntity);
			return flag;
		}
		return null;
	}
	
}
