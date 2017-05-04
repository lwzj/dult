package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.CareerFairIssue;
import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;
import cn.edu.dlut.career.service.company.RecCareerEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 组团 预约申请
 * Created by HealerJean on 2017/4/10.
 */
@Controller
@Transactional
public class RecCareerEventCommand {

    Logger logger = LoggerFactory.getLogger(RecCareerEventCommand.class);
    @Autowired
    private RecCareerEventService recCareerEventService;

    /**
     * 进入添加页面
     */
    @GetMapping("/company/appointment.html")
    public String saveRecCareerEventHtml()  {
        return "companyHTML/appointment";
    }

    /**
     * 保存组团预约申请
     * @return
     */
    @PostMapping("/company/addRecCareerEvent")
    public String saveRecCareerEvent(RecCareerEventDTO recCareerEventDTO)  {
        logger.info("/company/addRecCareerEvent");

       try {
           RecCareerEvent recCareerEvent= recCareerEventService.saveRecCareerEvent(recCareerEventDTO);
            if (recCareerEvent!=null) {
                return "companyHTML/registerSuccess";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "companyHTML/index";
    }

    /**
     * 修改 更新专场预约申请
     * @return
     */
    @PostMapping ("/company/updateRecCareerEven")
    public String updateRecCareerEvent(RecCareerEventDTO recCareerEventDTO)  {
        logger.info("/company/update/recCareerEven");
        //System.out.println(recCareerEventDTO.getEndTime()+"********");
        try {
           /* String auditStatus = recCareerEventService.auditStatusFindByid(recCareerEventDTO.getId());
          //审核状态通过不能修改
            if((auditStatus.trim()).equals("1")) {*/
                RecCareerEvent recCareerEvent = recCareerEventService.updateRecCareerEvent(recCareerEventDTO);
               /* if (recCareerEvent != null) {
                    return "registerSuccess";
                }
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return "companyHTML/appointment";
    }
}
