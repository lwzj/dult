package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.domain.student.ViolateApplication;
import cn.edu.dlut.career.dto.company.RecOfferDTO;
import cn.edu.dlut.career.service.company.RecOfferService;
import cn.edu.dlut.career.service.school.TeacherInfoService;
import cn.edu.dlut.career.service.student.ViolateApplicationService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
@Controller
@RequestMapping
@Transactional
public class RecOfferQuery {
    @Autowired
    private ViolateApplicationService violateApplicationService;
    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RecOfferService recOfferService;


    @GetMapping("/teacher/tea_agreementReview.html")
    public String tea_agreementReview(){
        return "/teacherHTML/tea_agreementReview";
    }



    /*
        三方协议页面
     */

    @GetMapping("/student/agreement.html")
    public ModelAndView recOffer() {
        ModelAndView modelAndView = null;
        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
        RecOffer recOffer = recOfferService.findRecOfferByStuNo(userPrincipal.getUserName());
       if (recOffer!=null) {
           ViolateApplication violateApplication = violateApplicationService.findByStuNo(userPrincipal.getUserName());
           modelAndView = new ModelAndView("studentHTML/stu_agreement");
           modelAndView.addObject("violateApplication", violateApplication);
           modelAndView.addObject("recOffer", recOffer);
       }else {
           modelAndView = new ModelAndView("studentHTML/stu_agreementNull");
       }
             return modelAndView;


    }

    @GetMapping("student/stu_offer.html")
    public String goToOffer(){
        return "studentHTML/stu_offer";
    }

    /**
     * 学生查询自己的offer
     * @param stuNo
     * @return
     */
    @GetMapping("student/RecOffer")
    @ResponseBody
    public List<RecOfferDTO> RecOffer(String stuNo,String stuReceivingStatus){
        List<RecOfferDTO> recOfferDTOS = null;
        if(stuReceivingStatus.equals("06")){
            recOfferDTOS = recOfferService.findByStuNo(stuNo);
        }else {
            recOfferDTOS = recOfferService.findByStuNos(stuNo, stuReceivingStatus);
        }
        return recOfferDTOS;
    }

    /**
     * 查看某条offer的详情
     * @param id
     * @return
     */
    @GetMapping("student/myoffer")
    public ModelAndView findOne(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("studentHTML/stu_offerDetails");
        Subject subject = SecurityUtils.getSubject();
        UserPrincipal userPrincipal = (UserPrincipal) subject.getPrincipal();
        RecOffer recOffer = recOfferService.findOne(id);
        RecOfferDTO recOffers = recOfferService.findByStuId(userPrincipal.getId());
        mv.addObject("recOffer",recOffer);
        mv.addObject("recOffers",recOffers);
        return mv;
    }
    /**
     * offer状态
     * @return
     */
    @ModelAttribute("offerStatus")
    public Map<String, String> offerStatus() {
        return PubCodeUtil.getDictMap("offerStatus");
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
     * 审核状态
     * @return
     */
    @ModelAttribute("totalAuditStatus")
    public Map<String, String> totalAuditStatus() {
        return PubCodeUtil.getDictMap("totalAuditStatus");
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
     * 老师通过条件筛选符合的offer,
     * @param stuNo
     * @param realName
     * @param department
     * @param eduDegree
     * @param endDate
     * @param stuReceivingStatus
     * @param majorCode
     * @param recName
     * @param pageable
     * @return
     */
    @GetMapping("/teacher/RecOffers")
    @ResponseBody
    public Page<RecOffer> recOffres(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable){
        UserPrincipal user = (UserPrincipal) request.getSession().getAttribute("user");
        Page<RecOffer> recOffers = null;
        String departmentId = "";
        if (!"00".equals(user.getDepId())){
            departmentId = user.getDepId();
        }
            recOffers = recOfferService.findRecOfferDynamic(stuNo,realName,departmentId,eduDegree,endDate,stuReceivingStatus,majorCode,recName,pageable);


        if(recOffers!=null) {
            return recOffers;
        }else {
            return null;
        }
    }

    /**
     * 根据ID查询简历详情
     * @param id
     * @return
     */
    @GetMapping("/teacher/RecOfferById")
    public ModelAndView findById(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("/teacherHTML/tea_agreementReview");
        RecOffer recOffer = recOfferService.findOne(id);
        UserPrincipal user = (UserPrincipal) request.getSession().getAttribute("user");
        if(user.getPrincipal().equals("SCHOOL") || user.getPrincipal().equals("SCHADMIN")){
            mv.setViewName("/teacherHTML/tea_agreement2");
        }else if(user.getPrincipal().equals("ACADEMY") || user.getPrincipal().equals("ACADMIN")){
            mv.setViewName("/teacherHTML/tea_agreement");
        }
        mv.addObject("recOffer",recOffer);
        return mv;
    }
}
