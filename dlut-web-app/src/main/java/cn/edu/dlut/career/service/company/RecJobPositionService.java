package cn.edu.dlut.career.service.company;




import cn.edu.dlut.career.domain.company.RecJobPosition;
import cn.edu.dlut.career.dto.company.RecJobPositionDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/23.
 *
 * 招聘职位 服务层接口
 */
public interface RecJobPositionService {

  /**
   * 查询全部招聘职位信息
   * @return
   */
  List<RecJobPosition> findAll();

  /**
   * 添加招聘职位信息
   * @param recJobPositionDTOs
   * @return
   */
  List<RecJobPosition> saveRecJobPosition(List<RecJobPositionDTO> recJobPositionDTOs);

  /**
   * 根据编号查找公司的招聘信息
   * @param id
   * @return
   */
  RecJobPosition findById(UUID id);

  /**
   * 根据公司编号查找招聘职位信息
   * @param recId
   * @return
   */
  List<RecJobPosition> findByRecId(UUID recId);

  /**
   * 修改审核状态,审核人,审核时间,未通过原因
   * @param id 主键
   * @param auditStatus 状态
   * @param auditor 审核人
   * @param auditTime 审核时间
   * @param nopassReason  审核失败原因
   * @return
   */
  String updateAudit(UUID id, String auditStatus,
                     String auditor, LocalDateTime auditTime,
                     String nopassReason);

  /**
   * 删除招聘职位信息
   * @return
   */
  String deleteById(UUID id);

    List<RecJobPosition> findByCondition(String name, String title, String category, String auditStatus);
}
