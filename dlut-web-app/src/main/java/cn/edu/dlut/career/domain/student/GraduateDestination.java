package cn.edu.dlut.career.domain.student;

import cn.edu.dlut.career.util.PubCodeUtil;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 毕业去向表 Graduate destination
 */
@Entity
@Table(name = "graduate_destination")
public class GraduateDestination {
    // 编号，主键UUID

    @Id
    @GenericGenerator(name = "myForeignGenerator", strategy = "foreign",
        parameters = @Parameter(name="property",value="studentInfo"))
    @GeneratedValue(generator = "myForeignGenerator")
    private UUID id;
    // 关联的学生信息
    @OneToOne(cascade={CascadeType.ALL}, optional = false)
    @PrimaryKeyJoinColumn
    private StudentInfo studentInfo;
    //就业去向代码
    @Column(length = 20,nullable = true)
    private String destinationType;

    @Transient
    private String destinationTypeName;
    //单位名称
    @Column(length = 500,nullable = true)
    private String recName;
    //组织机构代码
    @Column(length = 20,nullable = true)
    private String recCode;
    //单位性质
    @Column(length = 3,nullable = true)
    private String recNature;
    //单位行业
    @Column(length = 3,nullable = true)
    private String recIndustry;
    //单位所在省
    @Column(length = 50)
    private String recProvince;
    //单位所在地recCity
    @Column(length = 50,nullable = true)
    private String recCity;
    // 工作职位类别jobType
    @Column(length = 3,nullable = true)
    private String jobType;
    // 单位联系人recLinker
    @Column(length = 30,nullable = true)
    private String recLinker;
    // 联系人电话recTelphone
    @Column(length = 20,nullable = true)
    private String recTelphone;
    // 联系人手机recMobile
    @Column(length = 20,nullable = true)
    private String recMobile;
    @Column(length = 100,nullable = true)
    private String recAddress;
    // 单位邮编recZipcode
    @Column(length = 20,nullable = true)
    private String recZipcode;
    //隶属部门
    @Column(length = 20,nullable = true)
    private  String departmentBelong;
    // 档案转寄单位名称pfileToName
    @Column(length = 50)
    private String pfileToName;
    // 档案转寄部门pfileToDepart
    @Column(length = 50)
    private String pfileToDepart;
    // 档案转寄单位地址pfileToAddress
    @Column(length = 100)
    private String pfileToAddress;
    // 档案转寄单位邮编pfileToZipcode
    @Column(length = 20)
    private String pfileToZipcode;
    // 户口迁转地址hukouToAddress
    @Column(length = 100)
    private String hukouToAddress;
    //户口是否在学校
    @Column(length = 1)
    private String hukouIsSchool;

    //学生核对状态(00未核对 01已核对)
    @Column(length = 3,nullable = false)
    private String stuStatus;

    // 创建时间createTime
    @Column(updatable = true)
    @CreationTimestamp
    private LocalDateTime createTime;
    // 最后修改时间updateTime
    @Column
    @UpdateTimestamp
    private LocalDateTime updateTime;
    //状态status ? 最终审核状态(00未就业01待审核02审核通过)
    @Column(length = 3,nullable = false)
    private String status;
    //状态status 对应子码表
    @Transient
    private String statusName;
    //报到证信息前发信息类别
    @Column(length = 3)
    private String reportCardType;
    // 迁往单位所在地
    @Column(length = 50)
    private String reportCardAddress;
    //报到证迁往单位
    @Column(length = 50)
    private String reportCardRec;
    //报到证签发时间
    @Column()
    private LocalDate reportCardDate;

    public GraduateDestination() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public String getDestinationTypeName() {
        return PubCodeUtil.getName("graduateTo",this.getDestinationType());
    }

    public String getRecProvince() {
        return recProvince;
    }

    public void setRecProvince(String recProvince) {
        this.recProvince = recProvince;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode;
    }

    public String getRecNature() {
        return recNature;
    }

    public void setRecNature(String recNature) {
        this.recNature = recNature;
    }

    public String getRecIndustry() {
        return recIndustry;
    }

    public void setRecIndustry(String recIndustry) {
        this.recIndustry = recIndustry;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getRecLinker() {
        return recLinker;
    }

    public void setRecLinker(String recLinker) {
        this.recLinker = recLinker;
    }

    public String getRecTelphone() {
        return recTelphone;
    }

    public void setRecTelphone(String recTelphone) {
        this.recTelphone = recTelphone;
    }

    public String getRecMobile() {
        return recMobile;
    }

    public void setRecMobile(String recMobile) {
        this.recMobile = recMobile;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecZipcode() {
        return recZipcode;
    }

    public void setRecZipcode(String recZipcode) {
        this.recZipcode = recZipcode;
    }

    public String getDepartmentBelong() {
        return departmentBelong;
    }

    public void setDepartmentBelong(String departmentBelong) {
        this.departmentBelong = departmentBelong;
    }

    public String getPfileToName() {
        return pfileToName;
    }

    public void setPfileToName(String pfileToName) {
        this.pfileToName = pfileToName;
    }

    public String getPfileToDepart() {
        return pfileToDepart;
    }

    public void setPfileToDepart(String pfileToDepart) {
        this.pfileToDepart = pfileToDepart;
    }

    public String getPfileToAddress() {
        return pfileToAddress;
    }

    public void setPfileToAddress(String pfileToAddress) {
        this.pfileToAddress = pfileToAddress;
    }

    public String getPfileToZipcode() {
        return pfileToZipcode;
    }

    public void setPfileToZipcode(String pfileToZipcode) {
        this.pfileToZipcode = pfileToZipcode;
    }

    public String getHukouToAddress() {
        return hukouToAddress;
    }

    public void setHukouToAddress(String hukouToAddress) {
        this.hukouToAddress = hukouToAddress;
    }

    public String getHukouIsSchool() {
        return hukouIsSchool;
    }

    public void setHukouIsSchool(String hukouIsSchool) {
        this.hukouIsSchool = hukouIsSchool;
    }

    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReportCardType() {
        return reportCardType;
    }

    public void setReportCardType(String reportCardType) {
        this.reportCardType = reportCardType;
    }

    public String getReportCardAddress() {
        return reportCardAddress;
    }

    public void setReportCardAddress(String reportCardAddress) {
        this.reportCardAddress = reportCardAddress;
    }

    public String getReportCardRec() {
        return reportCardRec;
    }

    public void setReportCardRec(String reportCardRec) {
        this.reportCardRec = reportCardRec;
    }

    public LocalDate getReportCardDate() {
        return reportCardDate;
    }

    public void setReportCardDate(LocalDate reportCardDate) {
        this.reportCardDate = reportCardDate;
    }


    public String getStatusName() {
        return PubCodeUtil.getName("auditStatus",this.getStatus());
    }
}
