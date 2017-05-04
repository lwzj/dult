package cn.edu.dlut.career.repository.company;

import cn.edu.dlut.career.domain.company.CompanyInfo;
import cn.edu.dlut.career.dto.company.RecLoginDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by wei on 2017/3/23.
 */
public interface CompanyInfoRepository extends CrudRepository<CompanyInfo, UUID> {


    @Override
    List<CompanyInfo> findAll();

    /**
     * 根据email进行查询
     *
     * @param email
     * @return
     */
    @Query("from CompanyInfo c where c.email=?1")
    CompanyInfo findByEmail(String email);


    /**
     * @Description 根据邮箱获取登录信息.
     * @Author wangyj
     * @CreateDate 2017/4/6 11:03
     * @Param
     * @Return
     */
    @Query(value = "select new cn.edu.dlut.career.dto.company.RecLoginDTO (c.id ,c.name,c.email,c.pwd ,c.salt )from CompanyInfo c where c.email=?1")
    RecLoginDTO findLoginInfo(String email);
}
