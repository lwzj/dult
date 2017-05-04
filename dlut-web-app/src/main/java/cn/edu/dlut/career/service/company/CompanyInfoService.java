package cn.edu.dlut.career.service.company;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
public interface CompanyInfoService {
  /**
   * 注册公司
   * @param companyInfo
   */
  void saveCompany(CompanyInfo companyInfo);


  CompanyInfo update(CompanyInfo companyInfo);

  void delete(UUID id);

  List<CompanyInfo> findAll();

  CompanyInfo findOne(UUID id);


  CompanyInfo findByEmail(String email);

  /**
   * @Description 根据邮箱获取登录信息.
   * @Author  wangyj
   * @CreateDate 2017/4/6 11:14
   * @Param
   * @Return
   */
  RecLoginDTO findLoginInfo(String email);

}
