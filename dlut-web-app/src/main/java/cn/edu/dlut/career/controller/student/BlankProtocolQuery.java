package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.BlankProtocol;
import cn.edu.dlut.career.service.student.BlankProtocolService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/25.
 * <p>
 * 空白协议书 查询 页面跳转
 */
@Controller
public class BlankProtocolQuery {
    @Autowired
    private BlankProtocolService blankProtocolService;

    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }
    @ModelAttribute("blankProReason")
    public Map<String, String> blankProReason() {
        return PubCodeUtil.getDictMap("blankProReason");
    }
    @ModelAttribute("totalAuditStatus")
    public Map<String, String> totalAuditStatus() {
        return PubCodeUtil.getDictMap("totalAuditStatus");
    }

    /**
     *该方法用于教师端 动态查询空白协议书
     * @param stuNo 学生学号 要求精确
     * @param name 学生姓名 可模糊
     * @param auditStatus 审核状态
     * @param departmentId 院系
     * @param pageable
     * @return
     */
    @GetMapping("/teacher/findBlankProtocol")
    @ResponseBody
    public Page<BlankProtocol> findBlankProtocol(String stuNo, String name, String auditStatus, String departmentId, Pageable pageable){

        if("".equals(stuNo)){
            stuNo = null;
        }
        if("".equals(name)){
            name = null;
        }
        if("".equals(auditStatus)){
            auditStatus = null;
        }
        if("".equals(departmentId)){
            departmentId = null;
        }

        Page<BlankProtocol> bps = blankProtocolService.findBlankProtocol(stuNo,name,auditStatus,departmentId,pageable);
        return bps;
    }

    /**
     * 该方法用于教师端 老师进入空白协议书页面
     * @return
     */
    @GetMapping("/teacher/teaBlankBook")
    public String teaBlankBook(){ return "teacherHTML/tea_blankBook";}

    /**
     * 教师端 根据id查找空白协议书 进入教师空白协议书详情页面
     * @return
     */
    @GetMapping("/teacher/blankBookLook")
    public ModelAndView findBlankPro(@RequestParam UUID bid) {
        ModelAndView mv = new ModelAndView("teacherHTML/tea_blankBookDetails");

        BlankProtocol b = blankProtocolService.findById(bid);

        mv.addObject("bp",b);
        return mv;
    }

    /**
     * 跳转到申请空白协议书页面
     * @return
     */
    @GetMapping("/student/stuBlankBookNew")
    public String goBlankBookNew() {
        return "studentHTML/stu_blankBookNew";
    }


    /**
     * 学生端根据id查找空白协议书 跳转到学生端空白协议书详情页
     * @return
     */
    @GetMapping("/student/blankBookLook")
    public ModelAndView findBPro(@RequestParam UUID bid) {
        ModelAndView mv = new ModelAndView("studentHTML/stu_blankBookLook");

        BlankProtocol b = blankProtocolService.findById(bid);

        mv.addObject("bp",b);
        return mv;
    }

    /**
     * 页面跳转到 空白协议书页面
     * @return
     */
    @GetMapping("/student/stuBlankBook")
    public ModelAndView stuBlankBook(){
        ModelAndView mv = new ModelAndView("studentHTML/stu_blankBook");
        Subject subject = SecurityUtils.getSubject();

        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        UUID stuId = userPrincipal.getId();
        List<BlankProtocol> bps = blankProtocolService.findByStuId(stuId);

        mv.addObject("bps",bps);
        return mv;
    }

}
