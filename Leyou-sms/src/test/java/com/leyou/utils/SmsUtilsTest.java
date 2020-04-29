package com.leyou.utils;


import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsUtilsTest {

    @Autowired
    private AmqpTemplate amqpTemplate;


//    @Test
//    public void testsend() throws InterruptedException {
//        Map<String,String> msg = new HashMap<>();
//        msg.put("phone","15373168359");
//        msg.put("code","111111");
//
//        amqpTemplate.convertAndSend("ly.sms.exchange","sms.verify.code",msg);
//
//        Thread.sleep(100000L);
//    }




}
