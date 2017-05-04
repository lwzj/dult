package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.OfferTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/28.
 */
public interface OfferTemplateService {
    void save(OfferTemplate offerTemplate);

    void delete(UUID id);

    List<OfferTemplate> findAll();

    OfferTemplate findOne(UUID id);

    void update(OfferTemplate offerTemplate);
}
