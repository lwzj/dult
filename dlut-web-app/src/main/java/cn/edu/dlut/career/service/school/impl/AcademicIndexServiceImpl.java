package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.dto.school.AcademicIndexDTO;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.repository.student.*;
import cn.edu.dlut.career.service.school.AcademicIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 史念念 on 2017/4/28.
 *
 * 院系老师 首页信息 服务层实现类
 */
@Service
public class AcademicIndexServiceImpl implements AcademicIndexService {
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private GraduateDestinationRepostiory graduateDestinationRepostiory;
    @Autowired
    private RecOfferRepository recOfferRepository;
    @Autowired
    private ViolateApplicationRepository violateApplicationRepository;
    @Autowired
    private BlankProtocolRepository blankProtocolRepository;
    @Autowired
    private ReassignApplicationRespository reassignApplicationRespository;

    /**
     * 查询首页所需信息
     * @return
     */
    @Override
    public AcademicIndexDTO findAll(String departmentId,String graduateDate) {
        AcademicIndexDTO aid = new AcademicIndexDTO();
        Map<String,Double> emploRate = new HashMap<>();//院系就业率统计
        Map<String ,Integer> todoList = new HashMap<>();//待处理事项统计

        //查询本院系 共有多少学生
        int stuTotalNum = studentInfoRepository.findStuTotalNum(departmentId,graduateDate);
        //查询本院系 就业学生人数
        int stuEmpNum = graduateDestinationRepostiory.findStuEmpNum(departmentId,graduateDate);
        DecimalFormat    df   = new DecimalFormat("######0.0000");
        double empRate = (double)stuEmpNum/stuTotalNum;
        double er = Double.parseDouble(df.format(empRate));

        emploRate.put("emploRate",er);

        //就业去向统计
        List<Map<String,Object>> emploDestination = graduateDestinationRepostiory.findEmpDesList(departmentId,graduateDate);

        //就业地区分布统计
        List<Map<String,Object>> emploArea = graduateDestinationRepostiory.findEmpAreaList(departmentId,graduateDate);

        //就业单位性质统计
        List<Map<String,Object>> emploNature = graduateDestinationRepostiory.findEmpNatureList(departmentId,graduateDate);

        //就业行业分布统计
        List<Map<String,Object>> emploCategory = graduateDestinationRepostiory.findEmpCategoryList(departmentId,graduateDate);

        //待处理事项
        //签约待审核数量
        int signAuditNum = recOfferRepository.findSignAuditNum(departmentId,graduateDate);
        //违约待审核数量
        int violateAuditNum = violateApplicationRepository.findViolateAuditNum(departmentId,graduateDate);
        //空白协议书申请待审核数量
        int blankAuditNum = blankProtocolRepository.findBlankAuditNum(departmentId,graduateDate);
        todoList.put("signAuditNum",signAuditNum);
        todoList.put("violateAuditNum",violateAuditNum);
        todoList.put("blankAuditNum",blankAuditNum);

        aid.setEmploRate(emploRate);
        aid.setEmploDestination(emploDestination);
        aid.setEmploArea(emploArea);
        aid.setEmploNature(emploNature);
        aid.setEmploCategory(emploCategory);
        aid.setTodoList(todoList);

        return aid;
    }
}
