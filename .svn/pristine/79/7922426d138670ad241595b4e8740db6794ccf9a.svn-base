package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 考评指标数据
 * </p>
 *
 * @author zhichao
 * @since 2018-03-06
 */
@TableName("pe_site_indic")
public class PeSiteIndic extends Model<PeSiteIndic> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 站点编号
     */
	@TableField("site_id")
	private Integer siteId;
    /**
     * 指标编号
     */
	@TableField("indic_id")
	private Integer indicId;
    /**
     * 时间
     */
	private Date time;
	/**
	 * 抓拍图片
	 */
	private String url;
    /**
     * 备注
     */
	private String notes;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PeSiteIndic{" +
				"id=" + id +
				", siteId=" + siteId +
				", indicId=" + indicId +
				", time=" + time +
				", url='" + url + '\'' +
				", notes='" + notes + '\'' +
				'}';
	}
}
