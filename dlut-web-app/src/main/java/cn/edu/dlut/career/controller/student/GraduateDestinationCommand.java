package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.service.student.GraduateDestinationService;
import cn.edu.dlut.career.service.student.StudentInfoService;
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

import java.util.Map;

/**
 * Created by wei on 2017/4/14.
 */
@Controller
@Transactional
public class GraduateDestinationCommand {
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private GraduateDestinationService graduateDestinationService;




    /**
     * 教师添加、修改学生的就业去向
     * @param graduateDestination
     * @return
     */
    @PostMapping("teacher/jobDestinationSave")
    public String  addJob(GraduateDestination graduateDestination){
        graduateDestination.setStatus("0");
        graduateDestinationService.save(graduateDestination);
        return "redirect:/teacher/jobAdd?id="+graduateDestination.getId();
    }

    @ModelAttribute("graduateTo")
    public Map<String, String> offerStatus() {
        return PubCodeUtil.getDictMap("graduateTo");
    }


    //学生确认自己的就业去向信息
    @GetMapping("student/stuStatus")
    public String stuStatus(){
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal  = (UserPrincipal) subject.getPrincipal();
        graduateDestinationService.update(userPrincipal.getId());
        return "redirect:/student/JobDestination?stuId="+userPrincipal.getId();
    }

}
