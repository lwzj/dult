package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.RecBulletin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/4/7.
 *
 * 招聘简章 持久层
 */
@Transactional
public interface RecBulletinRepository extends CrudRepository<RecBulletin,UUID> {

    /**
     * 查询所有招聘简章
     * @return
     */
    List<RecBulletin> findAll();

    /**
     * 根据id查找招聘简章
     * @return
     */
    RecBulletin findById(UUID id);

    /**
     * 根据公司id查找招聘简章
     * @param recId
     * @return
     */
    List<RecBulletin> findByRecId(UUID recId);

    /**
     * 根据标题关键字查找招聘简章
     * @param keywords
     * @return
     */
    @Query("from RecBulletin where title like %?1%")
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
    @Modifying
    @Query("update RecBulletin set auditStatus=?2,auditTime=?3,auditor=?4,nopassReason=?5 where id=?1")
    int updateAudit(UUID id,
                    String auditStatus,
                    LocalDateTime auditTime,
                    String auditor,
                    String nopassReason);


    @Query("FROM  RecBulletin as r where (r.title like %?1% or ?1 = null) AND (r.auditStatus=?2) AND (r.startTime<=?3) AND (r.endTime>=?4)")
    List<RecBulletin> findByCondition(String title, String auditStatus, LocalDateTime startTime, LocalDateTime endTime);
}
