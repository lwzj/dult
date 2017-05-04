package cn.edu.dlut.career.service.school;

import cn.edu.dlut.career.domain.school.QuestionnaireStatInfo;

import java.util.List;
import java.util.UUID;

/**
 * 问卷统计信息表 接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface QuestionnaireStatInfoService {
    //保存，添加
    QuestionnaireStatInfo saveQuestionnaireStatInfo(QuestionnaireStatInfo questionnaireStatInfo) ;

    //根据id查询
    QuestionnaireStatInfo findById(UUID id);

    // 更新
    QuestionnaireStatInfo updateQuestionnaireStatInfo(QuestionnaireStatInfo questionnaireStatInfo);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteQuestionnaireStatInfo(UUID id);

    //查询所有的数据
    List<QuestionnaireStatInfo> findAllQuestionnaireStatInfo();
}
