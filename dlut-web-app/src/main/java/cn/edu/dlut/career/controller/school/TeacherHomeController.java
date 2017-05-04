package cn.edu.dlut.career.controller.school;


import cn.edu.dlut.career.domain.school.TeacherInfo;
import cn.edu.dlut.career.service.school.TeacherInfoService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 教师登录首页控制
 * Created by HealerJean on 2017/4/17.
 */
@Controller
public class TeacherHomeController {

    @Autowired
    private TeacherInfoService teacherInfoService;

    @GetMapping("/teacher/index.html")
    public ModelAndView stuHome(){
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        TeacherInfo teacherInfo = teacherInfoService.findById(userPrincipal.getId());

        ModelAndView mav = new ModelAndView("teacherHTML/tea_index");
        mav.addObject("teacherInfo",teacherInfo);
        return mav;
    }
}
