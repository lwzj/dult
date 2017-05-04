package cn.edu.dlut.career.domain.company;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * Created by 史念念 on 2017/3/23.
 */
@Embeddable
public class Audit {
    //审核状态（0 等待审核，1 通过审核，2 未通过审核）
    @Column(length = 1, nullable = false)
    private String auditStatus;

    //审核时间
    @Column(nullable = true)
    private LocalDateTime auditTime;

    //审核人
    @Column(length = 32, nullable = true)
    private String auditor;

    //审核失败原因
    @Column(length = 100, nullable = true)
    private String nopassReason;

    public Audit() {
    }

    public Audit(String auditStatus, LocalDateTime auditTime, String auditor, String nopassReason) {
        this.auditStatus = auditStatus;
        this.auditTime = auditTime;
        this.auditor = auditor;
        this.nopassReason = nopassReason;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getNopassReason() {
        return nopassReason;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public void setNopassReason(String nopassReason) {
        this.nopassReason = nopassReason;
    }
}
