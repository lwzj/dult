package cn.edu.dlut.career.controller.company;

import javax.servlet.http.HttpServletRequest;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.dto.company.RecOfferDTO;
import cn.edu.dlut.career.service.company.RecOfferService;
import cn.edu.dlut.career.service.student.GraduateDestinationService;
import cn.edu.dlut.career.service.student.StudentInfoService;
import cn.edu.dlut.career.service.student.ViolateApplicationService;
import cn.edu.dlut.career.shiro.UserPrincipal;
import cn.edu.dlut.career.util.PubCodeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/4/14.
 */
@Controller
@Transactional
public class RecOfferCommand {

    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private GraduateDestinationService graduateDestinationService;
    @Autowired
    private  RecOfferService recOfferService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ViolateApplicationService violateApplicationService;



    /**
     * 院系
     * @return
     */
    @ModelAttribute("academy")
    public Map<String, String> academy() {
        return PubCodeUtil.getDictMap("academy");
    }

    /**
     * 批量审核学生offer同意
     * @param
     * @return
     */
    @GetMapping("teacher/auditBatch")
    public String auditBatch(String[] box){
        UserPrincipal user = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        String auditStatus = "";
        String stuReceivingStatus = "";
        if(user.getPrincipal().equals("SCHOOL") || user.getPrincipal().equals("SCHADMIN")){
            auditStatus = "03";//校级审核通过
            stuReceivingStatus = "04";//签约
        }else if(user.getPrincipal().equals("ACADEMY") || user.getPrincipal().equals("ACADMIN")){
            auditStatus = "01";//院级审核通过
            stuReceivingStatus = "01";//offer接受
        }
        for (String uid:box
             ) {
            UUID uuid = UUID.fromString(uid);
            recOfferService.update(uuid,auditStatus,stuReceivingStatus);
            RecOffer recOffer = recOfferService.findOne(uuid);
            GraduateDestination graduateDestination = graduateDestinationService.findByStuId(recOffer.getStuId());
            graduateDestination.setDepartmentBelong(recOffer.getDepartmentBelong());
            graduateDestination.setDestinationType(recOffer.getDestinationType());
            graduateDestination.setHukouToAddress(recOffer.getHukouToAddress());
            graduateDestination.setJobType(recOffer.getCategory());
            graduateDestination.setRecName(recOffer.getRecName());
            graduateDestination.setRecCode(recOffer.getOrgCode());
            graduateDestination.setRecNature(recOffer.getNature());
            graduateDestination.setRecIndustry(recOffer.getIndustry());
            graduateDestination.setRecCity(recOffer.getRecCity());
            graduateDestination.setRecLinker(recOffer.getRecLinker());
            graduateDestination.setRecTelphone(recOffer.getRecTelphone());
            graduateDestination.setRecMobile(recOffer.getRecMobile());
            graduateDestination.setRecAddress(recOffer.getRecAddress());
            graduateDestination.setRecZipcode(recOffer.getRecZipcode());
            graduateDestination.setPfileToName(recOffer.getPfileToName());
            graduateDestination.setPfileToDepart(recOffer.getPfileToDepart());
            graduateDestination.setPfileToAddress(recOffer.getPfileToAddress());
            graduateDestination.setPfileToZipcode(recOffer.getPfileToZipcode());
            graduateDestination.setHukouIsSchool(recOffer.getIsSchHuKou());
            graduateDestination.setId(recOffer.getStuId());
            graduateDestination.setStatus("00");
            graduateDestinationService.save(graduateDestination);
        }
        return "teacherHTML/tea_agreementReview";
    }

