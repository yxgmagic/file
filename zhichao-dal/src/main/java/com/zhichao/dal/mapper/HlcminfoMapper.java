package com.zhichao.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhichao.beans.guns.Corpinfo;
import com.zhichao.beans.guns.Hlcminfo;

/**
 * <p>
  * VIEW Mapper 接口
 * </p>
 *
 * @author zqf
 * @since 2018-02-07
 */
public interface HlcminfoMapper extends BaseMapper {
	 List<Hlcminfo> listhlcminfo(@Param("page") Page<Hlcminfo> page, @Param("sitename") String sitename, @Param("areacode") String areacode, @Param("begindt") String  begindt
			 , @Param("enddt") String  enddt, @Param("sitetype") String sitetype, @Param("checkmode") String checkmode, @Param("vehicleid") String vehicleid);
		Corpinfo selCorpInfo(@Param("id") Integer id);
		Corpinfo selMeinfoById(@Param("id") Integer id);
		Corpinfo selLscinfoById(@Param("id") Integer id);
		Corpinfo selHspInfo(@Param("id") Integer id);

}