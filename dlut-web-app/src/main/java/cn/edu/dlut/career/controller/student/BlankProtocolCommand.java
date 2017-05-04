package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.service.student.BlankProtocolService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by 史念念 on 2017/4/25.
 * <p>
 * 空白协议书 增删改
 */
@Controller
public class BlankProtocolCommand {

    @Autowired
    private BlankProtocolService blankProtocolService;
    @Autowired
    private StudentInfoService studentInfoService;

    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }
    @ModelAttribute("totalAuditStatus")
    public Map<String, String> totalAuditStatus() {
        return PubCodeUtil.getDictMap("totalAuditStatus");
    }

    /**
     * 教师端详情页 空白协议书申请审核状态修改
     *
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    @PostMapping("teacher/updateBlankAudit1")
    public ModelAndView updateAudit1(String id, String auditStatus, String noPassReason) {
        ModelAndView mv = new ModelAndView("/teacherHTML/tea_blankBookDetails");
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();

        UUID bid = UUID.fromString(id);

        String result = updateBlankAudit(bid, auditStatus, noPassReason);
        BlankProtocol b = blankProtocolService.findById(bid);
        if ("ok".equals(result)) {
            if ("ACADEMY".equals(principal) || "ACADMIN".equals(principal)) {
                b.setDepartAuditStatus(auditStatus);
                b.setDepartNoPassReason(noPassReason);
            }else if("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)){
                b.setSchAuditStatus(auditStatus);
                b.setSchNoPassReason(noPassReason);
            }
            mv.addObject("msg", "审核状态修改成功");

        } else if ("fail".equals(result)) {
            mv.addObject("msg", "审核状态修改失败");
        }


        mv.addObject("bp", b);

        return mv;
    }

    /**
     * 教师端列表页 空白协议书申请审核状态修改
     *
     * @param id
     * @return
     */
    @PostMapping("teacher/updateBlankAudit2")
    @ResponseBody
    public String updateAudit2(@RequestParam UUID id) {
        BlankProtocol bp = blankProtocolService.findById(id);
        int status = Integer.parseInt(bp.getAuditStatus());
        String result = updateBlankAudit(id, "01", null);
        if ("ok".equals(result)) {
            return "审核状态修改成功";
        } else {
            return "审核状态修改失败";
        }

    }

    /**
     * 批量审核通过 空白协议书申请
     *
     * @param ids
     * @return
     */
    @PostMapping("teacher/updateMoreBlankAudit")
    @ResponseBody
    public String updateMoreBlankAudit(@RequestParam UUID[] ids) {
        String result = "";
        for (int i = 0; i < ids.length; i++) {
            UUID id = ids[i];
            result = updateBlankAudit(id, "01", null);
        }

        if ("ok".equals(result)) {
            return "审核状态修改成功";
        } else {
            return "审核状态修改失败";
        }
    }

    /**
     * 单个空白协议申请审核公共方法
     * 将该方法单独摘出来原因是：
     * 在详情页面中请求审核后返回的是ModelAndView，在列表页面中为ajax请求返回的是json
     *
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    public String updateBlankAudit(UUID id, String auditStatus, String noPassReason) {
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        //教师身份
        String principal = userPrincipal.getPrincipal();
        //教师姓名
        String auditor = userPrincipal.getRealName();

        //院校审核人、审核状态、审核不通过原因
        String departAuditor = null;
        String departAuditStatus = null;
        String departNoPassReason = null;
        String schAuditor = null;
        String schAuditStatus = null;
        String schNoPassReason = null;
        //根据教师身份类型 赋值
        if ("ACADEMY".equals(principal) || "ACADMIN".equals(principal)) {
            departAuditor = auditor;
            departAuditStatus = auditStatus;
            departNoPassReason = noPassReason;
        } else if ("SCHOOL".equals(principal) || "SCHADMIN".equals(principal)) {
            schAuditor = auditor;
            schAuditStatus = auditStatus;
            schNoPassReason = noPassReason;
        }

        String result = blankProtocolService.updateAudit(id, departAuditStatus, departAuditor, departNoPassReason, schAuditStatus, schAuditor, schNoPassReason);

        return result;
    }

    /**
     * 添加 空白协议书
     *
     * @param applicationReason        申请理由
     * @param applicationReasonRemarks 申请理由备注
     * @return
     */
    @PostMapping("/student/saveBPro")
    @ResponseBody
    public Map<String, String> savePro(String applicationReason, String applicationReasonRemarks) {

        Map<String, String> map = new HashMap();
        Subject subject = SecurityUtils.getSubject();

        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        UUID stuId = userPrincipal.getId();

        StudentInfo student = studentInfoService.findById(stuId);
        String result = blankProtocolService.saveBPro(student, applicationReason, applicationReasonRemarks);
        if ("success".equals(result)) {
            map.put("msg", "申请成功，请耐心等待学校审核");
            map.put("url", "/student/stuBlankBook");
        } else if ("fail".equals(result)) {
            map.put("msg", "申请失败，请重新操作");
            map.put("url", "");
        }

        return map;
    }

}
