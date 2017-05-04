package cn.edu.dlut.career.service.school.impl;

import cn.edu.dlut.career.dto.school.SchTeacherStatDTO;
import cn.edu.dlut.career.notification.NotificationService;
import cn.edu.dlut.career.service.school.SchTeacherStatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author wangyj.
 * @Date 2017/4/28  11:33.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SchTeacherStatServiceImplTest {

    @Autowired
    private SchTeacherStatService schTeacherStatService;


    @Test
    public void test(){
         schTeacherStatService.getStat("2017");
    }
}
