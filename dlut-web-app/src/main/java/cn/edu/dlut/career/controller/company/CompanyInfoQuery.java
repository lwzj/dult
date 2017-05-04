package cn.edu.dlut.career.controller.company;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.service.company.CompanyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
@RestController
public class CompanyInfoQuery {
  Logger logger = LoggerFactory.getLogger(CompanyInfo.class);
  @Autowired
  private CompanyInfoService companyInfoService;

    /**
     * 根据id查询公司信息。
     * @param id
     * @return
     */
  @GetMapping("/company/companyInfo")
    public ModelAndView findCompany(UUID id){
      CompanyInfo companyInfo = companyInfoService.findOne(id);
      ModelAndView mav =new ModelAndView();
      mav.setViewName("companyHTML/unitInformation");
      mav.addObject("companyInfo",companyInfo);
      return mav;
  }

}
