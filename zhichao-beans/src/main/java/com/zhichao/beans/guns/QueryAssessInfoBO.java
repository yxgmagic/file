package com.zhichao.beans.guns;

import java.io.Serializable;

/**
 * 查询考核详情返回结果的BO类
 * by imyzt
 * date 2018年3月14日17:50:08
 */
public class QueryAssessInfoBO implements Serializable{

    /**
     * 编号
     */
    private Integer id;
    /**
     * 计划id
     */
    private Integer assessId;
    /**
     * 站点id
     */
    private Integer siteId;
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 制度指标得分
     */
    private Integer institution;
    /**
     * 站点量化指标得分
     */
    private Integer quantitative;
    /**
     * 总分
     */
    private Integer score;
    /**
     * 预留字段
     */
    private String notes;

    @Override
    public String toString() {
        return "QueryAssessInfoBO{" +
                "id=" + id +
                ", assessId=" + assessId +
                ", siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", institution=" + institution +
                ", quantitative=" + quantitative +
                ", score=" + score +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Integer getAssessId() {
        return assessId;
    }

    public void setAssessId(Integer assessId) {
        this.assessId = assessId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getInstitution() {
        return institution;
    }

    public void setInstitution(Integer institution) {
        this.institution = institution;
    }

    public Integer getQuantitative() {
        return quantitative;
    }

    public void setQuantitative(Integer quantitative) {
        this.quantitative = quantitative;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
