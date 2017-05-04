package cn.edu.dlut.career.domain.company;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by wei on 2017/3/28.
 * offer模板
 */
@Entity
@Table(name = "offer_template")
public class OfferTemplate {
    //编号，主键UUID
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private UUID id;
    //职位类别
    @Column(length = 100)
    private String jobType;
    //适用学生类型
    @Column(length = 100)
    private String stuType;
    //截止日期
    @Column
    private LocalDateTime endTime;
    //offer说明
    @Column(length = 100)
    private String remarks;
    //是否需要报到证 1代表需要 0代表不需要
    @Column(length = 100)
    private String isreportCard;
    //报到证打印单位名称
    @Column(length = 100)
    private String repAddress;
    //是否解决档案 1代表解决 0代表不解决
    @Column(length = 100)
    private String isArchives;
    //档案转移单位名称
    @Column(length = 100)
    private String arcRecName;
    //档案转移详细地址
    @Column(length = 100)
    private String arcAddress;
    //是否解决户口 1代表解决 0代表不解决
    @Column(length = 100)
    private String isReg;
    //是否允许不迁户口 1代表迁移 0代表不迁移
    @Column(length = 100)
    private String transferAccount;
    //户口迁移地址
    @Column(length = 100)
    private String regAddress;
    //单位所在地址
    @Column(length = 100)
    private String recAddress;
    //档案转移部门
    @Column(length = 100)
    private String dep;
    //部门编号
    @Column(length = 100)
    private String deoId;

    public OfferTemplate() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsreportCard() {
        return isreportCard;
    }

    public void setIsreportCard(String isreportCard) {
        this.isreportCard = isreportCard;
    }

    public String getRepAddress() {
        return repAddress;
    }

    public void setRepAddress(String repAddress) {
        this.repAddress = repAddress;
    }

    public String getIsArchives() {
        return isArchives;
    }

    public void setIsArchives(String isArchives) {
        this.isArchives = isArchives;
    }

    public String getArcRecName() {
        return arcRecName;
    }

    public void setArcRecName(String arcRecName) {
        this.arcRecName = arcRecName;
    }

    public String getArcAddress() {
        return arcAddress;
    }

    public void setArcAddress(String arcAddress) {
        this.arcAddress = arcAddress;
    }

    public String getIsReg() {
        return isReg;
    }

    public void setIsReg(String isReg) {
        this.isReg = isReg;
    }

    public String getTransferAccount() {
        return transferAccount;
    }

    public void setTransferAccount(String transferAccount) {
        this.transferAccount = transferAccount;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDeoId() {
        return deoId;
    }

    public void setDeoId(String deoId) {
        this.deoId = deoId;
    }
}
