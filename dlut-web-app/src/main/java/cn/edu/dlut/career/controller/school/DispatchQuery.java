package cn.edu.dlut.career.controller.school;

import cn.edu.dlut.career.domain.student.GraduateDestination;
import cn.edu.dlut.career.dto.school.DispatchDTO;
import cn.edu.dlut.career.util.PubCodeUtil;
import cn.edu.dlut.career.service.student.GraduateDestinationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 派遣信息
 * Created by HealerJean on 2017/4/19.
 */
@Controller
public class DispatchQuery {
    @Autowired
    private GraduateDestinationService graduateDestinationService;

    Logger logger = LoggerFactory.getLogger(DispatchQuery.class);

    /**
     *  张宇晋
     *  tea_employmentLook.html y
     *  用于显示下拉列表
     * @param map
     */
    @ModelAttribute
    public void getMap(Map<String, Object> map){
        map.put("allMajor",   PubCodeUtil.getDictMap("major"));
        map.put("allEduDegree", PubCodeUtil.getDictMap("eduDegree"));
        map.put("allEduMode", PubCodeUtil.getDictMap("eduMode"));
        map.put("allAuditStatus", PubCodeUtil.getDictMap("auditStatus"));
    }


    @GetMapping("/teacher/teaEmploymentLook.html")
    public String dispatchIndex() {
        return "teacherHTML/tea_employmentLook";
    }



    /**
     * 派遣查询分页
     *
     * @param stuNo
     * @param stuName
     * @param majorCode
     * @param eduDegree
     * @param eduMode
     * @param endStatus
     * @param pageable
     * @return
     */
    @ResponseBody
    @GetMapping("/teacher/dispatchLookPage")
    public  Page<GraduateDestination> dispatchLookPage(String stuNo, String stuName, String majorCode, String eduDegree, String eduMode, String endStatus, Pageable pageable){
        Page<GraduateDestination> dispatchDTOByStuIdPage = graduateDestinationService.findDispatchDTOByStuIdPage(stuNo, stuName, majorCode, eduDegree, eduMode, endStatus, pageable);
        if (dispatchDTOByStuIdPage != null) {
            return dispatchDTOByStuIdPage;
        } else {
            return null;
        }
    }
}
