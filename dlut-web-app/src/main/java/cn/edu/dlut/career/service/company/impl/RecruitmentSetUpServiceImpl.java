package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.RecruitmentSetUp;
import cn.edu.dlut.career.repository.company.RecruitmentSetUpRepositry;
import cn.edu.dlut.career.service.company.RecruitmentSetUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/27.
 */
@Service
@Transactional
public class RecruitmentSetUpServiceImpl implements RecruitmentSetUpService {
  @Autowired
  private RecruitmentSetUpRepositry recruitmentSetUpRepositry;


  @Override
  public void save(RecruitmentSetUp recruitmentSetUp) {
    recruitmentSetUpRepositry.save(recruitmentSetUp);
  }

  @Override
  public void delete(UUID id) {
    recruitmentSetUpRepositry.delete(id);
  }

  @Override
  public List<RecruitmentSetUp> findAll() {
    return recruitmentSetUpRepositry.findAll();
  }

  @Override
  public RecruitmentSetUp finOne(UUID id) {
    return recruitmentSetUpRepositry.findOne(id);
  }


}
