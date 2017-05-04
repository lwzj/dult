package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.StudentInfo;
import cn.edu.dlut.career.dto.student.StuLoginDTO;
import cn.edu.dlut.career.dto.student.StudentInfoDTO;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/4/12.
 */
@Controller
@Transactional
public class StudentInfoCommand {

    Logger logger = LoggerFactory.getLogger(StudentInfoCommand.class);
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private HttpServletRequest request;



    @ModelAttribute
    public void getMap(Map<String, Object> map) {
        map.put("allGender", PubCodeUtil.getDictMap("gender"));
        map.put("allMajor", PubCodeUtil.getDictMap("major"));
        map.put("allEduMode", PubCodeUtil.getDictMap("eduMode"));
        map.put("allEduYear", PubCodeUtil.getDictMap("eduYear"));
        map.put("allAcademy", PubCodeUtil.getDictMap("academy"));
        map.put("allAuditStatus", PubCodeUtil.getDictMap("auditStatus"));
        map.put("allEduDegree", PubCodeUtil.getDictMap("eduDegree"));
        map.put("allPolitical", PubCodeUtil.getDictMap("political"));
        map.put("allLanguage", PubCodeUtil.getDictMap("language"));
        map.put("allEthnics", PubCodeUtil.getDictMap("ethnic"));
    }

    /**
     * 学生修改学生学籍 ，进行确认
     *
     * @return
     */

    @PostMapping("/student/updateInformation")

    public ModelAndView stuSaveInformation(StudentInfoDTO studentInfoDTO) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        studentInfoDTO.setId(userPrincipal.getId());
        ModelAndView mav = new ModelAndView();
        StudentInfo studentInfo = studentInfoService.updateStudentInfo(studentInfoDTO);

        if (studentInfo != null) {
            //判断是修改还是全部提交
            if (studentInfoDTO.getAllupdateStatus()!=null){ //全部提交
                mav.setViewName("studentHTML/stu_information2");
                mav.addObject("studentInfo", studentInfo);
            }else{ //修改  还是返回刚刚的页面
                mav.setViewName("studentHTML/stu_information");
                mav.addObject("studentInfo", studentInfo);
            }
        } else {
            mav.setViewName("studentHTML/stu_information");
            StudentInfo studentInfoMsg = studentInfoService.findById(userPrincipal.getId());
            mav.addObject("studentInfo", studentInfoMsg);
            mav.addObject("errorMsg", "您已经审核过了，不能再进行修改");
        }
        return mav;
    }

    /**
     * 更新密码
     *
     * @return
     */
    @PostMapping("/student/updatePwd")
    public ModelAndView updatePwd(String pwd, String newPwd) {
        ModelAndView mav = null;
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        StuLoginDTO stuLoginDTO = studentInfoService.findLoginInfo(userPrincipal.getUserName());
        String pwdStatus = studentInfoService.findPwd(userPrincipal.getId(), pwd, stuLoginDTO.getPwd(), stuLoginDTO.getSalt());
        if (pwdStatus != null) {
            int falseNo = studentInfoService.updatePwd(userPrincipal.getId(), newPwd, stuLoginDTO.getSalt());
            if (falseNo != 0) {
                logger.info("修改成功");
                SecurityUtils.getSubject().logout();
                mav = new ModelAndView("redirect:/login.html");
                return mav;
            } else {
                logger.info("修改失败");
            }
        } else {
            StudentInfo studentInfo = studentInfoService.findById(userPrincipal.getId());
            mav = new ModelAndView("studentHTML/stu_index");
            mav.addObject("studentInfo", studentInfo);
            mav.addObject("errorMsg", "您输入的密码错误，请重新输入密码");
        }
        return mav;
    }


    /**
     * 教师端
     * 修改学生学籍 ，进行确认
     *
     * @return
     */
    @PostMapping("/teacher/teaUpdateStuInfo")
    public ModelAndView teaUpdateStudentInfo(StudentInfoDTO studentInfoDTO) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        ModelAndView mav = new ModelAndView();
        StudentInfo studentInfo = studentInfoService.teaUpdateStudentInfo(studentInfoDTO, userPrincipal.getDepId());
        if (studentInfo != null) {
            mav.setViewName("teacherHTML/tea_informationDetails");
            mav.addObject("studentInfo", studentInfo);
            mav.addObject("principal",userPrincipal.getPrincipal());
        } else {
            mav.setViewName("teacherHTML/tea_informationDetails");
            mav.addObject("studentInfo", studentInfoDTO);
            mav.addObject("principal",userPrincipal.getPrincipal());
            mav.addObject("errorMsg", "非法访问或身份错误");
        }
        return mav;
    }


    /**
     * 教师端
     * 审核通过单个学生学籍
     */
    @GetMapping("/teacher/stuInfoAuditPass")
    @ResponseBody
    public String stuInfoAuditPass(@RequestParam UUID id) {
        StudentInfo studentInfo = studentInfoService.teaUpdateStuInfoAuditStatus(id);
        if (studentInfo != null) { //审核通过进入查看页面
            return "修改成功";
        } else {
            return "非法访问或身份错误";
        }
    }

    /**
     * 教师端
     * 批量审核通过单个学生学籍
     */
    @GetMapping("/teacher/stuInfoBatchAudit")
    @ResponseBody
    public String stuInfoBatchAudit(@RequestParam UUID[] id) {
        List<StudentInfo> studentInfos = studentInfoService.stuInfoBatchAudit(id);
        if (studentInfos.size() != 0) { //审核通过进入查看页面*/
            return "批量修改成功";
        } else {
            return "非法访问或身份错误";
        }

    }
}

