package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.RecOffer;
import cn.edu.dlut.career.dto.company.RecOfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/4/13.
 */
public interface RecOfferService {
    List<RecOfferDTO> findByStuNo(String stuNo);

    //根据学号查找 三方协议表
    RecOffer findRecOfferByStuNo(String stuNo);

    /**
     * 学生查询自己的offer
     * @param StuNo
     * @param stuReceivingStatus
     * @return
     */
    List<RecOfferDTO> findByStuNos(String StuNo,String stuReceivingStatus);

    /**
     * 老师通过条件筛选符合的offer,并分页展示
     * @param stuNo
     * @param realName
     * @param departmentId
     * @param eduDegree
     * @param endDate
     * @param stuReceivingStatus
     * @param majorCode
     * @param recName
     * @param pageable
     * @return
     */
    Page<RecOffer> findRecOfferDynamic(String stuNo, String realName, String departmentId, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable);

    /**
     * 根据ID查询简历详情
     * @param id
     * @return
     */
    RecOffer findOne(UUID id);

    /**
     * 保存、更新offer信息
     * @param recOffer
     */
    void save(RecOffer recOffer);

    Page<RecOffer> findRecOfferDynamicBySchool(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable);

    Page<RecOffer> findRecOfferDynamicByAcademy(String stuNo, String realName, String department, String eduDegree, String endDate, String stuReceivingStatus, String majorCode, String recName, Pageable pageable);

    //审核学生offer
    void update(UUID uuid,String auditStatus,String stuReceivingStatus);


    /**
     * 学生查看有无已经签约或者接收的offer
     * @param stuNo
     * @return
     */
    List<RecOfferDTO> findByStuNos(String stuNo);

    RecOfferDTO findByStuId(UUID id);
}
