package com.zhichao.beans.guns;

import java.util.Date;

/**
 * Hlcminfo,用来分页用的
 */
public class Hlcminfo {
    private Integer id;
    private Date fctime;
    private String areacode;
    private String sitename;
    private String sitetype;
    private String checkmode;
    private String vehicleid;
    private Integer axlesum;
    private Integer ratedloading;
    private Integer fctotalweight;
    private Integer overlimited;
    private Integer overrate;
    private String isoverlimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFctime() {
        return fctime;
    }

    public void setFctime(Date fctime) {
        this.fctime = fctime;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSitetype() {
        return sitetype;
    }

    public void setSitetype(String sitetype) {
        this.sitetype = sitetype;
    }

    public String getCheckmode() {
        return checkmode;
    }

    public void setCheckmode(String checkmode) {
        this.checkmode = checkmode;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public Integer getAxlesum() {
        return axlesum;
    }

    public void setAxlesum(Integer axlesum) {
        this.axlesum = axlesum;
    }

    public Integer getRatedloading() {
        return ratedloading;
    }

    public void setRatedloading(Integer ratedloading) {
        this.ratedloading = ratedloading;
    }

    public Integer getFctotalweight() {
        return fctotalweight;
    }

    public void setFctotalweight(Integer fctotalweight) {
        this.fctotalweight = fctotalweight;
    }

    public Integer getOverlimited() {
        return overlimited;
    }

    public void setOverlimited(Integer overlimited) {
        this.overlimited = overlimited;
    }

    public Integer getOverrate() {
        return overrate;
    }

    public void setOverrate(Integer overrate) {
        this.overrate = overrate;
    }

    public String getIsoverlimit() {
        return isoverlimit;
    }

    public void setIsoverlimit(String isoverlimit) {
        this.isoverlimit = isoverlimit;
    }

    public Hlcminfo(Integer id, Date fctime, String areacode, String sitename, String sitetype, String checkmode, String vehicleid, Integer axlesum, Integer ratedloading, Integer fctotalweight, Integer overlimited, Integer overrate, String isoverlimit) {
        this.id = id;
        this.fctime = fctime;
        this.areacode = areacode;
        this.sitename = sitename;
        this.sitetype = sitetype;
        this.checkmode = checkmode;
        this.vehicleid = vehicleid;
        this.axlesum = axlesum;
        this.ratedloading = ratedloading;
        this.fctotalweight = fctotalweight;
        this.overlimited = overlimited;
        this.overrate = overrate;
        this.isoverlimit = isoverlimit;
    }

    public Hlcminfo() {
    }

    @Override
    public String toString() {
        return "Hlcminfo{" +
                "id=" + id +
                ", fctime=" + fctime +
                ", areacode='" + areacode + '\'' +
                ", sitename='" + sitename + '\'' +
                ", sitetype='" + sitetype + '\'' +
                ", checkmode='" + checkmode + '\'' +
                ", vehicleid='" + vehicleid + '\'' +
                ", axlesum=" + axlesum +
                ", ratedloading=" + ratedloading +
                ", fctotalweight=" + fctotalweight +
                ", overlimited=" + overlimited +
                ", overrate=" + overrate +
                ", isoverlimit=" + isoverlimit +
                '}';
    }
}
