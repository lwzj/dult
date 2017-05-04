package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.PartakeRepresent;

import java.util.List;
import java.util.UUID;

/**
 * 参与招聘会代表  接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface PartakeRepresentService {
    //保存，添加
    PartakeRepresent savePartakeRepresent(PartakeRepresent partakeRepresent) ;

    //根据id查询
    PartakeRepresent findById(UUID id);

    // 更新
    PartakeRepresent updatePartakeRepresent(PartakeRepresent partakeRepresent);

    //根据id删除 删除成功返回 ok ，否则 null
    String deletePartakeRepresent(UUID id);

    //查询所有的数据
    List<PartakeRepresent> findAllPartakeRepresent();

}
