package cn.edu.dlut.career.controller.company;
import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;
import cn.edu.dlut.career.service.company.RecBulletinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Created by wei on 2017/4/10.
 */
@Controller
public class RecBulletinQuery {
    Logger logger = LoggerFactory.getLogger(CompanyInfo.class);
    @Autowired
    private RecBulletinService recBulletinService;

    /**
     * 按条件查询招聘简章
     * @param title
     * @param auditStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/caompany/queryByCondition")
    public ModelAndView queryByCondition(String title, String auditStatus, LocalDateTime startTime, LocalDateTime endTime ){
        ModelAndView mv = new ModelAndView("companyHTML/recruitment");
        List<RecBulletin> recBulletins = null;
        recBulletins = recBulletinService.findByCondition(title,auditStatus,startTime,endTime);
        mv.addObject("recBulletins",recBulletins);
        return mv;
    }



}
