package com.zhichao.service.system.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhichao.dal.mapper.UserMapper;
import com.zhichao.service.core.shiro.ShiroKit;
import com.zhichao.dal.system.IndexDataDao;
import com.zhichao.dal.system.NoticeDao;
import com.zhichao.service.system.IIndexDataService;
import com.zhichao.common.util.ToolUtil;

@Service
public class IndexDataServiceImp implements IIndexDataService {

	@Resource
	IndexDataDao indexDataDao;

	@Resource
	NoticeDao noticeDao;

	@Resource
	private UserMapper userMapper;

	//获取当前用户的部门id
	public String getUserdeptid() {
		return String.valueOf(ShiroKit.getUser().getDeptId());
	}

	private String userdeptid = "";

	//根据传经来的月份页面获取首页最上面的四个统计信息(从今天到本月第一天的数据)
	public Map<String, Object> getStatisticsData(String month) {
		//拿到用户的部门id,用于后面的查询
		userdeptid = getUserdeptid();
		//定义好返回对象
		Map<String, Object> result = new HashMap<String, Object>();
		//获取这个月所有的车流量
		Integer totalFlow = indexDataDao.getFixedsiTetotalFlow(userdeptid,month);
		//获取这个月所有超载的车流量
		Integer overloadCount = indexDataDao.getFixedsiOverloadFlow(userdeptid,month);
		//获取这个月所有处理了的车辆
		Integer handleCount = indexDataDao.getFixedsiteHandleCount(userdeptid,month);
		//获取这个月所有卸载的重量
		Integer unloadWeight = indexDataDao.getFixedsiteTotalUnloadWeight(userdeptid,month);
		//将拿到的数据存入返回对象
		result.put("totalFlow", totalFlow);
		result.put("overloadCount", overloadCount);
		result.put("handleCount", handleCount);
		result.put("unloadWeight", (unloadWeight == null? 0 : unloadWeight) / 1000);

		//这里是获取待办事项
		List<Map<String, Object>> notices = noticeDao.list(null);
		//定义新的返回对象,里面包含待办事项和数据统计数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//将数据添加进去
		resultMap.put("statisticsData", result);
		resultMap.put("notices", notices);
		//返回
		return resultMap;
	}

	/**
	 * 获取车流量信息,用于首页的图表展示
	 * @param thatDeptid	//需要查询的部门的id,如果为空则默认查询用户的部门例如 28
	 * @param dateArea		//查询的时间区域 例如 2017-05-04 ~ 2018-05-26
	 * @param type			//查询的数据类型,是向上级获取,还是向下钻取
	 * @return
	 */
	@Override
	public Object getTrafficFlow(String thatDeptid,String dateArea,String type) {

		//如果区域时间没有,可能是用户误点,不用返回对象
		if(ToolUtil.isEmpty(dateArea)) {
			return null;
		}
		//建立一个临时的部门id用来做查询条件,用于存储用户传进来的部门id,防止变量污染
        String thatDeptidV = thatDeptid;
		//获取用户的部门id ,在后面用于鉴权,如果传进来的部门id大于用户的部门id,则不需要返回
        userdeptid = getUserdeptid();
        //判断需要查询的部门id是否为空
		if(ToolUtil.isEmpty(thatDeptidV)) {	//如果部门id是不存在,则将需要查询的
			//将用户所属的部门id赋值给临时部门id
            thatDeptidV = userdeptid;
		}
		//创建返回list对象
		List<Map<String, Object>> resultList = new ArrayList<>();
		//判断需要查询的数据是向上查询吗(在战级双击柱子时应返回的是它父级的父级的数据,即市级的数据)
		if("upper".equals(type)) {
			//获取该部门的上级部门
            Map<String, Object> parentDept = indexDataDao.getParentDepts(thatDeptidV);
            //将临时部门id改成上级部门的id,用于后面的查询
            thatDeptidV = parentDept.get("id") + "";
            //如果没有查询到父级,则id可能为空,这里判断一下
            if(ToolUtil.isEmpty(thatDeptidV)) {	//如果id为空则不查询了,已经到顶级了,直接返回空
                return null;
            }
            //因为目前的顶级部门的id为40,所以这里判断他不等于顶级吗,没有则再向上找一层
			//(向上展示需要两层,所以在这里找两次)
            if(!"40".equals(thatDeptidV)) {
                parentDept = indexDataDao.getParentDepts(thatDeptidV);
                thatDeptidV = parentDept.get("id") + "";
            }
            //判断这次需要查的部门id是否在用户的权限范围之内
            if(hasPermission(thatDeptidV)) {	//有权限则根据这个部门id进行查找
                resultList = indexDataDao.getChildsDepts(thatDeptidV);
            } else {	//否则根据用户的部门id查找子级部门id
                resultList = indexDataDao.getChildsDepts(userdeptid);
            }

        } else if("down".equals(type)) {	//如果是向下查找,直接判断并获取部门列表
            if(hasPermission(thatDeptidV)) {
                resultList = indexDataDao.getChildsDepts(thatDeptidV);
            } else {
                resultList = indexDataDao.getChildsDepts(userdeptid);
            }
		}

		//防止到站一级时再进去一层变成空,
		if(resultList.size() < 1) {	//如果到了站一级,则不允许向下查找了,要不按照用户的部门查,要不找他的上一级部门
            Map<String, Object> parentDept = indexDataDao.getParentDepts(thatDeptidV);
            thatDeptidV = parentDept.get("id") + "";
            if(hasPermission(thatDeptidV)) {
                resultList = indexDataDao.getChildsDepts(thatDeptidV);
            } else {
                resultList = indexDataDao.getChildsDepts(userdeptid);
            }
        }


		//将传进来的时间区间进行分割
		String[] stringArr = dateArea.split("~");

		String beginTime = stringArr[0] + " 00:00:00";
		String endTime = stringArr[1] + " 23:59:59";
		//遍历上面查的到的子级部门id,然后分别查出每个部门的车辆情况
		for(Map hm : resultList) {
			String deptid = hm.get("id") + "";
			//查找车流量统计信息
            Map<String, Object> flowMap = indexDataDao.getTrafficFlow(deptid, beginTime, endTime);
            //将信息添加到需要返回的list中
            hm.put("sum",flowMap.get("sum"));
            hm.put("oversum",flowMap.get("oversum"));
        }

		//返回
		return resultList;


	}

