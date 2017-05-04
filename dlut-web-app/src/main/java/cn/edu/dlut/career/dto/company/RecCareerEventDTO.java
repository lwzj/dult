package cn.edu.dlut.career.dto.company;

import java.io.Serializable;
import java.net.ServerSocket;
import java.util.UUID;

/**
 * 组团预约申请 表对应DTO
 * Created by HealerJean on 2017/4/10.
 */
public class RecCareerEventDTO implements Serializable {
    private static final long SERIVALVERSIONUID = 876280513342148585L;
    private UUID id;
    //招聘会名称
    private String fairName;

    private String applicationTime;

    //开始时间
    private String fairStartTime;

    //结束时间
    private String fairEndTime;

    //企业名称
    private String recName;

    //企业所在地
    private String recAddress;

    //联系人
    private String contacts;

    //联系人联系电话
    private String conTel;

    //联系人邮箱
    private String conEmail;

    //场地要求/类型(多媒体教室、机房...)
    private String areaRequire;

    //场地规模(0-50人，50-100人，....)
    private String areaSize;

    //场地数量
    private Integer areaNum;

    //场地用途
    private String areaUsing;

    //场地地址
    private String areaAddress;

    //场地费用
    private Float areaCost;

    //接待人
    private String receiver;

    //接待人联系方式
    private String receiverTel;


    //缴费方式(线上，线下)
    private String payType;

    //备注
    private String remarks;
    public RecCareerEventDTO() {
    }
    public static long getSERIVALVERSIONUID() {
        return SERIVALVERSIONUID;
    }


    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFairName() {
        return fairName;
    }

    public void setFairName(String fairName) {
        this.fairName = fairName;
    }

    public String getFairStartTime() {
        return fairStartTime;
    }

    public void setFairStartTime(String fairStartTime) {
        this.fairStartTime = fairStartTime;
    }

    public String getFairEndTime() {
        return fairEndTime;
    }

    public void setFairEndTime(String fairEndTime) {
        this.fairEndTime = fairEndTime;
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getAreaRequire() {
        return areaRequire;
    }

    public void setAreaRequire(String areaRequire) {
        this.areaRequire = areaRequire;
    }

    public String getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(String areaSize) {
        this.areaSize = areaSize;
    }

    public Integer getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(Integer areaNum) {
        this.areaNum = areaNum;
    }

    public String getAreaUsing() {
        return areaUsing;
    }

    public void setAreaUsing(String areaUsing) {
        this.areaUsing = areaUsing;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public Float getAreaCost() {
        return areaCost;
    }

    public void setAreaCost(Float areaCost) {
        this.areaCost = areaCost;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
