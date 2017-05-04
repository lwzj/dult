package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.dto.school.AcademicIndexDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by 史念念 on 2017/4/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademicIndexServiceImplTest {
    @Autowired
    private AcademicIndexServiceImpl aisi;

    @Test
    public void test(){
       AcademicIndexDTO ad =  aisi.findAll("04","2017");
    }

}
