package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;
import cn.edu.dlut.career.repository.company.RecJobPositionRepository;
import cn.edu.dlut.career.service.company.RecJobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/23.
 * <p>
 * 招聘职位  服务层实现类
 */
@Service
public class RecJobPositionServiceImpl implements RecJobPositionService {
    @Autowired
    private RecJobPositionRepository recJobPositionRepository;

    /**
     * 查询全部招聘职位信息
     *
     * @return
     */
    @Override
    public List<RecJobPosition> findAll() {
        return recJobPositionRepository.findAll();
    }

    /**
     * 添加招聘职位信息
     *
     * @param recJobPositionDTOs
     * @return
     */
    @Override
    public List<RecJobPosition> saveRecJobPosition(List<RecJobPositionDTO> recJobPositionDTOs) {

            List<RecJobPosition> recJobPositions = new ArrayList<RecJobPosition>();
            for (RecJobPositionDTO recJobPositionDTO:recJobPositionDTOs) {
                RecJobPosition recJobPosition = new RecJobPosition();
                LocalDate startTime = LocalDate.parse(recJobPositionDTO.getStartTime());
                LocalDate endTime = LocalDate.parse(recJobPositionDTO.getEndTime());

                recJobPosition.setRecId(recJobPositionDTO.getRecId());
                recJobPosition.setName(recJobPositionDTO.getName());
                recJobPosition.setType(recJobPositionDTO.getType());
                recJobPosition.setCategory(recJobPositionDTO.getCategory());
                recJobPosition.setDegree(recJobPositionDTO.getDegree());
                recJobPosition.setMajor(recJobPositionDTO.getMajor());
                recJobPosition.setReceiveMode(recJobPositionDTO.getReceiveMode());
                recJobPosition.setRecEmail(recJobPositionDTO.getRecEmail());
                recJobPosition.setCity(recJobPositionDTO.getCity());
                recJobPosition.setAddress(recJobPositionDTO.getAddress());
                recJobPosition.setRecruitmentNum(recJobPositionDTO.getRecruitmentNum());
                recJobPosition.setSalary(recJobPositionDTO.getSalary());
                recJobPosition.setStartTime(startTime);
                recJobPosition.setEndTime(endTime);
                //审核状态默认0待审核
                recJobPosition.setAuditStatus("0");
                //上线下线默认0下线
                recJobPosition.setOnlineStatus("0");

                recJobPositions.add(recJobPosition);
            }



           return recJobPositionRepository.save(recJobPositions);


    }

    /**
     * 根据编号查找招聘职位信息
     *
     * @param id
     * @return
     */
    @Override
    public RecJobPosition findById(UUID id) {
        RecJobPosition recJobPosition = recJobPositionRepository.findById(id);
        return recJobPosition;
    }

    /**
     * 根据公司编号查找招聘职位信息
     *
     * @param recId
     * @return
     */
    @Override
    public List<RecJobPosition> findByRecId(UUID recId) {
        List<RecJobPosition> ls = recJobPositionRepository.findByRecId(recId);
        return ls;
    }

    /**
     * 修改审核状态,审核人,审核时间,未通过原因
     *
     * @param id
     * @param auditStatus
     * @param auditor
     * @param auditTime
     * @param nopassReason
     * @return
     */
    @Override
    public String updateAudit(UUID id, String auditStatus, String auditor, LocalDateTime auditTime, String nopassReason) {
        int result = recJobPositionRepository.updateAudit(id, auditStatus, auditor, auditTime, nopassReason);

        return result > 0 ? "ok" : "fail";
    }

    /**
     * 删除招聘职位信息
     *
     * @param id
     * @return
     */
    @Override
    public String deleteById(UUID id) {
        try {
            recJobPositionRepository.delete(id);
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }

    @Override
    public List<RecJobPosition> findByCondition(String name, String title, String category, String auditStatus) {
        return recJobPositionRepository.findByCondition(name,title,category,auditStatus);
    }
}
