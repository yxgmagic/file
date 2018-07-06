package com.zhichao.beans.employee;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
/**
 * <p>
 * 雇员信息
 * </p>
 *
 * @author yanxingui
 * @since 2018-06-27
 */
@TableName("emp")
public class Emp extends Model<Emp> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value="empno", type= IdType.AUTO)
	private Integer empno;
	private String ename;
	private String job;
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date hiredate;
	private Integer deptno;
	private Integer status;
	private String url;
	
	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", hiredate=" + hiredate + ", deptno="
				+ deptno + ", status=" + status + ", url = " + url + "]";
	}
}
