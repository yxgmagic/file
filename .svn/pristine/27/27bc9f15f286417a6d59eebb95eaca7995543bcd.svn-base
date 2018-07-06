package com.zhichao.beans.flow;

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
 * 流程表
 * </p>
 *
 * @author yanjiangjie123
 * @since 2018-06-07
 */
@TableName("bas_flw_workflow")
public class BasFlwWorkflow extends Model<BasFlwWorkflow> {

    private static final long serialVersionUID = 1L;

    /**
     * 流程ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 流程创建json
     */
    @TableField("flow_json")
    private String flowJson;
    /**
     * 创建人ID
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_Date")
    private Date createDate;
    /**
     * 最后更新人ID
     */
    @TableField("updata_by")
    private String updataBy;
    /**
     * 最后更新时间
     */
    @TableField("updata_date")
    private Date updataDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态 1 初始化 2 启动 3 废除
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlowJson() {
        return flowJson;
    }

    public void setFlowJson(String flowJson) {
        this.flowJson = flowJson;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdataBy() {
        return updataBy;
    }

    public void setUpdataBy(String updataBy) {
        this.updataBy = updataBy;
    }

    public Date getUpdataDate() {
        return updataDate;
    }

    public void setUpdataDate(Date updataDate) {
        this.updataDate = updataDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BasFlwWorkflow{" +
        "id=" + id +
        ", name=" + name +
        ", flowJson=" + flowJson +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        ", updataBy=" + updataBy +
        ", updataDate=" + updataDate +
        ", remark=" + remark +
        ", state=" + state +
        "}";
    }
}
