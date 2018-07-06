package com.zhichao.beans.guns;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 视频设备信息表
 * </p>
 *
 * @author zjl
 * @since 2018-01-25
 */
@TableName("bs_video_device")
public class VideoDevice extends Model<VideoDevice> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String deviceName;
	private String deviceId;
	private String deviceIp;
	private String devicePort;
	private String deviceParentName;
	private String deviceParentId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDevicePort() {
		return devicePort;
	}

	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}

	public String getDeviceParentName() {
		return deviceParentName;
	}

	public void setDeviceParentName(String deviceParentName) {
		this.deviceParentName = deviceParentName;
	}

	public String getDeviceParentId() {
		return deviceParentId;
	}

	public void setDeviceParentId(String deviceParentId) {
		this.deviceParentId = deviceParentId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "VideoDevice{" +
			"id=" + id +
			", deviceName=" + deviceName +
			", deviceId=" + deviceId +
			", deviceIp=" + deviceIp +
			", devicePort=" + devicePort +
			", deviceParentName=" + deviceParentName +
			", deviceParentId=" + deviceParentId +
			"}";
	}
}
