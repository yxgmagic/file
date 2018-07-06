package com.zhichao.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhichao.service.common.IImageService;
import com.zhichao.beans.guns.Image;
import com.zhichao.dal.mapper.ImageMapper;

/**
 * <p>
 * 存储营业执照,汽车图片等字段 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-02
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

	
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public Integer insertImage(Image img) {
		return imageMapper.insertImage(img);
	}
	
}
