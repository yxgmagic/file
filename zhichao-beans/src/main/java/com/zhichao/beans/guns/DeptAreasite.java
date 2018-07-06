package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户部门区域站点权限表
 * </p>
 *
 * @author zqf
 * @since 2018-01-25
 */
@TableName("sys_dept_areasite")
public class DeptAreasite extends Model<DeptAreasite> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 部门编号
     */
	private Integer deptid;
    /**
     * 区域或站点编号
     */
	private String areasitecode;
    /**
     * 权限类型
     */
	private String areasitetype;
    /**
     * 新增权限
     */
	private String authadd;
    /**
     * 删除权限
     */
	private String authdelete;
    /**
     * 查询权限
     */
	private String authselect;
    /**
     * 修改权限
     */
	private String authupdate;
    /**
     * 创建时间
     */
	private Date crtdate;
    /**
     * 创建人
     */
	private Integer crtuserid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getAreasitecode() {
		return areasitecode;
	}

	public void setAreasitecode(String areasitecode) {
		this.areasitecode = areasitecode;
	}

	public String getAreasitetype() {
		return areasitetype;
	}

	public void setAreasitetype(String areasitetype) {
		this.areasitetype = areasitetype;
	}

	public String getAuthadd() {
		return authadd;
	}

	public void setAuthadd(String authadd) {
		this.authadd = authadd;
	}

	public String getAuthdelete() {
		return authdelete;
	}

	public void setAuthdelete(String authdelete) {
		this.authdelete = authdelete;
	}

	public String getAuthselect() {
		return authselect;
	}

	public void setAuthselect(String authselect) {
		this.authselect = authselect;
	}

	public String getAuthupdate() {
		return authupdate;
	}

	public void setAuthupdate(String authupdate) {
		this.authupdate = authupdate;
	}

	public Date getCrtdate() {
		return crtdate;
	}

	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
	}

	public Integer getCrtuserid() {
		return crtuserid;
	}

	public void setCrtuserid(Integer crtuserid) {
		this.crtuserid = crtuserid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DeptAreasite{" +
			"id=" + id +
			", deptid=" + deptid +
			", areasitecode=" + areasitecode +
			", areasitetype=" + areasitetype +
			", authadd=" + authadd +
			", authdelete=" + authdelete +
			", authselect=" + authselect +
			", authupdate=" + authupdate +
			", crtdate=" + crtdate +
			", crtuserid=" + crtuserid +
			"}";
	}
}
