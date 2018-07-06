package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhichao
 * @since 2018-02-25
 */
@TableName("dms_server_auth")
public class ServerAuth extends Model<ServerAuth> {

    private static final long serialVersionUID = 1L;
	 

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 服务代码
     */
	private String alias;
    /**
     * 服务别名
     */
	@TableField("alias_name")
	private String aliasName;
    /**
     * 网络地址
     */
	private String url;
    /**
     * 电脑名称
     */
	@TableField("computer_name")
	private String computerName;
    /**
     * 硬盘号
     */
	private String diskno;
    /**
     * 网卡地址
     */
	private String macno;
    /**
     * 开始时间
     */
	private String begindt;
    /**
     * 结束时间
     */
	private String enddt;
    /**
     * 注册码
     */
	private String license;
    /**
     * CDKEY
     */
	private String cdkey;
    /**
     * 有效状态
     */
	private String status;
	@TableField("audit_status")
	private String auditStatus;
    /**
     * 用户编码
     */
	private String usercode;
    /**
     * 用户密码
     */
	private String userpassword;
    /**
     * 创建时间
     */
	private String creatdt;
    /**
     * 创建人
     */
	private Integer creatid;
    /**
     * 审核时间
     */
	private String auditdt;
    /**
     * 审核人
     */
	private Integer auditid;
    /**
     * 所属部门
     */
	private Integer deptid;
    /**
     * 修改时间
     */
	private String updt;
    /**
     * 修改人
     */
	private Integer upid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getDiskno() {
		return diskno;
	}

	public void setDiskno(String diskno) {
		this.diskno = diskno;
	}

	public String getMacno() {
		return macno;
	}

	public void setMacno(String macno) {
		this.macno = macno;
	}

	public String getBegindt() {
		return begindt;
	}

	public void setBegindt(String begindt) {
		this.begindt = begindt;
	}

	public String getEnddt() {
		return enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCdkey() {
		return cdkey;
	}

	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getCreatdt() {
		return creatdt;
	}

	public void setCreatdt(String creatdt) {
		this.creatdt = creatdt;
	}

	public Integer getCreatid() {
		return creatid;
	}

	public void setCreatid(Integer creatid) {
		this.creatid = creatid;
	}

	public String getAuditdt() {
		return auditdt;
	}

	public void setAuditdt(String auditdt) {
		this.auditdt = auditdt;
	}

	public Integer getAuditid() {
		return auditid;
	}

	public void setAuditid(Integer auditid) {
		this.auditid = auditid;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getUpdt() {
		return updt;
	}

	public void setUpdt(String updt) {
		this.updt = updt;
	}

	public Integer getUpid() {
		return upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ServerAuth{" +
			"id=" + id +
			", alias=" + alias +
			", aliasName=" + aliasName +
			", url=" + url +
			", computerName=" + computerName +
			", diskno=" + diskno +
			", macno=" + macno +
			", begindt=" + begindt +
			", enddt=" + enddt +
			", license=" + license +
			", cdkey=" + cdkey +
			", status=" + status +
			", auditStatus=" + auditStatus +
			", usercode=" + usercode +
			", userpassword=" + userpassword +
			", creatdt=" + creatdt +
			", creatid=" + creatid +
			", auditdt=" + auditdt +
			", auditid=" + auditid +
			", deptid=" + deptid +
			", updt=" + updt +
			", upid=" + upid +
			"}";
	}
}
