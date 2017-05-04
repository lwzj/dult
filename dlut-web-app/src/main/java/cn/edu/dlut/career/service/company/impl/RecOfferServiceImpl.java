package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.dto.company.RecOfferDTO;
import cn.edu.dlut.career.repository.company.RecOfferRepository;
import cn.edu.dlut.career.service.company.RecOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
@Service
public class RecOfferServiceImpl implements RecOfferService {

    @Autowired
    private RecOfferRepository recOfferRepository;

    /**
     * 学生查询自己的offer
     * @param stuNo
     * @param stuReceivingStatus
     * @return
     */
    @Override
    public List<RecOfferDTO> findByStuNos(String stuNo,String stuReceivingStatus) {
        return recOfferRepository.findByStuNos(stuNo,stuReceivingStatus);
    }

//    /**
//     * 老师通过条件筛选符合的offer,并分页展示
//     * @param stuNo
//     * @param realName
//     * @param department
//     * @param eduDegree
//     * @param endDate
//     * @param stuReceivingStatus
//     * @param majorCode
//     * @param recName
//     * @param pageable
//     * @return
//     */
//    @Override
//    public Page<RecOffer> findRecOfferDynamic(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable) {
//        if(stuNo.equals("")){
//            stuNo = null;
//        }
//        if(realName.equals("")){
//            realName = null;
//        }
//        if(department.equals("")){
//            department = null;
//        }
//        if(eduDegree.equals("")){
//            eduDegree = null;
//        }
//        if(stuReceivingStatus.equals("")){
//            stuReceivingStatus = null;
//        }
//        if(majorCode.equals("")){
//            majorCode = null;
//        }
//        if(recName.equals("")){
//            recName = null;
//        }
//
//        return recOfferRepository.findRecOfferDynamic(stuNo,realName,department,eduDegree,endDate,stuReceivingStatus,majorCode,recName,pageable);
//    }



    /**
     * 根据ID查询简历详情
     * @param id
     * @return
     */
    @Override
    public RecOffer findOne(UUID id) {
        return recOfferRepository.findOne(id);
    }

    /**
     * 保存、更新offer信息
     * @param recOffer
     */
    @Override
    public void save(RecOffer recOffer) {
        recOfferRepository.save(recOffer);
    }

    /**
     * 校级老师通过条件筛选符合的offer,并分页展示
     * @param stuNo
     * @param realName
     * @param department
     * @param eduDegree
     * @param endDate
     * @param stuReceivingStatus
     * @param majorCode
     * @param recName
     * @param pageable
     * @return
     */
    @Override
    public Page<RecOffer> findRecOfferDynamicBySchool(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable) {
        if(stuNo.equals("")){
            stuNo = null;
        }
        if(realName.equals("")){
            realName = null;
        }
        if(department.equals("")){
            department = null;
        }
        if(eduDegree.equals("")){
            eduDegree = null;
        }
        if(stuReceivingStatus.equals("")){
            stuReceivingStatus = null;
        }
        if(majorCode.equals("")){
            majorCode = null;
        }
        if(recName.equals("")){
            recName = null;
        }
        return recOfferRepository.findRecOfferDynamicBySchool(stuNo,realName,department,eduDegree,endDate,stuReceivingStatus,majorCode,recName,pageable);
    }

    /**
     * 院级老师通过条件筛选符合的offer,并分页展示
     * @param stuNo
     * @param realName
     * @param department
     * @param eduDegree
     * @param endDate
     * @param stuReceivingStatus
     * @param majorCode
     * @param recName
     * @param pageable
     * @return
     */
    @Override
    public Page<RecOffer> findRecOfferDynamicByAcademy(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable) {
        if(stuNo.equals("")){
            stuNo = null;
        }
        if(realName.equals("")){
            realName = null;
        }
        if(department.equals("")){
            department = null;
        }
        if(eduDegree.equals("")){
            eduDegree = null;
        }
        if(stuReceivingStatus.equals("")){
            stuReceivingStatus = null;
        }
        if(majorCode.equals("")){
            majorCode = null;
        }
        if(recName.equals("")){
            recName = null;
        }
        return recOfferRepository.findRecOfferDynamicByAcademy(stuNo,realName,department,eduDegree,endDate,stuReceivingStatus,majorCode,recName,pageable);
    }

    @Override
    public void update(UUID uuid ,String auditStatus,String stuReceivingStatus) {
        recOfferRepository.update(uuid,auditStatus,stuReceivingStatus);
    }

    /**
     * 学生查看有无已经签约或者接收的offer
     * @param stuNo
     * @return
     */
    @Override
    public List<RecOfferDTO> findByStuNos(String stuNo) {
        return recOfferRepository.findByStuNos(stuNo);
    }

    @Override
    public RecOfferDTO findByStuId(UUID id) {
        return recOfferRepository.findByStuId(id);
    }


    @Override
    public Page<RecOffer> findRecOfferDynamic(String stuNo, String realName, String departmentId, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName,Pageable pageable) {
        if(stuNo.equals("")){
            stuNo = null;
        }
        if(realName.equals("")){
            realName = null;
        }
        if(departmentId.equals("")){
            departmentId = null;
        }
        if(eduDegree.equals("")){
            eduDegree = null;
        }
        if(stuReceivingStatus.equals("")){
            stuReceivingStatus = null;
        }
        if(majorCode.equals("")){
            majorCode = null;
        }
        if(recName.equals("")){
            recName = null;
        }
        if(endDate.equals("")){
            endDate = null;
        }
        return recOfferRepository.findRecOfferDynamic(stuNo,realName,departmentId,eduDegree,endDate,stuReceivingStatus,majorCode,recName,pageable);
    }

    @Override
    public List<RecOfferDTO> findByStuNo(String stuNo) {
        return recOfferRepository.findByStuNo(stuNo);
    }

    @Override
    public RecOffer findRecOfferByStuNo(String stuNo) {
        return recOfferRepository.findRecOfferByStuNo(stuNo);
    }




}
