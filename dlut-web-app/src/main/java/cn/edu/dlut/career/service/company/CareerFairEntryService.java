package cn.edu.dlut.career.service.company;

import cn.edu.dlut.career.domain.company.CarreFairEntry;
import cn.edu.dlut.career.domain.company.InvoiceInfo;
import cn.edu.dlut.career.dto.company.CarreFairEntryDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 接口层
 * Created by HealerJean on 2017/4/6.
 */
public interface CareerFairEntryService {
    //保存，添加
    CarreFairEntry saveCarreFairEntry(CarreFairEntry carreFairEntry) ;

    //根据id查询
    CarreFairEntry findById(UUID id);

    // 更新
    CarreFairEntry updateCarreFairEntry(CarreFairEntry carreFairEntry);

    //根据id删除 删除成功返回 ok ，否则 null
    String deleteCarreFairEntry(UUID id);

    //查询所有的数据
    List<CarreFairEntry> findAllCarreFairEntry();

    //获取发票的详细信息
    InvoiceInfo getInvoiceInfo(String invoiceInfoJson);

    //更新预约申请表 审核状态
    // 返回0 则显示错误，返回正数 则正确
    int updateCarreFairEntryAudit(UUID id, String auditStatus, LocalDateTime auditTime, String auditor,String nopass_reason);

}
