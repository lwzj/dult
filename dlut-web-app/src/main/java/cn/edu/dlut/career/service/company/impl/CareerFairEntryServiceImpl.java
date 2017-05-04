package cn.edu.dlut.career.service.company.impl;

import cn.edu.dlut.career.domain.company.CarreFairEntry;
import cn.edu.dlut.career.domain.company.InvoiceInfo;
import cn.edu.dlut.career.dto.company.CarreFairEntryDTO;
import cn.edu.dlut.career.repository.company.CarrerFairEntryRepository;
import cn.edu.dlut.career.service.company.CareerFairEntryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 双选会，招聘会预约申请 服务层
 * Created by HealerJean on 2017/4/6.
 */
@Service
@Transactional
public class CareerFairEntryServiceImpl implements CareerFairEntryService {

    @Autowired
    CarrerFairEntryRepository carrerFairEntryRepository;
//    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CarreFairEntry saveCarreFairEntry(CarreFairEntry carreFairEntry) {
       return carrerFairEntryRepository.save(carreFairEntry);
      /*  CarreFairEntry carreFairEntry = new CarreFairEntry();
        try {
            //将InvoiceInfo对象转换为json
            String invoiceInfo = objectMapper.writeValueAsString(carreFairEntryDTO.getInvoiceInfo());
            carreFairEntry.setInvoiceInfo(invoiceInfo);

            carreFairEntry.setId(carreFairEntryDTO.getId());
            carreFairEntry.setRecJobFairId(carreFairEntryDTO.getRecJobFairId());
            carreFairEntry.setRecBulletinId(carreFairEntryDTO.getRecBulletinId());
            carreFairEntry.setRecName(carreFairEntryDTO.getRecName());
            carreFairEntry.setRecAddress(carreFairEntryDTO.getRecAddress());
            carreFairEntry.setUnitFinaPhone(carreFairEntryDTO.getUnitFinaPhone());
            carreFairEntry.setIsSign(carreFairEntryDTO.getIsSign());
            carreFairEntry.setAccoHotel(carreFairEntryDTO.getAccoHotel());
            carreFairEntry.setApplyTime(carreFairEntryDTO.getApplyTime());
            carreFairEntry.setEhxPlace(carreFairEntryDTO.getEhxPlace());
            carreFairEntry.setPayType(carreFairEntryDTO.getPayType());
            carreFairEntry.setPayTime(carreFairEntryDTO.getPayTime());
            carreFairEntry.setReceStatus(carreFairEntryDTO.getReceStatus());
            carreFairEntry.setAuditStatus(carreFairEntryDTO.getAuditStatus());
            carreFairEntry.setAuditor(carreFairEntryDTO.getAuditor());
            carreFairEntry.setAuditTime(carreFairEntryDTO.getAuditTime());
            carreFairEntry.setNopassReason(carreFairEntryDTO.getNopassReason());

*/


    }

    @Override
    public CarreFairEntry findById(UUID id) {
        return carrerFairEntryRepository.findById(id);
    }

    @Override
    public CarreFairEntry updateCarreFairEntry(CarreFairEntry carreFairEntry) {
        return carrerFairEntryRepository.save(carreFairEntry);
    }

    @Override
    public String deleteCarreFairEntry(UUID id) {
        try {
            carrerFairEntryRepository.delete(id);
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CarreFairEntry> findAllCarreFairEntry() {
        return carrerFairEntryRepository.findAll();
    }


    @Override
    public InvoiceInfo getInvoiceInfo(String invoiceInfoJson) {
   /*     JSONObject obj = new JSONObject().fromObject(invoiceInfoJson);//将json字符串转换为json对象

        InvoiceInfo jb = (InvoiceInfo)JSONObject.toBean(obj,InvoiceInfo.class);//将建json对象转换为

        return null;*/
   return  null;
    }

    @Override
    public int updateCarreFairEntryAudit(UUID id, String auditStatus, LocalDateTime auditTime, String auditor,String nopass_reason) {
       try {
          return carrerFairEntryRepository.updateCarreFairEntryAudit(id,auditStatus,auditTime,auditor,nopass_reason);
       }catch (Exception e){
           e.printStackTrace();
       }
        return 0;
    }
}
