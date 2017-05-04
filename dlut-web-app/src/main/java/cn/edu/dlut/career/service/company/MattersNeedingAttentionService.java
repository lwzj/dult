package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.MattersNeedingAttention;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/27.
 */
public interface MattersNeedingAttentionService {
  void save(MattersNeedingAttention mattersNeedingAttention);

  void delete(UUID id);

  List<MattersNeedingAttention> findAll();

  MattersNeedingAttention finOne(UUID id);

}
