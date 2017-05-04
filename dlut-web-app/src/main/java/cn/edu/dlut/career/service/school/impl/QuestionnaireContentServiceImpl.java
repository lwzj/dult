package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.domain.school.QuestionnaireContent;
import cn.edu.dlut.career.repository.school.QuestionnaireContentRepository;
import cn.edu.dlut.career.service.school.QuestionnaireContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional
public class QuestionnaireContentServiceImpl implements QuestionnaireContentService{
    @Autowired
    QuestionnaireContentRepository questionnaireContentRepository;
    @Override
    public QuestionnaireContent saveQuestionnaireContent(QuestionnaireContent questionnaireContent) {
        return questionnaireContentRepository.save(questionnaireContent);
    }

    @Override
    public QuestionnaireContent findById(UUID id) {
        return questionnaireContentRepository.findById(id);
    }

    @Override
    public QuestionnaireContent updateQuestionnaireContent(QuestionnaireContent questionnaireContent) {
        return questionnaireContentRepository.save(questionnaireContent);
    }

    @Override
    public String deleteQuestionnaireContent(UUID id) {
        try {
            questionnaireContentRepository.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<QuestionnaireContent> findAllQuestionnaireContent() {
        return questionnaireContentRepository.findAll();
    }
}
