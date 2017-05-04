package cn.edu.dlut.career.dto.school;

import javax.persistence.Column;

/**
 *  派遣信息
 * Created by HealerJean on 2017/4/19.
 */
public class DispatchDTO {
    private  String stuNo;

    private  String stuName;

    private  String majorCode;

    private String majorName;

    private  String eduDegree;

    private String eduMode;


    //单位名称
    private  String recName;

    //报到地址
    private  String recAddress;

    //初审
    private  String startStatus ;

    //终审
    private  String endStatus ;


    public DispatchDTO() {
    }

    public DispatchDTO(String stuNo, String stuName, String majorCode, String majorName, String eduDegree, String eduMode,  String recName, String recAddress, String startStatus, String endStatus) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.majorCode = majorCode;
        this.majorName = majorName;
        this.eduDegree = eduDegree;
        this.eduMode = eduMode;
        this.recName = recName;
        this.recAddress = recAddress;
        this.startStatus = startStatus;
        this.endStatus = endStatus;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
    }

    public String getEduMode() {
        return eduMode;
    }

    public void setEduMode(String eduMode) {
        this.eduMode = eduMode;
    }



    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getStartStatus() {
        return startStatus;
    }

    public void setStartStatus(String startStatus) {
        this.startStatus = startStatus;
    }

    public String getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(String endStatus) {
        this.endStatus = endStatus;
    }
}
