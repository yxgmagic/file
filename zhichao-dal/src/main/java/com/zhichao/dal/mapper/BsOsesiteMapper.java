package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.BsOsesite;

/**
 * <p>
  * 非现场执法信息表 Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-03-29
 */
@Repository
public interface BsOsesiteMapper extends BaseMapper<BsOsesite> {

    List<Map<String, Object>> list(@Param("sitename") String sitename, @Param("roadcode") String roadcode,
                                   @Param("areacode") String areacode, @Param("id") Integer id,
                                   @Param("userDeptid") String userDeptid);

}