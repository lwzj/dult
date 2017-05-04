package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.repository.student.BlankProtocolRepository;
import cn.edu.dlut.career.service.student.BlankProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/25.
 *
 * 空白协议书 服务层实现类
 */
@Service
public class BlankProtocolServiceImpl implements BlankProtocolService{
    @Autowired
    private BlankProtocolRepository blankProtocolRepository;

    /**
     * 空白协议书 添加
     * @param student 学生实体类
     * @param applicationReason 申请理由
     * @param applicationReasonRemarks 申请理由备注
     * @return
     */
    @Override
    public String saveBPro(StudentInfo student, String applicationReason, String applicationReasonRemarks) {
        try {
            BlankProtocol blankProtocol = new BlankProtocol();
            blankProtocol.setStuId(student.getId());
            blankProtocol.setStuNo(student.getStuNo());
            blankProtocol.setName(student.getName());
            blankProtocol.setEduDegree(student.getEduDegree());
            blankProtocol.setDepartmentId(student.getDepartmentId());
            blankProtocol.setDepartment(student.getDepartment());
            blankProtocol.setMajor(student.getMajorName());
            blankProtocol.setMobilephone(student.getMobilephone());
            blankProtocol.setEmail(student.getEmail());
            blankProtocol.setEndDate(student.getEndDate());
            blankProtocol.setApplicationReason(applicationReason);
            blankProtocol.setApplicationReasonRemarks(applicationReasonRemarks);
            blankProtocol.setDepartAuditStatus("00");//院审核状态 待审核
            blankProtocol.setSchAuditStatus("00");//校审核状态 待审核
            blankProtocol.setAuditStatus("00"); //申请状态 为待审核

            blankProtocolRepository.save(blankProtocol);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();

            return "fail";
        }

    }

    /**
     * 根据学生id查找空白协议书
     * @param stuId
     * @return
     */
    @Override
    public List<BlankProtocol> findByStuId(UUID stuId) {
        return blankProtocolRepository.findByStuId(stuId);
    }

    /**
     * 根据id查找空白协议书
     * @param id
     * @return
     */
    @Override
    public BlankProtocol findById(UUID id) {

        return blankProtocolRepository.findOne(id);
    }

    /**
     *该方法用于教师端 动态查询空白协议书
     * @param stuNo 学生学号 要求精确
     * @param name 学生姓名 可模糊
     * @param auditStatus 审核状态
     * @param departmentId 院系
     * @param pageable
     * @return
     */
    @Override
    public Page<BlankProtocol> findBlankProtocol(String stuNo, String name, String auditStatus, String departmentId, Pageable pageable) {

        String departAuditStatus = null;
        String schAuditStatus = null;
        if("00".equals(auditStatus)){
            departAuditStatus = "00";
            schAuditStatus = "00";
        }else  if("01".equals(auditStatus)){
            departAuditStatus ="01";
        }else  if("02".equals(auditStatus)){
            departAuditStatus ="02";
        }else if("03".equals(auditStatus)){
            schAuditStatus = "01";
        }else if("04".equals(auditStatus)){
            schAuditStatus="02";
        }
        Page<BlankProtocol> bps = blankProtocolRepository.findBlankProtocol(stuNo,name,departAuditStatus,schAuditStatus,departmentId,pageable);
        return bps;
    }

    /**
     * 教师端 修改审核信息
     * @param id 空白协议书申请表id
     * @param departAuditStatus 院审核状态
     * @param departAuditor 院审核人
     * @param departNoPassReason 院审核不通过原因
     * @param schAuditStatus 校审核状态
     * @param schAuditor 校审核人
     * @param schNoPassReason 校审核不通过原因
     * @return
     */
    @Override
    public String updateAudit(UUID id, String departAuditStatus, String departAuditor, String departNoPassReason, String schAuditStatus, String schAuditor, String schNoPassReason) {
        LocalDateTime departAuditTime = null;
        LocalDateTime schAuditTime = null;
        BlankProtocol bp = blankProtocolRepository.findOne(id);
        int result = 0;
        //申请审核状态
        String auditStatus = "00";
        int status = Integer.parseInt(bp.getAuditStatus());


        //院审核
        if(departAuditor!=null){
            //如果校级已经修改 则院系不可修改总状态
            if(status>2){
                auditStatus = bp.getAuditStatus();
                if ("01".equals(departAuditStatus)) {
                    departNoPassReason = null;
                }
            }else {
                if ("01".equals(departAuditStatus)) {
                    auditStatus = "01";
                    departNoPassReason = null;
                } else if ("02".equals(departAuditStatus)) {
                    auditStatus = "02";
                }
            }
            departAuditTime = LocalDateTime.now();

            result = blankProtocolRepository.updateDepartAudit(id,auditStatus,departAuditStatus,departAuditor,departAuditTime,departNoPassReason);
        }
        //校审核
        else if (schAuditor!=null){
            if("01".equals(schAuditStatus)){
                auditStatus = "03";
                schNoPassReason = null;
            }else if("02".equals(schAuditStatus)){
                auditStatus = "04";
            }
            schAuditTime = LocalDateTime.now();
            result = blankProtocolRepository.updateSchAudit(id,auditStatus,schAuditStatus,schAuditor,schAuditTime,schNoPassReason);
        }

        return result>0?"ok":"fail";
    }
}
