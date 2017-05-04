package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.ApplicationProcessing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */
public interface ApplicationProcessingRepository extends CrudRepository<ApplicationProcessing,UUID> {
  List<ApplicationProcessing> findAll();
}
