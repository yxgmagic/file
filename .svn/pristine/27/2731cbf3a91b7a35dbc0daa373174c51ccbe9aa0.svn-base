package com.zhichao.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhichao.beans.guns.ServerAuth;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zhichao
 * @since 2018-02-25
 */
public interface ServerAuthMapper extends BaseMapper<ServerAuth> {
	 List<Map<String, Object>> list(  @Param("alias") String alias, @Param("diskno") String diskno, @Param("macno") String  macno,@Param("id") Integer id);
		Integer updateCdkey(@Param(value="id") Integer id,
				@Param(value="cdkey") String cdkey);
		 String getPrivatekey();
		 String getAuthPublickey( @Param("authid") Integer authid);
		 List<Map<String, Object>> list_server_auth_chain(  @Param("authid") Integer authid);
		 List<Map<String, Object>> list_server_chain(  @Param("authid") Integer authid);
		 List<Map<String, Object>> list_server_list(  @Param("authid") Integer authid);//
		 List<Map<String, Object>> list_chain_action(  @Param("authid") Integer authid);
		 List<Map<String, Object>> list_action_list(  @Param("authid") Integer authid);//
		 List<Map<String, Object>> list_action_para(  @Param("authid") Integer authid);//
		 List<Map<String, Object>> list_inchain(  @Param("authid") Integer authid);//
		 List<Map<String, Object>> list_chain(  @Param("authid") Integer authid);//
		 List<Map<String, Object>> list_outchain(  @Param("authid") Integer authid);//
		 List<Map<String, Object>> list_sever_para(  @Param("authid") Integer authid);//
}