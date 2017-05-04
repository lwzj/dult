package cn.edu.dlut.career.domain.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/24.
 *
 * 专场招聘会预约 实体类
 */
@Entity
@Table(name = "rec_career_event")
public class RecCareerEvent {

    //主键
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;

    //招聘简章id
    @Column(name = "bulletin_id", nullable = false)
    private UUID bulletinId;

    //招聘会名称
    @Column(name = "fair_name", length = 100, nullable = false)
    private String fairName;

    //招聘会开始时间
    @Column(name = "fair_start_time", nullable = false)
    private LocalDateTime fairStartTime;

    //招聘会结束时间
    @Column(name = "fair_end_time", nullable = false)
    private LocalDateTime fairEndTime;

    //企业id
    @Column(name = "rec_id", nullable = false)
    private UUID recId;

    //企业名称
    @Column(name = "rec_name", length = 100, nullable = false)
    private String recName;

    //企业所在地
    @Column(name = "rec_address", length = 100, nullable = false)
    private String recAddress;

    //联系人
    @Column(name = "contacts", length = 50, nullable = false)
    private String contacts;

    //联系人联系电话
    @Column(name = "con_tel", length = 20, nullable = false)
    private String conTel;

    //联系人邮箱
    @Column(name = "con_email", length = 100, nullable = false)
    private String conEmail;

    //场地要求/类型(多媒体教室、机房...)
    @Column(name = "area_require", length = 3, nullable = false)
    private String areaRequire;

    //场地规模(0-50人，50-100人，....)
    @Column(name = "area_size", length = 3, nullable = false)
    private String areaSize;

    //场地数量
    @Column(name = "area_num", nullable = false)
    private Integer areaNum;

    //场地用途
    @Column(name = "area_using", length = 100, nullable = false)
    private String areaUsing;

    //审核状态（0待处理，1借用中，2已落实，3已回复，4未落实 X废弃不用）（0待审核，1审核不通过，2审核通过提醒缴费，3未交费，4已缴费）
    @Column(name = "audit_status", length = 3, nullable = false)
    private String auditStatus;

    //审批意见
    @Column(name = "audit_suggest", length = 1000, nullable = true)
    private String auditSuggest;

    //场地地址
    @Column(name = "area_address", length = 100, nullable = true)
    private String areaAddress;

    //场地费用
    @Column(name = "area_cost", nullable = true)
    private Float areaCost;

    //接待人
    @Column(name = "receiver", length = 50, nullable = true)
    private String receiver;

    //接待人联系方式
    @Column(name = "receiver_tel", length = 20, nullable = true)
    private String receiverTel;

    //审核时间
    @Column(name = "audit_time", nullable = true)
    private LocalDateTime auditTime;

    //审核人
    @Column(length = 50, nullable = true)
    private String auditor;

    //缴费方式(线上，线下)
    @Column(name = "pay_type", length = 3, nullable = true)
    private String payType;

    //缴费时间
    @Column(name = "pay_time", nullable = true)
    private LocalDateTime payTime;

    //申请日期
    @Column(name = "application_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime applicationTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    //备注
    @Column(name = "remarks", length = 1000, nullable = true)
    private String remarks;

    public RecCareerEvent() {
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(UUID bulletinId) {
        this.bulletinId = bulletinId;
    }

    public void setRecId(UUID recId) {
        this.recId = recId;
    }

    public String getFairName() {
        return fairName;
    }

    public void setFairName(String fairName) {
        this.fairName = fairName;
    }

    public LocalDateTime getFairStartTime() {
        return fairStartTime;
    }

    public void setFairStartTime(LocalDateTime fairStartTime) {
        this.fairStartTime = fairStartTime;
    }

    public LocalDateTime getFairEndTime() {
        return fairEndTime;
    }

    public void setFairEndTime(LocalDateTime fairEndTime) {
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

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
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

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(LocalDateTime applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