	//已经废除,首页不需要展示车轴信息了
	@Override
	public Object getTrafficAxle(String dateArea) {
		if(ToolUtil.isEmpty(dateArea)) {
			return null;
		}
		userdeptid = getUserdeptid();
		//将传进来的时间区间进行分割
		String[] stringArr = dateArea.split("~");

		String beginTime = stringArr[0] + " 00:00:00";
		String endTime = stringArr[1] + " 23:59:59";

		return indexDataDao.getTrafficAxle(userdeptid,beginTime, endTime);
	}

	/**
	 * 自己写的一个用于时间区间选择分割和格式化的方法 分隔符为 ~
	 * @param dateArea
	 * @return
	 * @throws ParseException
	 */
	private String[] splitDate(String dateArea) throws ParseException {
		//将传进来的时间区间进行分割
		String[] stringArr = dateArea.split("~");
		//格式化输入的时间
		DateFormat inputDF = new SimpleDateFormat("yyy-MM");
		//用于格式化返回的时间
		DateFormat outDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//日期对象用于时间的处理
		Calendar calendar = Calendar.getInstance();
		//用于返回的String数组
		String[] resultArr = new String[2];

		//用于储存临时的时间变量
		//在这里存储第一个元素
		Date time = inputDF.parse(stringArr[0]);

		//设置返回的第一个元素
		resultArr[0] = outDF.format(time);

		//开始设置返回的第二个元素
		//将分割出来的第二个元素取出来,转换成日期类型
		time = inputDF.parse(stringArr[1]);
		//给calendar设定日期
		calendar.setTime(time);
		//给这个日期加一个月
		calendar.add(Calendar.MONTH,1);
		//然后减一秒钟  最后得到的应该格式当前月的最后一天的23:59:59
		calendar.add(Calendar.SECOND,-1);
		//获取到日期对象
		time = calendar.getTime();
		//将日期添加到底二个返回元素
		resultArr[1] = outDF.format(time);

		return resultArr;
	}

	/**
	 * 判断用户是否有该部门的操作权限
	 * @param thatDeptid 部门id
	 * @return
	 */
	public boolean hasPermission(String thatDeptid) {
		userdeptid = getUserdeptid();
		Map<String, Integer> resultMap = indexDataDao.hasDeptidPermission(userdeptid, thatDeptid);

		if(ToolUtil.isEmpty(resultMap)) {
		    return false;
        } else {
            String pid = resultMap.get("id") + "";
            return thatDeptid.equals(pid);
        }

	}



}
