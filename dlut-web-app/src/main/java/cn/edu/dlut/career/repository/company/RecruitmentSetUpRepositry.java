package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.RecruitmentSetUp;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/27.
 */
public interface RecruitmentSetUpRepositry extends CrudRepository<RecruitmentSetUp,UUID> {
  List<RecruitmentSetUp> findAll();
}
