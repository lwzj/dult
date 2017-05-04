package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.MattersNeedingAttention;
import cn.edu.dlut.career.repository.company.MattersNeedingAttentionRepository;
import cn.edu.dlut.career.service.company.MattersNeedingAttentionService;
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
public class MattersNeedingAttentionServiceImpl implements MattersNeedingAttentionService {
  @Autowired
  private MattersNeedingAttentionRepository mattersNeedingAttentionRepository;

  @Override
  public void save(MattersNeedingAttention mattersNeedingAttention) {
    mattersNeedingAttentionRepository.save(mattersNeedingAttention);
  }

  @Override
  public void delete(UUID id) {
    mattersNeedingAttentionRepository.delete(id);
  }

  @Override
  public List<MattersNeedingAttention> findAll() {
    return mattersNeedingAttentionRepository.findAll();
  }

  @Override
  public MattersNeedingAttention finOne(UUID id) {
    return mattersNeedingAttentionRepository.findOne(id);
  }


}
