package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.CarreFairEntry;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface CarrerFairEntryRepository extends CrudRepository<CarreFairEntry,UUID>{
    //查找全部信息
    List<CarreFairEntry> findAll();

    //根据id进行查找
    CarreFairEntry findById(UUID id);

    //更新审核状态 根据 id
    @Modifying
    @Query(value = "update CarreFairEntry set auditStatus = ?2 ,auditTime = ?3,auditor=?4,nopass_reason=?5 where id = ?1")
    int updateCarreFairEntryAudit(UUID id, String auditStatus, LocalDateTime auditTime, String auditor,String nopass_reason);

    /**
     * 根据招聘会id查找招聘会预约申请中通过审核的预约申请
     * @param recJobFairId
     * @return
     */
    @Query("from CarreFairEntry where recJobFairId=?1 and auditStatus='1'")
    List<CarreFairEntry> findByRecJobFairId(UUID recJobFairId);

}
