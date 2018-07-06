package com.zhichao.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.BsCorp;
import org.springframework.stereotype.Repository;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author imyzt
 * @since 2018-01-02
 */
@Repository
public interface BsCorpMapper extends BaseMapper<BsCorp> {

	/**
	 * 查询源头企业管理方法
	 * @param corpname 企业名称
	 * @param areacode 所属区域
	 * @return
	 */
	List<BsCorp> queryCorpByCondition(@Param(value = "corpname") String corpname, @Param(value = "areacode") String areacode);
	
	/**
	 * 根据ID查询源头企业
	 * @param id 企业主键id
	 * @return
	 */
	BsCorp selById(Integer id);

    /**
     * 查询源头企业是否存在
     * @param corpcode
     * @param id
     * @param managerid
     * @return
     */
	Integer corpIsExist(@Param(value = "corpcode") String corpcode, @Param(value = "id") Integer id, @Param(value = "managerid") String managerid);
}