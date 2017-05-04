package cn.edu.dlut.career.service.student.impl;

import cn.edu.dlut.career.domain.student.StudentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentInfoServiceImplTest {

    @Autowired
    private StudentInfoServiceImpl s;

    @Test
    public void aa(){
        StudentInfo stu = s.findByStuNo("1362140134");

        String a = "";
    }

}
