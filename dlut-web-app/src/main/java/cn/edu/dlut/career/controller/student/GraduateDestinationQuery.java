package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.service.student.GraduateDestinationService;
import cn.edu.dlut.career.service.student.ReassignApplicationService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

import java.util.Map;


import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
@Controller
public class GraduateDestinationQuery {
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GraduateDestinationService graduateDestinationService;
    @Autowired
    private ReassignApplicationService reassignApplicationService;

    @GetMapping("student/stu_employment.html")
    public String goEmployment(){
        return "studentHTML/stu_employment";
    }

    @GetMapping("/teacher/job.html")
    public String goJob(){
        return "teacherHTML/tea_job";
    }

    /**
     * 根据学生id查询学生的就业意向
     * @param
     * @return
     */
    @GetMapping("student/JobDestination")
    public ModelAndView findOne(@RequestParam UUID stuId){
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal  = (UserPrincipal) subject.getPrincipal();
        String stuNo = userPrincipal.getUserName();
        ModelAndView mv = new ModelAndView("studentHTML/stu_employment");
        GraduateDestination graduateDestination = graduateDestinationService.findByStuId(stuId);
        if(graduateDestination.getRecName()==null || graduateDestination.getRecName() ==""){
            mv.addObject("errorMessage","暂无就业信息");
        }
        ReassignApplication reassignApplication = null;
        //根据学号查看改派信息
        reassignApplication = reassignApplicationService.findByStuNo(stuNo);
        mv.addObject("reassignApplication", reassignApplication);
        mv.addObject("graduateDestination", graduateDestination);
        return mv;
    }

    /**
     *
     * 教师通过条件筛选学生就业意向
     * @param stuNo
     * @param name
     * @param departmentId
     * @param eduDegree
     * @param endDate
     * @param stuStatus
     * @param majorCode
     * @param recName
     * @param pageable
     * @return
     */
    @GetMapping("teacher/jobDestinationList")
    @ResponseBody
    public Page<GraduateDestination> jobList(String stuNo, String name, String departmentId, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable){
        UserPrincipal user = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        departmentId = "";
        if (!"00".equals(user.getDepId())){
            departmentId = user.getDepId();
        }
        Page<GraduateDestination> graduateDestinations = graduateDestinationService.findList(stuNo,name,departmentId,eduDegree,endDate,stuStatus,majorCode,recName,pageable);
        return graduateDestinations;
    }

//    @GetMapping("teacher/jobDestinationList")
//    public ModelAndView jobList(String stuNo, String name, String department, String eduDegree, String endDate, String stuStatus, String majorCode, String recName, Pageable pageable){
//        ModelAndView mv = new ModelAndView();
//        Page<StudentInfo> StudentInfos = studentInfoService.findList(stuNo,name,department,eduDegree,endDate,stuStatus,majorCode,recName,pageable);
//        mv.addObject("StudentInfos",StudentInfos);
//        return mv;
//    }

    @GetMapping("/teacher/jobAdd")
    public ModelAndView job(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("teacherHTML/tea_jobAdd");
        GraduateDestination graduateDestination = graduateDestinationService.findByStuId(id);
        mv.addObject("graduateDestination",graduateDestination);
        return mv;
    }

    @ModelAttribute("graduateTo")
    public Map<String, String> graduateTo() {
        return PubCodeUtil.getDictMap("graduateTo");
    }


    /**
     * 审核状态
     * @return
     */
    @ModelAttribute("auditStatus")
    public Map<String, String> auditStatus() {
        return PubCodeUtil.getDictMap("auditStatus");
    }

    /**
     * 院系
     * @return
     */
    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }

    /**
     * 专业
     * @return
     */
    @ModelAttribute("major")
    public Map<String, String> major() {
        return PubCodeUtil.getDictMap("major");
    }

    /**
     * 学历代码表映射
     * @return
     */
    @ModelAttribute("eduDegree")
    public Map<String, String> eduDegree() {
        return PubCodeUtil.getDictMap("eduDegree");
    }

    /**
     *工作类型表映射
     * @return
     */
    @ModelAttribute("jobType")
    public Map<String, String> jobType() {
        return PubCodeUtil.getDictMap("jobType");
    }

    /**
     *单位性质
     * @return
     */
    @ModelAttribute("nature")
    public Map<String, String> nature() {
        return PubCodeUtil.getDictMap("nature");
    }

    /**
     *单位行业
     * @return
     */
    @ModelAttribute("industry")
    public Map<String, String> industry() {
        return PubCodeUtil.getDictMap("industry");
    }


}
