package cn.edu.dlut.career.controller.student;

import cn.edu.dlut.career.domain.student.ReassignApplication;
import cn.edu.dlut.career.dto.ResponseInfo;
import cn.edu.dlut.career.service.student.ReassignApplicationService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * 改派申请
 *
 * Created by HealerJean on 2017/4/13.
 */
@Controller
public class ReassignApplyQuery {
    @Autowired
    private ReassignApplicationService reassignApplicationService;

    Logger logger = LoggerFactory.getLogger(ReassignApplyQuery.class);

    /**
     * 张宇晋
     * tea_employmentAlertDetails.html
     * @param map
     */
    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("allAuditStatus", PubCodeUtil.getDictMap("auditStatus"));
    }

    /**
     * 进入学生改派申请的页面
     * @return
     */
    @GetMapping("/student/reassignApplyInfo")
    public ModelAndView reassignApplyInfo(){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        ReassignApplication reassignApplication = reassignApplicationService.findByStuNo(userPrincipal.getUserName());
        ModelAndView mav = new ModelAndView("studentHTML/stu_employment_look");
        mav.addObject("reassignApplication",reassignApplication);
        return mav;
    }


    /**
     * 张宇晋
     * 查看 学生是不是已经具有违约申请表了
     * @return
     */
    @GetMapping("/student/haveReassignApplication")
    @ResponseBody
    public ResponseInfo haveReassignApplication(){
        UserPrincipal userPrincipal = (UserPrincipal)SecurityUtils.getSubject().getPrincipal();
        ReassignApplication reassignApplication = reassignApplicationService.findByStuNo(userPrincipal.getUserName());
        ResponseInfo responseInfo = new ResponseInfo();
        if(reassignApplication != null){
            responseInfo.setStatus(1);
            responseInfo.setMessage("已经存在改派申请位，不可以再次申请");
        }else {
            responseInfo.setStatus(0);
            responseInfo.setMessage("改派申请不存在，可以申请");
        }
        return responseInfo;
    }


    /**
     * 教师端改派查询，进行分页
     */
    @GetMapping("/teacher/listRessignApplicationPage")
    @ResponseBody
    public Page<ReassignApplication> listRessignApplication(String stuNo, String  stuName, String majorCode, String eduDegree, String departmentId, String auditStatus, Pageable pageable){
            logger.info("stuNo:"+stuNo);
            Page<ReassignApplication> reassignApplications =  reassignApplicationService.listRessignApplication(stuNo,stuName,majorCode,eduDegree,departmentId,auditStatus,pageable);
            if (reassignApplications!=null){
                return  reassignApplications;
            }
            return  null;
    }

    /**
     * 教师端
     * 改派申请的进入编辑某个学生的页面
     * @param id
     * @return
     */
    @GetMapping("/teacher/editRessignApplication")
    public  ModelAndView editRessignApplication(@RequestParam UUID id) {
        ModelAndView mav = new ModelAndView();
        ReassignApplication reassignApplication = reassignApplicationService.findById(id);
        if (reassignApplication != null) {
            mav.setViewName("teacherHTML/tea_employmentAlertDetails");
            mav.addObject("reassignApplication", reassignApplication);
            return  mav;
        }
        return  null;
    }
}
