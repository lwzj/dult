package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.RecCareerEvent;
import cn.edu.dlut.career.dto.company.RecCareerEventDTO;
import cn.edu.dlut.career.repository.company.RecCareerEventRepository;
import cn.edu.dlut.career.service.company.RecCareerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 史念念 on 2017/3/24.
 * <p>
 * 专场招聘会预约服务层实现类
 */
@Service
@Transactional
public class RecCareerEventServiceImpl implements RecCareerEventService {
    @Autowired
    private RecCareerEventRepository recCareerEventRepository;


    /**
     * 添加 专场招聘会预约信息
     * @return
     */
    @Override
    public RecCareerEvent saveRecCareerEvent(RecCareerEventDTO recCareerEventDTO) {

        try {
            LocalDateTime fairStartTime = LocalDateTime.parse(recCareerEventDTO.getFairStartTime());
            LocalDateTime fairEndTime = LocalDateTime.parse(recCareerEventDTO.getFairEndTime());
            RecCareerEvent recCareerEvent  = new RecCareerEvent();
            recCareerEvent.setFairName(recCareerEventDTO.getFairName());
            recCareerEvent.setFairStartTime(fairStartTime);
            recCareerEvent.setFairEndTime(fairEndTime);
            recCareerEvent.setRecName(recCareerEventDTO.getRecName());
            recCareerEvent.setRecAddress(recCareerEventDTO.getRecAddress());
            recCareerEvent.setContacts(recCareerEventDTO.getContacts());
            recCareerEvent.setConTel(recCareerEventDTO.getConTel());
            recCareerEvent.setConEmail(recCareerEventDTO.getConEmail());
            recCareerEvent.setAreaRequire(recCareerEventDTO.getAreaRequire());
            recCareerEvent.setAreaSize(recCareerEventDTO.getAreaSize());
            recCareerEvent.setAreaNum(recCareerEventDTO.getAreaNum());
            recCareerEvent.setAreaUsing(recCareerEventDTO.getAreaUsing());
            recCareerEvent.setAreaAddress(recCareerEventDTO.getAreaAddress());
            recCareerEvent.setAreaCost(recCareerEventDTO.getAreaCost());
            recCareerEvent.setReceiver(recCareerEventDTO.getReceiver());
            recCareerEvent.setReceiverTel(recCareerEventDTO.getReceiverTel());
            recCareerEvent.setPayType(recCareerEventDTO.getPayType());
            recCareerEvent.setRemarks(recCareerEventDTO.getRemarks());

           return recCareerEventRepository.save(recCareerEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RecCareerEvent updateRecCareerEvent(RecCareerEventDTO recCareerEventDTO) {
        try {
            LocalDateTime fairStartTime = LocalDateTime.parse(recCareerEventDTO.getFairStartTime());
            LocalDateTime fairEndTime = LocalDateTime.parse(recCareerEventDTO.getFairEndTime());
            RecCareerEvent recCareerEvent  = new RecCareerEvent();
            recCareerEvent.setId(recCareerEventDTO.getId());
            recCareerEvent.setFairName(recCareerEventDTO.getFairName());
          //  recCareerEvent.setApplicationTime(dateFormatUtil.getLocalDateTime(recCareerEventDTO.getApplicationTime(),"yyyy-MM-dd"));
            recCareerEvent.setFairStartTime(fairStartTime);
            recCareerEvent.setFairEndTime(fairEndTime);
            recCareerEvent.setRecName(recCareerEventDTO.getRecName());
            recCareerEvent.setRecAddress(recCareerEventDTO.getRecAddress());
            recCareerEvent.setContacts(recCareerEventDTO.getContacts());
            recCareerEvent.setConTel(recCareerEventDTO.getConTel());
            recCareerEvent.setConEmail(recCareerEventDTO.getConEmail());
            recCareerEvent.setAreaRequire(recCareerEventDTO.getAreaRequire());
            recCareerEvent.setAreaSize(recCareerEventDTO.getAreaSize());
            recCareerEvent.setAreaNum(recCareerEventDTO.getAreaNum());
            recCareerEvent.setAreaUsing(recCareerEventDTO.getAreaUsing());
            recCareerEvent.setAreaAddress(recCareerEventDTO.getAreaAddress());
            recCareerEvent.setAreaCost(recCareerEventDTO.getAreaCost());
            recCareerEvent.setReceiver(recCareerEventDTO.getReceiver());
            recCareerEvent.setReceiverTel(recCareerEventDTO.getReceiverTel());
            recCareerEvent.setPayType(recCareerEventDTO.getPayType());
            recCareerEvent.setRemarks(recCareerEventDTO.getRemarks());

            return recCareerEventRepository.save(recCareerEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    }

    /**
     * 查询所有 专场招聘会预约信息
     *
     * @return
     */
    @Override
    public List<RecCareerEvent> findAll() {
        List<RecCareerEvent> recCareerEvents = recCareerEventRepository.findAll();

        return recCareerEvents;
    }

    /**
     * 根据Id查找 专场招聘会预约信息
     *
     * @param id
     * @return
     */
    @Override
    public RecCareerEvent findById(UUID id) {
        RecCareerEvent recCareerEvent = recCareerEventRepository.findById(id);

        return recCareerEvent;
    }

    /**
     * 根据企业id 查找专场招聘会预约信息
     *
     * @param recId
     * @return
     */
    @Override
    public List<RecCareerEvent> findByRecId(UUID recId) {
        List<RecCareerEvent> recCareerEvents = recCareerEventRepository.findByRecId(recId);

        return recCareerEvents;
    }

    /**
     * 修改审核及回执信息
     *
     * @param id
     * @param auditStatus  审核状态
     * @param auditSuggest 审核意见
     * @param areaAddress  场地地址
     * @param areaCost     场地费用
     * @param receiver     接待人
     * @param receiverTel  接待人联系方式
     * @param auditTime    审核时间
     * @param auditor      审核人
     * @param fairStartTime    开始时间
     * @param fairEndTime      结束时间
     * @return
     */
    @Override
    public String updateAudit(UUID id, String auditStatus, String auditSuggest, String areaAddress, Float areaCost, String receiver, String receiverTel, LocalDateTime auditTime, String auditor, LocalDateTime fairStartTime, LocalDateTime fairEndTime) {
        try {
            int i = recCareerEventRepository.updateAudit(id, auditStatus, auditSuggest, areaAddress, areaCost, receiver, receiverTel, auditTime, auditor, fairStartTime, fairEndTime);
            return i > 0 ? "ok" : "fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 删除 专场招聘会预约信息
     *
     * @param id
     * @return
     */
    @Override
    public String deleteById(UUID id) {
        try {
            recCareerEventRepository.delete(id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    /**
     *  通过id查找审核状态
     */
    @Override
    public String auditStatusFindByid(UUID id) {
        try {
           return recCareerEventRepository.auditStatusFindByid(id);
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public List<RecCareerEvent> queryRecByCondition(LocalDateTime applicationTime, LocalDateTime applicationEndTime, LocalDateTime fairStartTime, LocalDateTime fairEndTime, String auditStatus, String fairName) {
        try {
            return recCareerEventRepository.queryRecByCondition(applicationTime,applicationEndTime,fairStartTime,fairEndTime,auditStatus,fairName);
        }catch (Exception e){
        }
        return null;
    }
}
