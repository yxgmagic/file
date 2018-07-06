package com.zhichao.dal.platformConfig;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PunishmentRulesDao {
	List<Map<String, Object>> selectPunishmentRulesList(@Param("trucksType") String trucksType,
			@Param("trucksAxles") String trucksAxles);
}
