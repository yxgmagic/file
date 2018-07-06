package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhichao
 * @since 2018-01-24
 */
@TableName("bs_video_server")
public class VideoServer extends Model<VideoServer> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户名
     */
	private String uname;
    /**
     * 密码
     */
	private String upass;
    /**
     * 管理IP地址
     */
	private String serverip;
    /**
     * 管理端口
     */
	private String serverport;
    /**
     * 服务器类型
     */
	private Integer flag;
    /**
     * 标记
     */
	private Integer verify;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpass() {
		return upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	public String getServerip() {
		return serverip;
	}

	public void setServerip(String serverip) {
		this.serverip = serverip;
	}

	public String getServerport() {
		return serverport;
	}

	public void setServerport(String serverport) {
		this.serverport = serverport;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "VideoServer{" +
			"id=" + id +
			", uname=" + uname +
			", upass=" + upass +
			", serverip=" + serverip +
			", serverport=" + serverport +
			", flag=" + flag +
			", verify=" + verify +
			"}";
	}
}
