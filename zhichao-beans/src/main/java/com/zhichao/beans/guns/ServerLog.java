package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 数据服务终端日志
 * </p>
 *
 * @author zqf
 * @since 2018-02-26
 */
@TableName("dms_server_log")
public class ServerLog extends Model<ServerLog> {

    private static final long serialVersionUID = 1L;
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据服务
     */
	private Integer authid;
    /**
     * 日志触发类
     */
	private String classname;
    /**
     * 触发方法
     */
	private String method;
    /**
     * 日志时间
     */
	private String logtime;
    /**
     * 日志内容
     */
	private String logtext;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthid() {
		return authid;
	}

	public void setAuthid(Integer authid) {
		this.authid = authid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getLogtime() {
		return logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public String getLogtext() {
		return logtext;
	}

	public void setLogtext(String logtext) {
		this.logtext = logtext;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ServerLog{" +
			"id=" + id +
			", authid=" + authid +
			", classname=" + classname +
			", method=" + method +
			", logtime=" + logtime +
			", logtext=" + logtext +
			"}";
	}
}
