package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.JobFair;
import cn.edu.dlut.career.dto.company.JobFairDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息表 数据操作层
 * Created by HealerJean on 2017/4/6.
 */
@Repository
public interface JobFairReposiroty extends CrudRepository<JobFair ,UUID>{
    //查找全部信息
    List<JobFair> findAll();

    //根据id进行查找
    JobFair findById(UUID id);

    //根据招聘会类型、时间查询招聘会信息
     @Query("select new cn.edu.dlut.career.dto.company.JobFairDTO ( " +
        "jf.id,count(cfe.recJobFairId),count(rbj.bulletinId), " +
        "jf.name,jf.description,jf.type,jf.fairStartTime,jf.fairEndTime, " +
        "jf.startTime,jf.endTime,jf.location,jf.needCost, " +
        "jf.invitation,jf.checkCode,jf.creator,jf.createTime," +
        "jf.updateTime,jf.updatePerson ) " +
        "from JobFair jf left join CarreFairEntry cfe on cfe.recJobFairId=jf.id " +
        "left join RecBulletin rb on rb.id=cfe.recBulletinId " +
        "left join RecBulletinJob rbj on rbj.bulletinId=rb.id " +
        "where (jf.type =?1 or ?1=null) and " +
        "(jf.fairStartTime>=?2 or cast(?2 as timestamp)=null) and " +
        "(jf.fairEndTime <=?3 or cast(?3 as timestamp)=null) and " +
         "cfe.auditStatus='1' " +
         "group by jf.id")
     Page<JobFairDTO> findByTypeAndFairEndTime(String type, LocalDateTime fairStartTime, LocalDateTime fairEndTime, Pageable pageable);
}
