package cn.edu.dlut.career.service.company.impl;


import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;
import cn.edu.dlut.career.repository.company.CompanyInfoRepository;
import cn.edu.dlut.career.service.company.CompanyInfoService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by wei on 2017/3/23.
 */
@Service
@Transactional
public class CompanyInfoServiceImpl implements CompanyInfoService {
  @Autowired
  private CompanyInfoRepository companyInfoRepository;

  /**
   * 注册公司
   * @param companyInfo
   */
  @Override
  public void saveCompany(CompanyInfo companyInfo) {

      //获得随机盐
      SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
      String salt = secureRandomNumberGenerator.nextBytes().toHex();
      //对密码加密后,将加密后的密码和盐存入对象
      String password = companyInfo.getPwd();
      password = new Md5Hash(password, salt).toString();
      companyInfo.setPwd(password);
      companyInfo.setSalt(salt);
      companyInfoRepository.save(companyInfo);

  }


  @Override
  public CompanyInfo update(CompanyInfo companyInfo) {
    return companyInfoRepository.save(companyInfo);
  }

  @Override
  public void delete(UUID id) {
    companyInfoRepository.delete(id);
  }

  @Override
  public List<CompanyInfo> findAll() {
    return companyInfoRepository.findAll();
  }

  @Override
  public CompanyInfo findOne(UUID id) {
    return companyInfoRepository.findOne(id);
  }

  @Override
  public CompanyInfo findByEmail(String email) {
    CompanyInfo companyInfo = companyInfoRepository.findByEmail(email);
    return companyInfo;
  }

  /**
   * @Description 根据邮箱获取登录信息.
   * @Author  wangyj
   * @CreateDate 2017/4/6 11:18
   * @Param
   * @Return
   */
    @Override
    public RecLoginDTO findLoginInfo(String email) {
        return companyInfoRepository.findLoginInfo(email);
    }
}
