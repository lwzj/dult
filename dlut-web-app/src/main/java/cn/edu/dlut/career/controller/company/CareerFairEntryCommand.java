package cn.edu.dlut.career.controller.company;

import cn.edu.dlut.career.domain.company.CarreFairEntry;
import cn.edu.dlut.career.dto.company.CarreFairEntryDTO;
import cn.edu.dlut.career.service.company.CareerFairEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * 双选会，招聘会预约申请 Controller
 * 增 删 改 查
 * Created by HealerJean on 2017/4/7.
 */

@Controller
@RequestMapping("/company")
@Transactional
public class CareerFairEntryCommand {
    private  Logger logger = LoggerFactory.getLogger(CareerFairEntryCommand.class);
    @Autowired
    private  CareerFairEntryService careerFairEntryService;


    /**
     * 添加 双选会，招聘会预约申请，进行保存
     */
    @PostMapping(value = "/orderCareerFair.index")
    @ResponseBody
    public CarreFairEntry orderSaveCareerFair(@RequestBody CarreFairEntry carreFairEntry){
        try {
            return careerFairEntryService.saveCarreFairEntry(carreFairEntry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 通过id删除
     * 双选会，招聘会预约申请
     */
    @DeleteMapping("/delCareerFair/{id}")
    @ResponseBody
    public String deleteCareerFair(@PathVariable UUID id){
        String result="";
        try {
            return careerFairEntryService.deleteCarreFairEntry(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 修改
     * 双选会，招聘会预约申请
     */
    @PutMapping(value = "/updateCareerFair.index")
    @ResponseBody
    public CarreFairEntry updateCareerFair(@RequestBody CarreFairEntry carreFairEntry){
        try {
            return careerFairEntryService.saveCarreFairEntry(carreFairEntry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 通过id查找
     * 双选会，招聘会预约申请
     */
    @GetMapping("/findCareerFair/{id}")
    @ResponseBody
    public CarreFairEntry findCareerFair(@PathVariable UUID id){
        try {
            return careerFairEntryService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
