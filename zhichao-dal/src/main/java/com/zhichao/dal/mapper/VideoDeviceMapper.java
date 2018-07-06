package com.zhichao.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.node.ZTreeNodeEntity;
import com.zhichao.beans.guns.VideoDevice;

/**
 * <p>
  * 视频设备信息表 Mapper 接口
 * </p>
 *
 * @author zjl
 * @since 2018-01-25
 */
public interface VideoDeviceMapper extends BaseMapper<VideoDevice> {
	List<ZTreeNodeEntity> deviceTree();
	
	List<ZTreeNodeEntity> deviceTreeBySiteCode(@Param("siteCode") String siteCode);
}