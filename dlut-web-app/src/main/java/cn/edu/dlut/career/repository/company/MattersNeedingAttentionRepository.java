package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.MattersNeedingAttention;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/27.
 */
public interface MattersNeedingAttentionRepository extends CrudRepository<MattersNeedingAttention,UUID> {
  List<MattersNeedingAttention> findAll();
}
