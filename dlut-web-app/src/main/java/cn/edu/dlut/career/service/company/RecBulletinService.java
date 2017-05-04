package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.RecBulletin;
import cn.edu.dlut.career.dto.company.RecBulletinApplicationDTO;
import cn.edu.dlut.career.dto.company.RecBulletinDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 服务层接口
 */
public interface RecBulletinService {

    /**
     * 查询所有 招聘简章
     * @return
     */
    List<RecBulletin> findALL();

    /**
     * 根据id查找招聘简章
     * @param id
     * @return
     */
    RecBulletin findById(UUID id);

    /**
     * 根据公司ID查找 招聘简章
     * @return
     */
    List<RecBulletin> findByRecId(UUID recId);

    /**
     * 根据标题关键字查找招聘简章
     * @param keywords
     * @return
     */
    List<RecBulletin> findByKeywords(String keywords);

    /**
     * 修改审核信息
     * @param id 招聘简章id
     * @param auditStatus 审核状态
     * @param auditTime 审核时间
     * @param auditor 审核人
     * @param nopassReason 审核不通过原因
     * @return
     */
    String updateAudit(UUID id, String auditStatus, LocalDateTime auditTime, String auditor, String nopassReason);

    /**
     * 添加 招聘简章
     * @param recBulletinDTO
     * @return
     */
    RecBulletin saveRecBulletin(RecBulletinDTO recBulletinDTO);

    /**
     * 删除招聘简章
     * @param id
     * @return
     */
    String deleteRecBulletin(UUID id);

    /**
     * 按条件查询招聘简章
     * @param title
     * @param auditStatus
     * @param startTime
     * @param endTime
     * @return
     */
    List<RecBulletin> findByCondition(String title, String auditStatus, LocalDateTime startTime, LocalDateTime endTime);
}
