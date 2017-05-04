package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.RecruitmentSetUp;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/27.
 */
public interface RecruitmentSetUpService {
  void save(RecruitmentSetUp recruitmentSetUp);

  void delete(UUID id);

  List<RecruitmentSetUp> findAll();

  RecruitmentSetUp finOne(UUID id);

}
