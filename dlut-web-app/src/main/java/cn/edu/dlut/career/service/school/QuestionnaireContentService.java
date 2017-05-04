package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 问卷问题内容 接口层
 * Created by HealerJean on 2017/4/6.
 */

public interface QuestionnaireContentService {
    //保存，添加
    QuestionnaireContent saveQuestionnaireContent(QuestionnaireContent questionnaireContent) ;

    //根据id查询
    QuestionnaireContent findById(UUID id);

    // 更新
    QuestionnaireContent updateQuestionnaireContent(QuestionnaireContent questionnaireContent);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteQuestionnaireContent(UUID id);

    //查询所有的数据
    List<QuestionnaireContent> findAllQuestionnaireContent();
}
