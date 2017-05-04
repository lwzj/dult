package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.service.student.GraduateDestinationService;
import cn.edu.dlut.career.service.student.ReassignApplicationService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * 改派申请
 *
 * Created by HealerJean on 2017/4/13.
 */
@Controller
public class ReassignApplyCommand {
    @Autowired
    private ReassignApplicationService reassignApplicationService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private GraduateDestinationService graduateDestinationService;

    /**
     * 张宇晋
     * tea_employmentAlertLook.html
     * @param map
     */
    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("allMajor",   PubCodeUtil.getDictMap("major"));
        map.put("allEduDegree", PubCodeUtil.getDictMap("eduDegree"));
        map.put("allAcademy", PubCodeUtil.getDictMap("academy"));
        map.put("allAuditStatus", PubCodeUtil.getDictMap("auditStatus"));
        map.put("allReassignReason", PubCodeUtil.getDictMap("reassignReason"));
    }

    //查看改派信息
    @GetMapping("/studentHTML/stu_employment_look.html")
    public ModelAndView look(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        String stuNo = userPrincipal.getUserName();
        ReassignApplication reassignApplication = reassignApplicationService.findByStuNo(stuNo);
        ModelAndView mv = new ModelAndView("/studentHTML/stu_employment_look");
        mv.addObject("reassignApplication",reassignApplication);
        return mv;
    }

    //改派申请页面
    @GetMapping("/student/employmentApply.html")
    public ModelAndView  reassignApply(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        ModelAndView modelAndView = null;
        GraduateDestination graduateDestination = graduateDestinationService.findByStuIdAndStatus(userPrincipal.getId(),"04");
        if (graduateDestination!=null) {
            ReassignApplication reassignApplication = reassignApplicationService.findByStuNo(userPrincipal.getUserName());
            if(reassignApplication==null) {
                StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
                modelAndView = new ModelAndView("studentHTML/stu_employment_apply");
                modelAndView.addObject("graduateDestination", graduateDestination);
                modelAndView.addObject("studentInfo", studentInfo);
            }else{
                StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
                modelAndView = new ModelAndView("studentHTML/stu_employment_apply");
                modelAndView.addObject("graduateDestination", graduateDestination);
                modelAndView.addObject("studentInfo", studentInfo);
                modelAndView.addObject("errorMsg", "您已经申请过了,不能重复申请");
            }
        }else {
            modelAndView = new ModelAndView("studentHTML/stu_offer");
            modelAndView.addObject("errorMsg","您没有签订就业,请接收一个offer");
        }
        return modelAndView;

    }

    /**
     * 添加改派申请
     * @param reassignApplication
     * @return
     */
    @PostMapping("/student/addReassignApply")
    public ModelAndView saveReassignApply(ReassignApplication reassignApplication){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        GraduateDestination graduateDestination = graduateDestinationService.findByStuIdAndStatus(userPrincipal.getId(),"04");
        ModelAndView mav = null;
        if (graduateDestination!=null) {
            ReassignApplication reassignApplicationStatus = reassignApplicationService.saveReassignApply(reassignApplication);
            if (reassignApplicationStatus != null) {
                mav = new ModelAndView("studentHTML/stu_employment_look");
                mav.addObject("reassignApplication", reassignApplication);
            }else {
                mav = new ModelAndView("studentHTML/stu_employment_apply");
                StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
                mav.addObject("graduateDestination", graduateDestination);
                mav.addObject("studentInfo", studentInfo);
                mav.addObject("errorMsg", "您已经申请过了,不能重复申请");
            }
        }else {
            mav = new ModelAndView("studentHTML/stu_offer");
            mav.addObject("errorMsg","您没有签订就业,请接收一个offer");
        }
        return mav;

    }

    /**
     * 教师端
     * 改派申请查看 准备进行分页展示
     * @return
     */
    @GetMapping("/teacher/listRessignApplication.html")
    public String listRessign(){
        return  "teacherHTML/tea_employmentAlertLook";
    }


    /**
     * 教师对某个学生的改派申请进行审核
     * @param reassignApplication
     * @return
     */
    @PostMapping("/teacher/updateReassAppAuditStatus")
    public ModelAndView updateReassAppAuditStatus(ReassignApplication reassignApplication){
        ModelAndView mav= new ModelAndView();
        ReassignApplication reassignApplicationFinale = reassignApplicationService.updateReassAppAuditStatus(reassignApplication);
        if ( reassignApplicationFinale!=null) {
            mav.setViewName("teacherHTML/tea_employmentAlertDetails");
            mav.addObject("reassignApplication", reassignApplicationFinale);
            mav.addObject("successMsg", "提交成功");
            return mav;
        }else {
            mav.setViewName("teacherHTML/tea_employmentAlertLook");
            mav.addObject("errorMsg", "非校级身份或非法访问");
        }
        return  mav;
    }



   /* *//**
     * 教师端
     * 直接通过id审核通过 改派申请
     */

    @GetMapping("/teacher/stuReassAppAudit")
    @ResponseBody
    public  String stuReassAppAudit(@RequestParam UUID id) {
        ReassignApplication reassignApplication = reassignApplicationService.stuReassAppAudit(id);
        if (reassignApplication != null) { //审核通过进入查看页面
            return "修改成功";
        } else {
            return "非校级身份或非法访问";
        }
    }

    /**
     * 教师端
     * 批量审核改派申请
     */
    @GetMapping("/teacher/stuReassAppBatchAudit")
    @ResponseBody
    public  String stuReassAppBatchAudit(@RequestParam  UUID[]id) {
        int  operateStatus= reassignApplicationService.stuReassAppBatchAudit(id);
        if (operateStatus!=0){ //审核通过进入查看页面
            return "批量修改成功";
        }else {
            return "非校级身份或非法访问";
        }
    }


}
