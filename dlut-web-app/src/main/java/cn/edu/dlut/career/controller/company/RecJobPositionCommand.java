package cn.edu.dlut.career.controller.company;


import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.AuditDTO;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;
import cn.edu.dlut.career.service.company.RecJobPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/27.
 * <p>
 * 招聘职位 增删改
 */
@Controller
@RequestMapping
@Transactional
public class RecJobPositionCommand {
    @Autowired
    private RecJobPositionService recJobPositionService;

    private Logger logger = LoggerFactory.getLogger(RecJobPositionCommand.class);

    /**
     * 招聘职位查询页面的跳转
     * @return
     */
    @GetMapping("/company/recruitment2.html")
    public String recruitment(){
        return "companyHTML/recruitment2";
    }

    @GetMapping("/company/recruitment-step2.html")
    public String recruitmentStep(){
        return "companyHTML/recruitment-step2";
    }

    /**
     * 添加招聘职位
     * @param recJobPositionDTOs
     * @return
     */
    @PostMapping("company/RecJob")
    public String saveRecJob(@RequestBody List<RecJobPositionDTO> recJobPositionDTOs) {
        List<RecJobPosition> recJobPositions = new ArrayList<RecJobPosition>();
        recJobPositions = recJobPositionService.saveRecJobPosition(recJobPositionDTOs);
        if(recJobPositions!=null){
            return "companyHTML/recruitment-step3";
        }else{
            return "companyHTML/recruitment-step2";
        }


    }

    /**
     * 删除 招聘职位信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/company/delete/{id}")
    @ResponseBody //该注释用于前期测试时使用 后期页面跟上后删除
    public String deleteRecJob(@PathVariable UUID id) {
        RecJobPosition recJobPosition = recJobPositionService.findById(id);
        if (recJobPosition != null) {
            try {
                recJobPositionService.deleteById(id);
                return "删除成功";
            } catch (Exception e) {
                e.printStackTrace();
                return "不知名原因，删除失败";
            }
        } else {
            return "该职位不存在，删除失败";
        }
    }

    /**
     * 修改审核信息
     *
     * @return
     */
    @PutMapping("/company/updateAudit/{id}")
    @ResponseBody //该注释用于前期测试时使用 后期页面跟上后删除
    public String updateAudit(@PathVariable UUID id, @RequestBody AuditDTO auditDTO) {

        String auditStatus = auditDTO.getAuditStatus();
        LocalDateTime auditTime = LocalDateTime.now();
        String auditor = auditDTO.getAuditor();
        String nopassReason = auditDTO.getNopassReason();

        String result = recJobPositionService.updateAudit(id,auditStatus,auditor,auditTime,nopassReason);
        return result;
    }

}
