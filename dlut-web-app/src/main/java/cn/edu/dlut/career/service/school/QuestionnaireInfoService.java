package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireInfo;

import java.util.List;
import java.util.UUID;

/**
 * 问卷信息表  服务层接口
 * Created by HealerJean on 2017/4/6.
 */
public interface QuestionnaireInfoService {
    //保存，添加
    QuestionnaireInfo saveQuestionnaireInfo(QuestionnaireInfo questionnaireInfo) ;

    //根据id查询
    QuestionnaireInfo findById(UUID id);

    // 更新
    QuestionnaireInfo updateQuestionnaireInfo(QuestionnaireInfo questionnaireInfo);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteQuestionnaireInfo(UUID id);

    //查询所有的数据
    List<QuestionnaireInfo> findAllQuestionnaireInfo();

}
