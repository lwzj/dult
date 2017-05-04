package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.JobApplicationRecord;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */
public interface JobApplicationRecordService {
  JobApplicationRecord save(JobApplicationRecord jobApplicationRecord);

  void delete(UUID id);

  JobApplicationRecord update(JobApplicationRecord jobApplicationRecord);

  List<JobApplicationRecord> findAll();

  JobApplicationRecord finOne(UUID id);
}
