package com.zhichao.dal.system;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IndexDataDao {
	
	//获取固定治超站所有通过的车流量
	Integer getFixedsiTetotalFlow(@Param("userdeptid")String userdeptid,@Param("month1") String month1);
	
	//获取固定治超站所有超载的车流量
	Integer getFixedsiOverloadFlow(@Param("userdeptid")String userdeptid,@Param("month1") String month1);

	//获取固定治超站所有已处理的车辆数目
	Integer getFixedsiteHandleCount(@Param("userdeptid")String userdeptid,@Param("month1") String month1);

	//获取所有的卸货量
	Integer getFixedsiteTotalUnloadWeight(@Param("userdeptid")String userdeptid,@Param("month1") String month1);


	/**
	 *	根据传进来的时间区间进行查询车流量信息
	 * @param userdeptid	用户所属部门的id
	 * @param beginTime		查询的开始时间
	 * @param endTime		查询的结束时间
	 * @return
	 */
	Map<String, Object> getTrafficFlow(@Param("userdeptid")String userdeptid,
											 @Param("beginTime") String beginTime,
											 @Param("endTime") String endTime);

	/**
	 * 根据传进来的时间区间进行查询车流量车轴数的统计信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<Map<String, Object>> getTrafficAxle(@Param("userdeptid")String userdeptid,
											 @Param("beginTime") String beginTime,
											 @Param("endTime") String endTime);

	/**
	 * 判断用户是否可以操作该部门
	 * @param userdeptid
	 * @param thatDeptid
	 * @return	部门id
	 */
	Map<String, Integer> hasDeptidPermission(@Param("userdeptid")String userdeptid,
								   @Param("thatDeptid")String thatDeptid);


	/**
	 * 获取部门的所有子部门
	 * @param thatDeptid
	 * @return
	 */
	List<Map<String, Object>> getChildsDepts(@Param("thatDeptid")String thatDeptid);

	/**
	 * 获取该部门的父级部门
	 * @param thatDeptid
	 * @return
	 */
	Map<String, Object> getParentDepts(@Param("thatDeptid")String thatDeptid);
}
