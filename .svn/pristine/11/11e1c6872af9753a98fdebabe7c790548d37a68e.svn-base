package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.VideoServer;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zhichao
 * @since 2018-01-24
 */
public interface VideoServerMapper extends BaseMapper<VideoServer> {
	List<Map<String, Object>> getVideoList(@Param("flag") int flag,@Param("code") String code);
	List<Map<String, Object>> findVideoList(@Param("siteName") String siteName);
}