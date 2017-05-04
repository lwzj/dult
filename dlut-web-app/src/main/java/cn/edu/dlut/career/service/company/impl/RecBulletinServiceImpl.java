package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.dto.company.RecBulletinApplicationDTO;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;
import cn.edu.dlut.career.repository.company.RecBulletinRepository;
import cn.edu.dlut.career.service.company.RecBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 服务层实现类
 */
@Service
public class RecBulletinServiceImpl implements RecBulletinService{

    @Autowired
    private RecBulletinRepository recBulletinRepository;

    /**
     * 查找所有招聘简章
     * @return
     */
    @Override
    public List<RecBulletin> findALL() {
        List<RecBulletin> recBulletins = recBulletinRepository.findAll();
        return recBulletins;
    }

    /**
     * 根据id查找招聘简章
     * @param id
     * @return
     */
    @Override
    public RecBulletin findById(UUID id) {
        RecBulletin recBulletin = recBulletinRepository.findById(id);
        return recBulletin;
    }

    /**
     * 根据公司id查找招聘简章
     * @param recId
     * @return
     */
    @Override
    public List<RecBulletin> findByRecId(UUID recId) {
        List<RecBulletin> recBulletins = recBulletinRepository.findByRecId(recId);

        return recBulletins;
    }

    /**
     * 根据标题关键字查找招聘简章
     * @param keywords
     * @return
     */
    @Override
    public List<RecBulletin> findByKeywords(String keywords) {
        List<RecBulletin> recBulletins = recBulletinRepository.findByKeywords(keywords);
        return recBulletins;
    }

    /**
     * 修改审核信息
     * @param id 招聘简章id
     * @param auditStatus 审核状态
     * @param auditTime 审核时间
     * @param auditor 审核人
     * @param nopassReason 审核不通过原因
     * @return
     */
    @Override
    public String updateAudit(UUID id, String auditStatus, LocalDateTime auditTime, String auditor, String nopassReason) {
        int result = recBulletinRepository.updateAudit(id,auditStatus,auditTime,auditor,nopassReason);

        return result>0?"ok":"fail";
    }

    /**
     * 添加招聘简章
     * @param recBulletinDTO
     * @return
     */
    @Override
    public RecBulletin saveRecBulletin(RecBulletinDTO recBulletinDTO) {

        RecBulletin recBulletin = new RecBulletin();
        RecBulletin rec = new RecBulletin();
        LocalDate startTime = LocalDate.parse(recBulletinDTO.getStartTime());
        LocalDate endTime = LocalDate.parse(recBulletinDTO.getEndTime());
        recBulletin.setRecId(recBulletinDTO.getRecId());
        recBulletin.setTitle(recBulletinDTO.getTitle());
        recBulletin.setContent(recBulletinDTO.getContent());
        recBulletin.setStartTime(startTime);
        recBulletin.setEndTime(endTime);
        recBulletin.setAuditStatus("0");//审核状态默认为0 待审核
        recBulletin.setOnlineStatus("0");//上下线状态默认0为下线

        rec = recBulletinRepository.save(recBulletin);
        return rec;

    }

    /**
     * 删除 招聘简章
     * @param id
     * @return
     */
    @Override
    public String deleteRecBulletin(UUID id) {
        try {
            recBulletinRepository.delete(id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 按条件查询招聘简章
     * @param title
     * @param auditStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<RecBulletin> findByCondition(String title, String auditStatus, LocalDateTime startTime, LocalDateTime endTime) {
        return recBulletinRepository.findByCondition(title,auditStatus,startTime,endTime);
    }
}
