package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.ResponseInfo;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/4/18.
 */
@Controller
public class StudentInfoQuery {
    Logger logger = LoggerFactory.getLogger(StudentInfoCommand.class);
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private HttpServletRequest request;

    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("allGender",  PubCodeUtil.getDictMap("gender"));
        map.put("allMajor",   PubCodeUtil.getDictMap("major"));
        map.put("allEthnics", PubCodeUtil.getDictMap("ethnic"));
        map.put("allEduMode", PubCodeUtil.getDictMap("eduMode"));
        map.put("allEduYear", PubCodeUtil.getDictMap("eduYear"));
        map.put("allAcademy", PubCodeUtil.getDictMap("academy"));
        map.put("allAuditStatus", PubCodeUtil.getDictMap("auditStatus"));
        map.put("allEduDegree", PubCodeUtil.getDictMap("eduDegree"));
        map.put("allPolitical", PubCodeUtil.getDictMap("political"));
        map.put("allLanguage", PubCodeUtil.getDictMap("language"));
    }


    /**
     * 学生学籍信息查看 页面
     * @return
     */
    @GetMapping("/student/stuInformation.html")
    public ModelAndView stuInformation(){
        Subject currentUser = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal)currentUser.getPrincipal();;
        StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
        ModelAndView mav =new ModelAndView();
        if (studentInfo.getHaveReport().equals("0")){
            mav.setViewName("studentHTML/stu_information");
        }else {
            mav.setViewName("studentHTML/stu_information2");
        }
        mav.addObject("studentInfo", studentInfo);
        return mav;
    }


    /**
     * 查看表中是否已经存在用户输入的密码
     * @return
     */
    @GetMapping("/student/findPwd")
    @ResponseBody
    public ResponseInfo findByPwd(String pwd){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        StuLoginDTO stuLoginDTO = studentInfoService.findLoginInfo(userPrincipal.getUserName());
        String pwdStatus = studentInfoService.findPwd( userPrincipal.getId(),  pwd,  stuLoginDTO.getPwd(), stuLoginDTO.getSalt());
        ResponseInfo responseInfo = new ResponseInfo();

        if(pwdStatus != null){
            responseInfo.setStatus(200);
            responseInfo.setMessage("密码存在");
        }else {
            responseInfo.setStatus(403);
            responseInfo.setMessage("密码不存在");
        }
        return responseInfo;
    }


    /**
     * 张宇晋
     * 教师端，院系生源显示页面
     * @return
     */
    @GetMapping("/teacher/teaEnrollment.html")
    public String departStusHtml(){
        return "teacherHTML/tea_enrollment";
    }

    /**
     * 张宇晋
     * 教师端，本院系生源信息审核页面
     * @return
     */
    @GetMapping("/teacher/teaDepartStusAudit.html")
    public String departStusAudit(){
        return "teacherHTML/tea_enrollment2";
    }





    /**
     * 教师端
     * 分页查询 学生学籍信息
     */
    @GetMapping("/teacher/listDepartStuPage")
    @ResponseBody
    public   Page<StudentInfo> listDepartStuPage(String name, String stuNo, String gender, String ethnic, String departmentId, String majorCode, String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree , Pageable pageable) {
        Page<StudentInfo> StudentInfos = studentInfoService.listDepartStuPage(name, stuNo, gender, ethnic, departmentId, majorCode, eduYear, eduMode, endDate, tutorName, status, eduDegree,pageable);
        if (StudentInfos!=null) {
            return  StudentInfos;
        }else {
            return  null;
        }
    }

    /**
     * 教师端 (校级可以随意查看，修改)
     * 分页查询 某个学院的学生学籍 将来用于修改学生学籍
     */
    @GetMapping("/teacher/listMyDepartStuPage")
    @ResponseBody
    public   Page<StudentInfo> listMyDepartStuPage(String name, String stuNo, String gender, String ethnic, String majorCode, String  departmentId ,String eduYear, String eduMode, String endDate, String tutorName, String status, String eduDegree , String haveReport, Pageable pageable) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        logger.info("院系id"+departmentId);
        if (!(userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN"))) {
            departmentId = userPrincipal.getDepId();
        }
            Page<StudentInfo> StudentInfos = studentInfoService.listMyDepartStuPage(name, stuNo, gender, ethnic, majorCode,  departmentId,eduYear, eduMode, endDate, tutorName, status, eduDegree,haveReport, pageable);
        if (StudentInfos!=null) {
            return  StudentInfos;
        }else {
            return  null;
        }
    }
    /**
     * 教师端
     * 查看某个学生的学籍信息 ，仅仅是查看
     */
    @GetMapping("/teacher/stuInformation")
    public   ModelAndView stuInfos(@RequestParam UUID id) {
        StudentInfo studentInfo = studentInfoService.findById(id);
        ModelAndView mav = new ModelAndView("teacherHTML/tea_informationDetails2");
        mav.addObject("studentInfo", studentInfo);
        return  mav;
    }


    /**
     * 教师端
     * 查看某个学生的学籍信息 ，并将来对其进行修改
     */
    @GetMapping("/teacher/editStuInformation")
    public   ModelAndView editStuInformation( String id) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        UUID sid = UUID.fromString(id);
        StudentInfo studentInfo = studentInfoService.findById(sid);
        ModelAndView mav = new ModelAndView("teacherHTML/tea_informationDetails");
        mav.addObject("principal",userPrincipal.getPrincipal());
        if (userPrincipal.getDepId().equals(studentInfo.getDepartmentId())||userPrincipal.getPrincipal().equals("SCHOOL")||userPrincipal.getPrincipal().equals("SCHADMIN")) {
            mav.addObject("studentInfo", studentInfo);
            return mav;
         }else {
            mav.addObject("studentInfo", studentInfo);
            mav.addObject("errorMsg","您只能修改本院系的学生学籍");
            return mav;
        }
    }




}
