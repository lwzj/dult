package cn.edu.dlut.career.controller.student;


import cn.edu.dlut.career.dto.ResponseInfo;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.ViolateApplicationDTO;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.service.student.ViolateApplicationService;
import cn.edu.dlut.career.shiro.UserPrincipal;

import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

/**
 * 违约申请
 * Created by HealerJean on 2017/4/14.
 */
@Controller
public class ViolateApplicationQuery {

    @Autowired
    private ViolateApplicationService violateApplicationService;
    @Autowired
    private HttpServletRequest request;

    @ModelAttribute("auditStatus")
    public Map<String, String> auditStatus() {
        return PubCodeUtil.getDictMap("auditStatus");
    }

    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }
    @ModelAttribute("totalAuditStatus")
    public Map<String, String> totalAuditStatus() {
        return PubCodeUtil.getDictMap("totalAuditStatus");
    }


    /**
     * 页面的跳转
     *
     * @return
     */
    @GetMapping("/teacher/tea_absentReview.html")
    public String toTea_absentReview() {
        return "/teacherHTML/tea_absentReview";
    }

    /**
     * 教师端详情页面的跳转
     *
     * @return
     */
    @GetMapping("/teacher/teaAbsent.html")
    public ModelAndView toTea_absent(String vid) {
        UUID id = UUID.fromString(vid);
        ModelAndView mv = new ModelAndView("/teacherHTML/tea_absent");
        ViolateApplicationDTO vad = violateApplicationService.findByVioId(id);

        mv.addObject("vad", vad);
        return mv;
    }

    /**
     * 动态查询 违约申请
     * 该方法用于教师端 对违约申请的查询
     * 教师类型 SCHOOL普校 SCHADMIN校管 ACADEMY普院 ACADMIN院管
     * @param stuNo           学号
     * @param stuName         姓名
     * @param departmentId      院系id
     * @param majorName       专业
     * @param applicationTime 申请时间
     * @param auditStatus     审核状态
     * @return
     */
    @GetMapping("/teacher/findVio")
    @ResponseBody
    public Page<ViolateApplicationDTO> findByKey(String stuNo,
                                                 String stuName,
                                                 String departmentId,
                                                 String majorName,
                                                 String applicationTime,
                                                 String auditStatus,
                                                 Pageable pageable) {

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        //如果学号为空 ，stuNo值设为null 下同
        if ("".equals(stuNo)) {
            stuNo = null;
        }
        if ("".equals(stuName)) {
            stuName = null;
        }
        if ("".equals(majorName)) {
            majorName = null;
        }
        if("".equals(departmentId)){
            departmentId = null;
        }
        if("".equals(auditStatus)){
            auditStatus = null;
        }

        if ("3".equals(applicationTime) || "0".equals(applicationTime)) {
            //查询所有
            startTime = null;
            endTime = null;
        } else if ("1".equals(applicationTime)) {
            //查询一周以内的违约申请
            startTime = LocalDateTime.now().plusWeeks(-1);
            endTime = LocalDateTime.now();
        } else if ("2".equals(applicationTime)) {
            //查询一月以内的违约申请
            startTime = LocalDateTime.now().plusMonths(-1);
            endTime = LocalDateTime.now();
        }

        Page<ViolateApplicationDTO> vads = violateApplicationService.findByKey(stuNo, stuName, departmentId, majorName, startTime, endTime, auditStatus, pageable);

        return vads;
    }

    /**
     * 违约详情
     *
     * @return
     */
    @GetMapping("/student/violateApplicationInfo")
    public ModelAndView violateApplicationInfo() {
        UserPrincipal userPrincipal = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        ViolateApplication violateApplication = violateApplicationService.findByStuNo(userPrincipal.getUserName());
        ModelAndView mav = new ModelAndView("studentHTML/stu_agreement_look");
        mav.addObject("violateApplication", violateApplication);
        return mav;
    }

    /**
     * 张宇晋
     * 查看 学生是不是已经具有违约申请表了
     *
     * @return
     */
    @GetMapping("/student/haveViolateApplication")
    @ResponseBody
    public ResponseInfo haveViolateApplication() {
        UserPrincipal userPrincipal = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        ViolateApplication violateApplication = violateApplicationService.findByStuNo(userPrincipal.getUserName());
        ResponseInfo responseInfo = new ResponseInfo();
        if (violateApplication != null) {
            responseInfo.setStatus(1);
            responseInfo.setMessage("已经存在违约申请位，不可以再次申请");
        } else {
            responseInfo.setStatus(0);
            responseInfo.setMessage("违约申请不存在，可以申请");
        }
        return responseInfo;
    }
}
