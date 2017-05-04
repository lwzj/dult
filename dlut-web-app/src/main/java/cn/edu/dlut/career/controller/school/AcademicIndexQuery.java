package cn.edu.dlut.career.controller.school;

import cn.edu.dlut.career.dto.school.AcademicIndexDTO;
import cn.edu.dlut.career.service.school.AcademicIndexService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by 史念念 on 2017/4/28.
 *
 * 院系老师首页信息 控制层
 */
@Controller
public class AcademicIndexQuery {
    @Autowired
    private AcademicIndexService academicIndexService;

    /**
     * 查询首页信息
     * @param model
     * @param endDate 毕业时间
     * @return
     */
    @GetMapping("teacher/academicIndex")
    public String findAll(Model model,String endDate){
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();

        //院系id
        String departmentId = userPrincipal.getDepId();

        AcademicIndexDTO aid = academicIndexService.findAll(departmentId,endDate);
        model.addAttribute("aid",aid);

        return "teacherHTML/tea_academicIndex";
    }
}
