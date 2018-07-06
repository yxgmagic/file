package com.zhichao.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 对外接口，标准返回实体
 * 
 * @author yanjiangjie
 */
@ApiModel(value = "对外接口，标准返回实体")
public class OutsideAPIResult {

	@ApiModelProperty(value = "resCode : 返回代码，01成功，02失败，03参数验证异常")
	private String resCode = "01";

	@ApiModelProperty(value = "errInfo : 默认显示成功。 请求失败时候，返回具体的错误信息。")
	private String errInfo = "成功！";

	@ApiModelProperty(value = "orderId : 本次请求生成的消费id")
	private String orderId = null;

	/**
	 * 带错误类型和信息的构造方法 Creates a new instance of OutsideAPIResult.
	 *
	 * @param resCode
	 * @param errInfo
	 */
	public OutsideAPIResult(String resCode, String errInfo) {
		this.resCode = resCode;
		this.errInfo = errInfo;
	}

	/**
	 * 请求成功的构造方法 Creates a new instance of OutsideAPIResult.
	 *
	 * @param orderTid
	 */
	public OutsideAPIResult(String orderTid) {
		this.orderId = orderTid;
	}

	/**
	 * resCode.
	 *
	 * @return the resCode
	 * @since JDK 1.6
	 */
	public String getResCode() {
		return resCode;
	}

	/**
	 * resCode.
	 *
	 * @param resCode
	 *            the resCode to set
	 * @since JDK 1.6
	 */
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	/**
	 * errInfo.
	 *
	 * @return the errInfo
	 * @since JDK 1.6
	 */
	public String getErrInfo() {
		return errInfo;
	}

	/**
	 * errInfo.
	 *
	 * @param errInfo
	 *            the errInfo to set
	 * @since JDK 1.6
	 */
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	/**
	 * orderId.
	 *
	 * @return the orderId
	 * @since JDK 1.6
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * orderId.
	 *
	 * @param orderId
	 *            the orderId to set
	 * @since JDK 1.6
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
