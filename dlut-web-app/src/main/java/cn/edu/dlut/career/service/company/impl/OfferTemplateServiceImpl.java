package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.OfferTemplate;
import cn.edu.dlut.career.repository.company.OfferTemplateRepositry;
import cn.edu.dlut.career.service.company.OfferTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/28.
 */
@Service
@Transactional
public class OfferTemplateServiceImpl implements OfferTemplateService {

    @Autowired
    private OfferTemplateRepositry offerTemplateRepositry;

    @Override
    public void save(OfferTemplate offerTemplate) {
        offerTemplateRepositry.save(offerTemplate);
    }

    @Override
    public void delete(UUID id) {
        offerTemplateRepositry.delete(id);
    }

    @Override
    public List<OfferTemplate> findAll() {
        return offerTemplateRepositry.findAll();
    }

    @Override
    public OfferTemplate findOne(UUID id) {
        return offerTemplateRepositry.findOne(id);
    }

    @Override
    public void update(OfferTemplate offerTemplate) {
        offerTemplateRepositry.save(offerTemplate);
    }
}
