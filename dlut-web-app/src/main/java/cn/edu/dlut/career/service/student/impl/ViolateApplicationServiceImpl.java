package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.repository.student.GraduateDestinationRepostiory;
import cn.edu.dlut.career.repository.student.StudentInfoRepository;
import cn.edu.dlut.career.repository.student.ViolateApplicationRepository;
import cn.edu.dlut.career.service.company.RecOfferService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.service.student.ViolateApplicationService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 违约申请 服务层
 * Created by HealerJean on 2017/4/13.
 */
@Service
@Transactional
public class ViolateApplicationServiceImpl implements ViolateApplicationService {
    @Autowired
    private ViolateApplicationRepository violateApplicationRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private RecOfferRepository recOfferRepository;

    @Autowired
    private RecOfferService recOfferService;

    @Autowired
    private GraduateDestinationRepostiory graduateDestinationRepostiory;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentInfoService studentInfoService;
    @Override
    public ViolateApplication saveViolateApply(ViolateApplication violateApplication) {

        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        //查看这个学生表中是否已经存在违约申请了
        ViolateApplication violateApplicationStatus = violateApplicationRepository.findByStuNo(userPrincipal.getUserName());
        if(violateApplicationStatus==null) {
            StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
            violateApplication.setMajorCode(studentInfo.getMajorCode());
            violateApplication.setMajorName(studentInfo.getMajorName());
            violateApplication.setDepartment(studentInfo.getDepartment());
            violateApplication.setDepartmentId(studentInfo.getDepartmentId());

            violateApplication.setAuditStatus("00");
            violateApplication.setDepartAuditStatus("00");
            violateApplication.setSchAuditStatus("00");
            UUID recOfferId = recOfferService.findRecOfferByStuNo(violateApplication.getStuNo()).getId();
            violateApplication.setRecOfferId(recOfferId);
            return violateApplicationRepository.save(violateApplication);
        }

            return  null;


    }

    @Override
    public ViolateApplication findById(UUID id) {
        return violateApplicationRepository.findOne(id);
    }

    @Override
    public ViolateApplication updateViolateApply(ViolateApplication violateApplication) {
        return violateApplicationRepository.save(violateApplication);
    }

    @Override
    public String deleteViolateApply(UUID id) {
        try {
            violateApplicationRepository.delete(id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ViolateApplication> findAllViolateApply() {
        return violateApplicationRepository.findAll();
    }

    /**
     * 动态查询 违约申请
     * 该方法用于教师端 对违约申请的查询
     *
     * @param stuNo       学号
     * @param stuName     姓名
     * @param departmentId  院系id
     * @param majorName   专业
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param auditStatus 审核状态
     * @return
     */
    @Override
    public Page<ViolateApplicationDTO> findByKey(String stuNo,
                                                 String stuName,
                                                 String departmentId,
                                                 String majorName,
                                                 LocalDateTime startTime,
                                                 LocalDateTime endTime,
                                                 String auditStatus,
                                                 Pageable pageable) {

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
        Page<ViolateApplicationDTO> vads = violateApplicationRepository.findByKey(stuNo,stuName,departmentId,majorName,startTime,endTime,departAuditStatus,schAuditStatus,pageable);

        return vads;
    }

    @Override
    public ViolateApplication findByStuNo (String stuNo){
        return violateApplicationRepository.findByStuNo(stuNo);
    }

    /**
     * 根据违约申请id查找违约申请信息
     * @param id
     * @return
     */
    @Override
    public ViolateApplicationDTO findByVioId(UUID id) {
        ViolateApplicationDTO vad = violateApplicationRepository.findById(id);

        return vad;
    }

    /**
     * 修改审核信息
     * @param id 违约申请id
     * @param departAuditStatus 院级审核状态
     * @param departNoPassReason 院级审核不通过原因
     * @param departAuditor 院级审核人
     * @param schAuditStatus 校级审核状态
     * @param schNoPassReason 校级审核不通过原因
     * @param schAuditor 校级审核人
     * @return
     */
    @Override
    public String updateAudit(UUID id, String departAuditStatus, String departNoPassReason, String departAuditor, String schAuditStatus, String schNoPassReason, String schAuditor) {

        LocalDateTime departAuditTime = null;
        LocalDateTime schAuditTime = null;
        int result = 0;
        //根据id查找违约申请信息
        ViolateApplication va = violateApplicationRepository.findOne(id);
        int status = Integer.parseInt(va.getAuditStatus());
        //申请审核状态
        String auditStatus="00";

        //院系老师进行的修改操作
        if(departAuditor!=null){
            //若校级已审核院系不可对总审核状态进行修改
            if(status>2){
                auditStatus = va.getAuditStatus();
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
            result = violateApplicationRepository.updateDepartAudit(id,auditStatus,departAuditStatus,departAuditor,departAuditTime,departNoPassReason);
        }
        //校级老师进行的修改操作
        if(schAuditor!=null){
            if("01".equals(schAuditStatus)){
                auditStatus = "03";
                schNoPassReason = null;
            }else if("02".equals(schAuditStatus)){
                auditStatus = "04";
            }
            schAuditTime = LocalDateTime.now();
            result = violateApplicationRepository.updateSchAudit(id,auditStatus,schAuditStatus,schAuditor,schAuditTime,schNoPassReason);

            if(result>0 && "03".equals(auditStatus)){
                //如果校级审核通过，则学生签约表中的学生接收状态改成 5毁约
                result = recOfferRepository.updateStuReceivingStatus(va.getRecOfferId(),"05");
                //校级审核通过，删除旧就业去向，并添加一个新的
                GraduateDestination graduateDestination = graduateDestinationRepostiory.findByStuNo(va.getStuNo());
                graduateDestination.setStatus("00");
                graduateDestination.setStuStatus("00");
                graduateDestination.setDestinationType(null);
                graduateDestination.setRecName(null);
                graduateDestination.setRecCode(null);
                graduateDestination.setRecNature(null);
                graduateDestination.setRecIndustry(null);
                graduateDestination.setRecProvince(null);
                graduateDestination.setRecCity(null);
                graduateDestination.setJobType(null);
                graduateDestination.setRecLinker(null);
                graduateDestination.setRecMobile(null);
                graduateDestination.setRecTelphone(null);
                graduateDestination.setRecAddress(null);
                graduateDestination.setRecZipcode(null);
                graduateDestination.setDepartmentBelong(null);
                graduateDestination.setPfileToName(null);
                graduateDestination.setPfileToAddress(null);
                graduateDestination.setPfileToDepart(null);
                graduateDestination.setPfileToZipcode(null);
                graduateDestination.setHukouToAddress(null);
                graduateDestination.setHukouIsSchool(null);
                graduateDestination.setReportCardType(null);
                graduateDestination.setReportCardAddress(null);
                graduateDestination.setReportCardDate(null);
                graduateDestination.setReportCardRec(null);
                graduateDestinationRepostiory.save(graduateDestination);
            }
        }
        return result>0?"ok":"fail";
    }

}
