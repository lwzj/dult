package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.dto.company.RecBulletinApplicationDTO;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;
import cn.edu.dlut.career.service.company.RecBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 增删改
 */
@Controller
@RequestMapping
@Transactional
public class RecBulletinCommand {

    @Autowired
    private RecBulletinService recBulletinService;

    /**
     * 招聘简章查询页面的跳转
     * @return
     */
    @GetMapping("/company/recruitment.html")
    public String recruitment(){
        return "companyHTML/recruitment";
    }
    /**
     * 招聘简章添加页面的跳转
     * @return
     */
    @GetMapping("/company/recruitment-step1.html")
    public String recruitmentStep1(){
        return "companyHTML/recruitment-step1";
    }

    /**
     * 添加招聘简章
     * @param recBulletinDTO
     * @return
     */
    @PostMapping("company/RecBullrtin")
    public String saveRecBulletin(@RequestBody RecBulletinDTO recBulletinDTO){
        RecBulletin recBulletin = recBulletinService.saveRecBulletin(recBulletinDTO);
        if(recBulletin!=null){
            //添加成功后跳转到添加职位页面
            return "companyHTML/recruitment-step2";
        }else{
            //添加失败后跳转到添加简章页面
            return "companyHTML/recruitment-step1";
        }

    }

}
