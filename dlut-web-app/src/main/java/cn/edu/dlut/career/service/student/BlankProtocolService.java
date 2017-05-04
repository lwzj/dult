package cn.edu.dlut.career.service.student;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.domain.student.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/25.
 *
 * 空白协议书 服务层接口
 */
public interface BlankProtocolService {

    /**
     * 添加空白协议书
     * @param student 学生实体类
     * @param applicationReason 申请理由
     * @param applicationReasonRemarks 申请理由备注
     * @return
     */
    String saveBPro(StudentInfo student, String applicationReason, String applicationReasonRemarks);

    /**
     * 根据学生id查找空白协议书
     * @param stuId
     * @return
     */
    List<BlankProtocol> findByStuId(UUID stuId);

    /**
     * 根据id查找空白协议书
     * @param id
     * @return
     */
    BlankProtocol findById(UUID id);

    /**
     *该方法用于教师端 动态查询空白协议书
     * @param stuNo 学生学号 要求精确
     * @param name 学生姓名 可模糊
     * @param auditStatus 审核状态
     * @param departmentId 院系
     * @param pageable
     * @return
     */
    Page<BlankProtocol> findBlankProtocol(String stuNo, String name, String auditStatus, String departmentId, Pageable pageable);

    /**
     * 教师端 审核状态修改
     * @param id 空白协议书申请表id
     * @param departAuditStatus 院审核状态
     * @param departAuditor 院审核人
     * @param departNoPassReason 院审核不通过原因
     * @param schAuditStatus 校审核状态
     * @param schAuditor 校审核人
     * @param schNoPassReason 校审核不通过原因
     * @return
     */
    String updateAudit(UUID id, String departAuditStatus, String departAuditor, String departNoPassReason, String schAuditStatus, String schAuditor, String schNoPassReason);
}