    /**
     * 老师拒绝学生Offer
     * @param id
     * @param auditStatus
     * @param noPassReason
     * @return
     */
    @GetMapping("/teacher/noPassOffer")
    public String recOffer(@RequestParam UUID id, String auditStatus, String noPassReason){
        RecOffer recOffer = recOfferService.findOne(id);
        recOffer.setNoPassReason(noPassReason);
        recOffer.setAuditStatus(auditStatus);
        recOfferService.save(recOffer);
        return "teacherHTML/tea_agreementReview";
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
     * 老师单个审核学生offer，只有同意
     * @param id
     * @return
     */
    @GetMapping("/teacher/agreerecOffer")
    public String agreeRecoffer(@RequestParam UUID id){
        UserPrincipal user = (UserPrincipal)  SecurityUtils.getSubject().getPrincipal();
        String auditStatus = "";
        String stuReceivingStatus = "";
        if(user.getPrincipal().equals("SCHOOL") || user.getPrincipal().equals("SCHADMIN")){
            auditStatus = "03";//校级审核通过
            stuReceivingStatus = "04";//签约
            RecOffer recOffer = recOfferService.findOne(id);
            GraduateDestination graduateDestination = graduateDestinationService.findByStuId(recOffer.getStuId());
            graduateDestination.setDepartmentBelong(recOffer.getDepartmentBelong());
            graduateDestination.setDestinationType(recOffer.getDestinationType());
            graduateDestination.setHukouToAddress(recOffer.getHukouToAddress());
            graduateDestination.setJobType(recOffer.getCategory());
            graduateDestination.setRecName(recOffer.getRecName());
            graduateDestination.setRecCode(recOffer.getOrgCode());
            graduateDestination.setRecNature(recOffer.getNature());
            graduateDestination.setRecIndustry(recOffer.getIndustry());
            graduateDestination.setRecCity(recOffer.getRecCity());
            graduateDestination.setRecLinker(recOffer.getRecLinker());
            graduateDestination.setRecTelphone(recOffer.getRecTelphone());
            graduateDestination.setRecMobile(recOffer.getRecMobile());
            graduateDestination.setRecAddress(recOffer.getRecAddress());
            graduateDestination.setRecZipcode(recOffer.getRecZipcode());
            graduateDestination.setPfileToName(recOffer.getPfileToName());
            graduateDestination.setPfileToDepart(recOffer.getPfileToDepart());
            graduateDestination.setPfileToAddress(recOffer.getPfileToAddress());
            graduateDestination.setPfileToZipcode(recOffer.getPfileToZipcode());
            graduateDestination.setHukouIsSchool(recOffer.getIsSchHuKou());
            graduateDestination.setId(recOffer.getStuId());
            graduateDestination.setStatus("00");
            graduateDestinationService.save(graduateDestination);
        }else if(user.getPrincipal().equals("ACADEMY") || user.getPrincipal().equals("ACADMIN")){
            auditStatus = "01";//院级审核通过
            stuReceivingStatus = "01";//offer接受
        }
        recOfferService.update(id,auditStatus,stuReceivingStatus);
        return "teacherHTML/tea_agreementReview";
    }
    /**
     * 学生同意offer
     * @param id
     */
    @GetMapping("student/updateOffer1")
    public ModelAndView agree(@RequestParam UUID id){
        ModelAndView mv = new ModelAndView("studentHTML/stu_offer");
        RecOffer recOffer = recOfferService.findOne(id);
        RecOfferDTO recOfferDTOS = null;
        recOfferDTOS = recOfferService.findByStuId(recOffer.getStuId());
        if(recOfferDTOS!=null) {
            mv.addObject("errorMessage","你已经有一份接受的Offer了！");
        }else{
            recOffer.setStuReceivingStatus("01");
            recOfferService.save(recOffer);
        }
        return mv;
    }

    /**
     * 学生拒绝offer
     * @param id
     */
    @GetMapping("student/updateOffer2")
    public String refuse(@RequestParam UUID id){
        RecOffer recOffer = recOfferService.findOne(id);
        recOffer.setStuReceivingStatus("02");
        recOfferService.save(recOffer);
        return "studentHTML/stu_offer";
    }

    /**
     * offer状态
     * @return
     */
    @ModelAttribute("offerStatus")
    public Map<String, String> offerStatus() {
        return PubCodeUtil.getDictMap("offerStatus");
    }

}
