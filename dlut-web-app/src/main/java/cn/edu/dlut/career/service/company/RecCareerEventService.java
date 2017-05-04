package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/24.
 *
 * 专场招聘会预约 服务层接口
 */
public interface RecCareerEventService {

  /**
   * 添加 专场招聘会预约信息
   * @return
   */
  RecCareerEvent saveRecCareerEvent(RecCareerEventDTO recSpecialOrderDTO);

    /**
     * 更新 专场招聘会预约信息
     */
    RecCareerEvent updateRecCareerEvent(RecCareerEventDTO recSpecialOrderDTO);

    /**
   * 查询所有 专场招聘会预约信息
   * @return
   */
  List<RecCareerEvent> findAll();

  /**
   * 根据id查找 专场招聘会预约信息
   * @param id
   * @return
   */
  RecCareerEvent findById(UUID id);

  /**
   * 根据公司id查找 专场招聘会预约信息
   * @param recId
   * @return
   */
  List<RecCareerEvent> findByRecId(UUID recId);

    /**
     * 修改审核及回执信息
     * @param id
     * @param auditStatus 审核状态
     * @param auditSuggest 审核意见
     * @param areaAddress 场地地址
     * @param areaCost 场地费用
     * @param receiver 接待人
     * @param receiverTel 接待人联系方式
     * @param auditTime 审核时间
     * @param auditor 审核人
     * @param fairStartTime 开始时间
     * @param fairEndTime 结束时间
     * @return
     */
  String updateAudit(UUID id,String auditStatus,String auditSuggest,String areaAddress,
                     Float areaCost,String receiver,String receiverTel, LocalDateTime auditTime,
                     String auditor,LocalDateTime fairStartTime,
                     LocalDateTime fairEndTime);

  /**
   * 删除 专场招聘会预约信息
   * @param id
   * @return
   */
        String deleteById(UUID id);

    /*
     通过id查找审核后状态
     */
    String auditStatusFindByid(UUID id);

    /**
     *  张宇晋
     *  动态查询 ，通过 申请日期，开始日期，审核状态，以及 招聘会名称
     *  查询专场招聘预约 申请表
     * @return
     */
    List<RecCareerEvent> queryRecByCondition(LocalDateTime applicationTime,LocalDateTime applicationEndTime, LocalDateTime fairStartTime,LocalDateTime fairEndTime, String auditStatus,String fairName);

}
