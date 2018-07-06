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
 * 货物转运
 * </p>
 *
 * @author fengshuonan
 * @since 2018-01-30
 */
@TableName("b_make_cargo")
public class MakeCargo extends Model<MakeCargo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 接货单号
     */
	private String makecargono;
    /**
     * 车牌号
     */
	private String makevehicleid;
    /**
     * 接货车辆联系人
     */
	private String makevehicleman;
    /**
     * 接货车辆联系方式
     */
	private String makevehicletel;
    /**
     * 接货重量
     */
	private Integer makecargoweight;
    /**
     * 接货日期
     */
	private Date makecargodate;
    /**
     * 暂扣单号
     */
	private String withholdno;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMakecargono() {
		return makecargono;
	}

	public void setMakecargono(String makecargono) {
		this.makecargono = makecargono;
	}

	public String getMakevehicleid() {
		return makevehicleid;
	}

	public void setMakevehicleid(String makevehicleid) {
		this.makevehicleid = makevehicleid;
	}

	public String getMakevehicleman() {
		return makevehicleman;
	}

	public void setMakevehicleman(String makevehicleman) {
		this.makevehicleman = makevehicleman;
	}

	public String getMakevehicletel() {
		return makevehicletel;
	}

	public void setMakevehicletel(String makevehicletel) {
		this.makevehicletel = makevehicletel;
	}

	public Integer getMakecargoweight() {
		return makecargoweight;
	}

	public void setMakecargoweight(Integer makecargoweight) {
		this.makecargoweight = makecargoweight;
	}

	public Date getMakecargodate() {
		return makecargodate;
	}

	public void setMakecargodate(Date makecargodate) {
		this.makecargodate = makecargodate;
	}

	public String getWithholdno() {
		return withholdno;
	}

	public void setWithholdno(String withholdno) {
		this.withholdno = withholdno;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MakeCargo{" +
			"id=" + id +
			", makecargono=" + makecargono +
			", makevehicleid=" + makevehicleid +
			", makevehicleman=" + makevehicleman +
			", makevehicletel=" + makevehicletel +
			", makecargoweight=" + makecargoweight +
			", makecargodate=" + makecargodate +
			", withholdno=" + withholdno +
			"}";
	}
}
