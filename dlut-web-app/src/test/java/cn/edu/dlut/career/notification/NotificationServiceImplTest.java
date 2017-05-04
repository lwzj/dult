package cn.edu.dlut.career.notification;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author wangyj.
 * @Date 2017/3/28  17:18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceImplTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void addNotification() throws Exception {

        Notifcations notifcations = new Notifcations();
        notifcations.setSenderId("1111");
        notifcations.setSenderType("sys");
        notifcations.setToId("123");
        notifcations.setToType("rec");
        notifcations.setAction("audit");
        Map<String,String > map = new HashMap<>();
        map.put("recName","迷你校");
        map.put("jobName","java工程师");
        notifcations.setVar(map);
        notificationService.addNotification(notifcations);
    }

}
