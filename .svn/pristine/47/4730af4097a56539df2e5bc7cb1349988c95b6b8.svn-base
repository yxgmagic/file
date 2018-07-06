package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据节点终端配置表
 * </p>
 *
 * @author zqf
 * @since 2018-02-27
 */
@TableName("dms_server_list")
public class ServerList extends Model<ServerList> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 数据终端别名
     */
	private String sysalias;
    /**
     * 数据终端描述
     */
	private String sysdesc;
    /**
     * 数据服务类型
     */
	private String systype;
    /**
     * 数据来源去向
     */
	private String syssource;
    /**
     * 网络地址
     */
	private String host;
    /**
     * 端口
     */
	private Integer port;
    /**
     * 数据库名或服务名
     */
	private String dbname;
    /**
     * 数据驱动类名
     */
	private String dbdriver;
    /**
     * 访问用户
     */
	private String dbuser;
    /**
     * 访问密码
     */
	private String dbpassword;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSysalias() {
		return sysalias;
	}

	public void setSysalias(String sysalias) {
		this.sysalias = sysalias;
	}

	public String getSysdesc() {
		return sysdesc;
	}

	public void setSysdesc(String sysdesc) {
		this.sysdesc = sysdesc;
	}

	public String getSystype() {
		return systype;
	}

	public void setSystype(String systype) {
		this.systype = systype;
	}

	public String getSyssource() {
		return syssource;
	}

	public void setSyssource(String syssource) {
		this.syssource = syssource;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbdriver() {
		return dbdriver;
	}

	public void setDbdriver(String dbdriver) {
		this.dbdriver = dbdriver;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ServerList{" +
			"id=" + id +
			", sysalias=" + sysalias +
			", sysdesc=" + sysdesc +
			", systype=" + systype +
			", syssource=" + syssource +
			", host=" + host +
			", port=" + port +
			", dbname=" + dbname +
			", dbdriver=" + dbdriver +
			", dbuser=" + dbuser +
			", dbpassword=" + dbpassword +
			"}";
	}
}
