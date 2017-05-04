package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import cn.edu.dlut.career.service.company.RecOfferService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.service.student.ViolateApplicationService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * 违约申请
 * Created by HealerJean on 2017/4/14.
 */
@Controller
public class ViolateApplicationCommand {
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private ViolateApplicationService violateApplicationService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RecOfferService recOfferService;

    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("totalAuditStatus", PubCodeUtil.getDictMap("totalAuditStatus"));
        map.put("allViolateReason", PubCodeUtil.getDictMap("violateReason"));
        map.put("academy", PubCodeUtil.getDictMap("academy"));
        map.put("auditStatus", PubCodeUtil.getDictMap("auditStatus"));

    }


    //违约申请页面
    @GetMapping("/student/agreementApply.html")
    public ModelAndView violateApplication(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        ViolateApplication violateApplicationStatus = violateApplicationService.findByStuNo(userPrincipal.getUserName());
        StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
        ModelAndView mav=null;
        RecOffer recOffer = recOfferService.findRecOfferByStuNo(userPrincipal.getUserName());
        if (recOffer!=null) {
            if (violateApplicationStatus != null) {
                mav = new ModelAndView("studentHTML/stu_agreement_apply");
                mav.addObject("studentInfo", studentInfo);
                mav.addObject("errorMsg", "您已经申请过了,不能重复申请");
            } else {
                mav = new ModelAndView("studentHTML/stu_agreement_apply");
                mav.addObject("studentInfo", studentInfo);
            }
        }else {
            mav = new ModelAndView("studentHTML/stu_offer");
            mav.addObject("errorMsg", "请先接受一个Offer");
        }
        return mav;
    }

    //违约申请添加
    /**
     * 暂时不全，因为违约协议书暂时，没有，后期可以进行跟进学生签约信息，利用stuId进行导入
     * @param violateApplication
     * @return
     */
    @PostMapping("/student/addviolateApplication")
    public ModelAndView saveviolateApplication(ViolateApplication violateApplication){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        RecOffer recOffer = recOfferService.findRecOfferByStuNo(userPrincipal.getUserName());
         ModelAndView mav=null;
         if(recOffer!=null) {
             ViolateApplication violateApplicationStatus = violateApplicationService.saveViolateApply(violateApplication);
             if (violateApplicationStatus != null) {
                 mav = new ModelAndView("studentHTML/stu_agreement_look");
                 mav.addObject("violateApplication", violateApplication);
             } else {
                 StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
                 mav = new ModelAndView("studentHTML/stu_agreement_apply");
                 mav.addObject("studentInfo", studentInfo);
                 mav.addObject("errorMsg", "您已经申请过了,不能重复申请");
             }
         }else {
             mav = new ModelAndView("studentHTML/stu_offer");
             mav.addObject("errorMsg", "请先接受一个Offer");
         }


        return mav;
    }

    /**
     * 老师在详情页修改违约申请审核状态
     * 教师类型 SCHOOL普校 SCHADMIN校管 ACADEMY普院 ACADMIN院管
     * @param id 违约申请id
     * @param auditStatus 审核状态
     * @param noPassReason 审核不通过原因
     * @return
     */
    @PostMapping("/teacher/updateVioAudit1")
    public ModelAndView updateAudit1(String id,
                                    String auditStatus,
                                    String noPassReason){
        ModelAndView mv = new ModelAndView("/teacherHTML/tea_absent");
        UUID vid = UUID.fromString(id);
        String result = updateVioAudit(vid,auditStatus,noPassReason);

        if("ok".equals(result)){
            mv.addObject("msg","审核状态修改成功");
        }else{
            mv.addObject("msg","审核状态修改失败");
        }
        ViolateApplicationDTO vad = violateApplicationService.findByVioId(vid);
        mv.addObject("vad", vad);
        return mv;
    }

    /**
     * 老师在列表页修改违约申请审核状态
     * @param id
     * @return
     */
    @PostMapping("/teacher/updateVioAudit2")
    @ResponseBody
    public String updateAudit2(String id){
        UUID vid = UUID.fromString(id);
        String result = updateVioAudit(vid, "01", null);
        if ("ok".equals(result)) {
                return "审核状态修改成功";
        } else {
                return "审核状态修改失败";
        }

    }

    /**
     * 单个违约审核公共方法
     * 将该方法单独摘出来原因是：
     * 在详情页面中请求审核后返回的是ModelAndView，在列表页面中为ajax请求返回的是json
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    public String updateVioAudit(UUID id,
                                 String auditStatus,
                                 String noPassReason){

        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();
        //教师姓名
        String auditor = userPrincipal.getRealName();

        //院校审核人、审核状态、审核不通过原因
        String departAuditor = null;
        String departAuditStatus=null;
        String departNoPassReason=null;
        String schAuditor = null;
        String schAuditStatus=null;
        String schNoPassReason=null;
        //根据教师身份类型 赋值
        if("ACADEMY".equals(principal) || "ACADMIN".equals(principal)){
            departAuditor = auditor;
            departAuditStatus = auditStatus;
            departNoPassReason = noPassReason;
        }else if("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)){
            schAuditor = auditor;
            schAuditStatus = auditStatus;
            schNoPassReason = noPassReason;
        }
        String result = violateApplicationService.updateAudit(id,departAuditStatus,departNoPassReason,departAuditor,schAuditStatus,schNoPassReason,schAuditor);

        return result;
    }

    /**
     * 教师端 批量审核通过 违约申请
     * @param ids
     * @return
     */
    @Transactional
    @PostMapping("/teacher/updateMoreVioAudit")
    @ResponseBody
    public String updateMoreVioAudit(String[] ids){

        String result="";
        //循环 修改审核信息
        for(int i=0;i<ids.length;i++){
            UUID vid = UUID.fromString(ids[i]);

            result = updateVioAudit(vid,"01",null);
        }
        if("ok".equals(result)){
            return "审核状态修改成功";
        }else{
            return "审核状态修改失败";
        }
    }


}
