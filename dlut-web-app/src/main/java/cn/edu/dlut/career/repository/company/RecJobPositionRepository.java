package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.RecJobPosition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/23.
 *
 * 招聘职位持久层
 */
@Transactional
public interface RecJobPositionRepository extends CrudRepository<RecJobPosition,UUID> {

  /**
   * 查询全部招聘职位信息
   * @return
   */
  List<RecJobPosition> findAll();

    /**
     * 批量保存职位
     * @return
     */
  List<RecJobPosition> save(List<RecJobPosition> recJobPositions);

  /**
   * 根据编号查找公司招聘信息
   * @param id
   * @return
   */
  RecJobPosition findById(UUID id);

  /**
   * 根据公司编号查找招聘信息
   * @param recId
   * @return
   */
  List<RecJobPosition> findByRecId(UUID recId);

  /**
   * 修改审核状态,审核人,审核时间,未通过原因
   * @param auditStatus
   * @return
   */
  @Modifying
  @Query("update RecJobPosition  set auditStatus=?2 ,auditor=?3 ," +
      "auditTime=?4,nopassReason=?5 where id = ?1")
  int updateAudit(UUID id,
                  String auditStatus,
                  String auditor,
                  LocalDateTime auditTime,
                  String nopassReason);


  @Query(value = "select * from rec_job_position as rj,rec_bulletin as rb,rec_bulletin_job as rbj where (rj.name like %?1% or ?1 = null) AND (rj.id in (select job_id from rec_bulletin_job where bulletin_id = (select id from rec_bulletin where title like %?2% or ?2 = null))) AND" +
      "(rj.category = ?3) AND (rj.audit_status = ?4)",nativeQuery = true)
  List<RecJobPosition> findByCondition(String name, String title, String category, String auditStatus);

    /**
     * 根据招聘简章id查找招聘职位数量
     * @param bulletinId
     * @return
     */
  @Query(value = "select count(*) from rec_job_position jp left join rec_bulletin_job bj on bj.job_id=jp.id where bj.bulletin_id=?1",nativeQuery = true)
  int findByBulId(UUID bulletinId);
}
