package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.JobFair;
import cn.edu.dlut.career.repository.company.JobFairReposiroty;
import cn.edu.dlut.career.service.company.JobFairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会信息表 服务层
 * Created by HealerJean on 2017/4/6.
 */


@Service
@Transactional
public class JobFairServiceImpl implements JobFairService{
    @Autowired
    JobFairReposiroty jobFairReposiroty;
    @Override
    public JobFair saveJobFair(JobFair jobFair) {
        return jobFairReposiroty.save(jobFair);
    }

    @Override
    public JobFair findById(UUID id) {
        return jobFairReposiroty.findById(id);
    }

    @Override
    public JobFair updateJobFair(JobFair jobFair) {
        return jobFairReposiroty.save(jobFair);
    }

    @Override
    public String deleteJobFair(UUID id) {
        try {
            jobFairReposiroty.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<JobFair> findAllJobFair() {
        return jobFairReposiroty.findAll();
    }
}
