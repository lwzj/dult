package cn.edu.dlut.career.controller;

import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private HttpServletRequest request;

    /**
     * 登录后展示的首页
     *
     * @return
     */
    @GetMapping( {"/", "index.html"})
    public ModelAndView dashboard() {
        Subject currentUser = SecurityUtils.getSubject();
        logger.info("Show unitInformation");
        UserPrincipal userPrincipal = (UserPrincipal) currentUser.getPrincipal();
        ModelAndView mav = new ModelAndView();
        HttpSession session= request.getSession();
        //身份信息放入session
        logger.info(userPrincipal.getRole()+"***"+userPrincipal.getDepName());
        session.setAttribute("user",userPrincipal);
        if (userPrincipal.getRole().equals("STUDENT")){
            mav.setViewName("redirect:/student/index.html");
        }else if(userPrincipal.getRole().equals("COMPANY")){
            mav.setViewName("redirect:/company/index.html");
            //教师类型（院级/校级）
        }else if (userPrincipal.getRole().equals("TEACHER")){
            mav.setViewName("redirect:/teacher/index.html");
        }
        return mav;
    }
}
