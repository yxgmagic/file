package com.zhichao.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.Cartype;
import com.zhichao.beans.node.ZTreeNode;

/**
 * <p>
  * 车型分类信息表 Mapper 接口
 * </p>
 *
 * @author zjl
 * @since 2018-01-09
 */
public interface CartypeMapper extends BaseMapper<Cartype> {
	/**
     * 获取ztree的节点列表
     *
     * @return
     * @date 2017年2月17日 下午8:28:43
     */
    List<ZTreeNode> tree();
    Cartype selectByCarTypeCode(@Param("cartypecode") String cartypecode);
}