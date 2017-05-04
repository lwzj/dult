package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 违约申请 服务层接口
 * Created by HealerJean on 2017/4/13.
 */
public interface ViolateApplicationService {
    //保存，添加
    ViolateApplication saveViolateApply(ViolateApplication violateApplication) ;

    //根据id查询
    ViolateApplication findById(UUID id);

    // 更新
    ViolateApplication updateViolateApply(ViolateApplication violateApplication);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteViolateApply(UUID id);

    //查询所有的数据
    List<ViolateApplication> findAllViolateApply();

    /**
     * 动态查询 违约申请
     * 该方法用于教师端 对违约申请的查询
     * @param stuNo 学号
     * @param stuName 姓名
     * @param departmentId 院系id
     * @param majorName 专业
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param auditStatus 审核状态
     * @return
     */
    Page<ViolateApplicationDTO> findByKey(String stuNo,
                                          String stuName,
                                          String departmentId,
                                          String majorName,
                                          LocalDateTime startTime,
                                          LocalDateTime endTime,
                                          String auditStatus,
                                          Pageable pageable);

    ViolateApplication findByStuNo(String stuNo);

    /**
     * 根据违约申请id查找 违约申请信息
     * @param id
     * @return
     */
    ViolateApplicationDTO findByVioId(UUID id);

    /**
     * 修改违约申请审核信息
     * @param id 违约申请id
     * @param departAuditStatus 院级审核状态
     * @param departNoPassReason 院级审核不通过原因
     * @param departAuditor 院级审核人
     * @param schAuditStatus 校级审核状态
     * @param schNoPassReason 校级审核不通过原因
     * @param schAuditor 校级审核人
     * @return
     */

    String updateAudit(UUID id,
                       String departAuditStatus,
                       String departNoPassReason,
                       String departAuditor,
                       String schAuditStatus,
                       String schNoPassReason,
                       String schAuditor);

}
