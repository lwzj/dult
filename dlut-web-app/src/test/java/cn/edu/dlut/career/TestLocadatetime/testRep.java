package cn.edu.dlut.career.TestLocadatetime;

import cn.edu.dlut.career.domain.company.JobFair;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
@Repository
public interface testRep extends CrudRepository<JobFair ,String> {

    @Query(value="from JobFair as jf where (jf.type =?1 or ?1=null) and (jf.fairStartTime>=?2 or cast(?2 as timestamp)=null) and (jf.fairEndTime <=?3 or cast(?3 as timestamp)=null)")
    List<JobFair> findByBBB(String type, LocalDateTime fairStartTime, LocalDateTime fairEndTime);

    @Query(value="from JobFair as jf where (jf.type =?1 or ?1=null) and jf.fairStartTime>=?2 and jf.fairEndTime <=?3")
    List<JobFair> findByAAA(String type, LocalDateTime fairStartTime, LocalDateTime fairEndTime);
}
