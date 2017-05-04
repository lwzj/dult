package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.service.company.RecJobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wei on 2017/4/10.
 */
@Controller
public class RecJobPositionQuery {
    @Autowired
    private RecJobPositionService recJobPositionService;

    @GetMapping("/company/queryByCondition")
    public ModelAndView queryByCondition(String name,String title,String category,String auditStatus){
        ModelAndView mv = new ModelAndView("");
        List<RecJobPosition> recJobPositions = recJobPositionService.findByCondition(name,title,category,auditStatus);
        mv.addObject("recJobPositions",recJobPositions);
        return mv;
    }
}
