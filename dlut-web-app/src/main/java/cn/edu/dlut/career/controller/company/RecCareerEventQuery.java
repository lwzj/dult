package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.CareerFairIssue;
import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.service.company.RecCareerEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 专场招聘 预约申请
 * Created by HealerJean on 2017/4/10.
 */

@Controller
@Transactional
public class RecCareerEventQuery {

    Logger logger = LoggerFactory.getLogger(RecCareerEventQuery.class);
    @Autowired
    private RecCareerEventService recCareerEventService;

    /**
     * 通过id查找预约申请表
     */
    @GetMapping("/company/findRecCareerEvent")
    @ResponseBody
    public ModelAndView findRecCareerEvent(UUID id, ModelAndView modelAndView)  {
        try {
            RecCareerEvent recCareerEvent=  recCareerEventService.findById(id);

            modelAndView.addObject("recCareerEvent",recCareerEvent);
            modelAndView.setViewName("/companyHTML/unitappointment");
            return  modelAndView;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            logger.info("RecCareerEventQuery.findRecCareerEvent");
        }
        return null;
    }

    /**
     * 动态查询 ，通过 申请日期（区间），开始日期（区间），审核状态，以及 招聘会名称
     */
    @GetMapping("/caompany/queryRecByCondition")
    public ModelAndView queryRecByCondition(LocalDateTime applicationTime,LocalDateTime applicationEndTime, LocalDateTime fairStartTime,LocalDateTime fairEndTime, String auditStatus,String fairName){
        ModelAndView mv = new ModelAndView("测试");
        List<RecCareerEvent> recCareerEvents = recCareerEventService.queryRecByCondition( applicationTime, applicationEndTime,  fairStartTime, fairEndTime,  auditStatus, fairName);
            mv.addObject("recCareerEvents",recCareerEvents);
            return mv;
        }
}
/*
    @PostMapping ("/caompany/queryRecByCondition")
    public  List<RecCareerEvent> queryRecByCondition(Date applicationTime,Date applicationEndTime, Date startTime,Date startEndTime, String auditStatus,String fairName){
        List<RecCareerEvent> recCareerEvents = recCareerEventService.queryRecByCondition( this.DateToLocalDateTime(applicationTime),this.DateToLocalDateTime(applicationEndTime) ,  this.DateToLocalDateTime(startTime),  this.DateToLocalDateTime(startEndTime),  auditStatus, fairName);
        return recCareerEvents;
    }
    //将url字符串日期转化为Date 类型
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    //将Date转化为LocalDateTime
    public LocalDateTime DateToLocalDateTime(Date auditTime){
        Instant instant = Instant.ofEpochMilli(auditTime.getTime());
        LocalDateTime res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return  res;
    }
*/

