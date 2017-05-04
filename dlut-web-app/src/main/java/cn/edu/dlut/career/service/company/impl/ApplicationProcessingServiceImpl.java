package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.ApplicationProcessing;
import cn.edu.dlut.career.repository.company.ApplicationProcessingRepository;
import cn.edu.dlut.career.service.company.ApplicationProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/24.
 */
@Service
@Transactional
public class ApplicationProcessingServiceImpl implements ApplicationProcessingService {
  @Autowired
  private ApplicationProcessingRepository applicationProcessingRepository;

  /**
   * 添加offer处理信息
   * @param applicationProcessing
   * @return
   */
  @Override
  public ApplicationProcessing save(ApplicationProcessing applicationProcessing) {
    return applicationProcessingRepository.save(applicationProcessing);
  }

  /**
   * 删除offer处理信息
   * @param id
   */
  @Override
  public void delete(UUID id) {
    applicationProcessingRepository.delete(id);

  }

  /**
   * 更新offer处理信息
   * @param applicationProcessing
   * @return
   */
  @Override
  public ApplicationProcessing update(ApplicationProcessing applicationProcessing) {
    return applicationProcessingRepository.save(applicationProcessing);
  }

  /**
   * 查询所有offer信息
   * @return
   */
  @Override
  public List<ApplicationProcessing> findAll() {
    return applicationProcessingRepository.findAll();
  }

  /**
   * 按照ID查询offer信息
   * @param id
   * @return
   */
  @Override
  public ApplicationProcessing findOne(UUID id) {
    return applicationProcessingRepository.findOne(id);
  }
}
