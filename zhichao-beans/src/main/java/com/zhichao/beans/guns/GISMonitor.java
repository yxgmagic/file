package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * GIS地图监控
 * @author yice
 * @since 2018-01-02
 */
@TableName("bs_fixedsite")
public class GISMonitor extends Model<GISMonitor> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 站点编号
     */
	private String sitecode;
    /**
     * 固定治超检测站名称
     */
	private String sitename;
    /**
     * 治超站等级
     */
	private Integer sitelevel;
    /**
     * 经度
     */
	private BigDecimal longitude;
    /**
     * 维度
     */
	private BigDecimal latitude;
    /**
     * 地址
     */
	private String address;
    /**
     * 规划面积
     */
	private BigDecimal planarea;
    /**
     * 卸货厂面积
     */
	private BigDecimal unloadarea;
    /**
     * 所属区域
     */
	private String areacode;
    /**
     * 所属道路
     */
	private String roadcode;
    /**
     * 管理部门
     */
	private Integer deptid;
    /**
     * 法定代表人
     */
	private String lawperson;
    /**
     * 法定代表人联系电话
     */
	private String lawpersontel;
    /**
     * 设置时间
     */
	private String setbegindate;
    /**
     * 卸货场
     */
	private String unloadcode;
    /**
     * 卸货场联系人
     */
	private String unloadperson;
    /**
     * 卸货场联系人电话
     */
	private String unloadpersontel;
    /**
     * 站点负责人
     */
	private String manager;
    /**
     * 站点负责人联系方式
     */
	private String managertel;
    /**
     * 核定编制
     */
	private Integer approves;
    /**
     * 实配人数
     */
	private Integer actuals;
    /**
     * 检测人数
     */
	private Integer checks;
    /**
     * 合同制人数
     */
	private Integer contracts;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public Integer getSitelevel() {
		return sitelevel;
	}

	public void setSitelevel(Integer sitelevel) {
		this.sitelevel = sitelevel;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPlanarea() {
		return planarea;
	}

	public void setPlanarea(BigDecimal planarea) {
		this.planarea = planarea;
	}

	public BigDecimal getUnloadarea() {
		return unloadarea;
	}

	public void setUnloadarea(BigDecimal unloadarea) {
		this.unloadarea = unloadarea;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getRoadcode() {
		return roadcode;
	}

	public void setRoadcode(String roadcode) {
		this.roadcode = roadcode;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getLawperson() {
		return lawperson;
	}

	public void setLawperson(String lawperson) {
		this.lawperson = lawperson;
	}

	public String getLawpersontel() {
		return lawpersontel;
	}

	public void setLawpersontel(String lawpersontel) {
		this.lawpersontel = lawpersontel;
	}

	public String getSetbegindate() {
		return setbegindate;
	}

	public void setSetbegindate(String setbegindate) {
		this.setbegindate = setbegindate;
	}

	public String getUnloadcode() {
		return unloadcode;
	}

	public void setUnloadcode(String unloadcode) {
		this.unloadcode = unloadcode;
	}

	public String getUnloadperson() {
		return unloadperson;
	}

	public void setUnloadperson(String unloadperson) {
		this.unloadperson = unloadperson;
	}

	public String getUnloadpersontel() {
		return unloadpersontel;
	}

	public void setUnloadpersontel(String unloadpersontel) {
		this.unloadpersontel = unloadpersontel;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagertel() {
		return managertel;
	}

	public void setManagertel(String managertel) {
		this.managertel = managertel;
	}

	public Integer getApproves() {
		return approves;
	}

	public void setApproves(Integer approves) {
		this.approves = approves;
	}

	public Integer getActuals() {
		return actuals;
	}

	public void setActuals(Integer actuals) {
		this.actuals = actuals;
	}

	public Integer getChecks() {
		return checks;
	}

	public void setChecks(Integer checks) {
		this.checks = checks;
	}

	public Integer getContracts() {
		return contracts;
	}

	public void setContracts(Integer contracts) {
		this.contracts = contracts;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Fixedsite{" +
			"id=" + id +
			", sitecode=" + sitecode +
			", sitename=" + sitename +
			", sitelevel=" + sitelevel +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", address=" + address +
			", planarea=" + planarea +
			", unloadarea=" + unloadarea +
			", areacode=" + areacode +
			", roadcode=" + roadcode +
			", deptid=" + deptid +
			", lawperson=" + lawperson +
			", lawpersontel=" + lawpersontel +
			", setbegindate=" + setbegindate +
			", unloadcode=" + unloadcode +
			", unloadperson=" + unloadperson +
			", unloadpersontel=" + unloadpersontel +
			", manager=" + manager +
			", managertel=" + managertel +
			", approves=" + approves +
			", actuals=" + actuals +
			", checks=" + checks +
			", contracts=" + contracts +
			"}";
	}
}
