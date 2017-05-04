package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.CarreFairEntry;
import cn.edu.dlut.career.service.company.CareerFairEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 Controller
 * 修改预约审核状态
 * Created by HealerJean on 2017/4/7.
 */

@Controller
@RequestMapping("/company")
@Transactional
public class CareerFairEntryQuery {
    private  Logger logger = LoggerFactory.getLogger(CareerFairEntryQuery.class);
    @Autowired
    private  CareerFairEntryService careerFairEntryService;

    /**
     * 修改
     * 双选会，招聘会审核状态  ：
     */
    @PutMapping(value = "/updateCareerFairAudit.index")
    @ResponseBody
    public int updateCareerFairAudit(UUID id, String auditStatus, LocalDateTime auditTime, String auditor, String nopass_reason){

        try {
            return careerFairEntryService.updateCarreFairEntryAudit(id,auditStatus,auditTime,auditor,nopass_reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
