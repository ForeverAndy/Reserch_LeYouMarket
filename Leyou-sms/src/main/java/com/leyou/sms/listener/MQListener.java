package com.leyou.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.leyou.config.SmsProperties;
import com.leyou.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * sms消息队列消费者
 */
@Component
@Slf4j
@EnableConfigurationProperties(SmsProperties.class)   //读取配置文件
public class MQListener {
    //自动注入一个smsUtils服务
    @Autowired
    private SmsUtils smsUtils;

    //自动注入一个我们写好的属性
    @Autowired
    private SmsProperties prop;

    /**
     * 使用RabbitListener注解声明这是一个队列消费者
     * 自动绑定到ly.sms.queue队列
     * 自动绑定到ly.sms.exchange交换机
     * 使用sms.verify.code表示topic类型的交换机的秘钥（交换机会按照key去分配队列）
     * type = ExchangeTypes.TOPIC 表示使用的是topic类型的交换机
     * @param msg  入参，是map<string,string>类型的
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ly.sms.queue", durable = "true"),
            exchange = @Exchange(value = "ly.sms.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC),       //topic类型的交换机
            key = {"sms.verify.code"}))
    public void listenerSendMessage(Map<String, String> msg){
        //校验数据
        if (msg == null || msg.isEmpty()) {
            return;
        }
        //得到解析数据
        String phone = msg.get("phone");
        String code = msg.get("code");
        //还是校验数据
        if (phone == null || code == null) {
            return;
        }
        //生成一个SendSmsResponse对象，他会告诉是否发送完成
//        SendSmsResponse resp = null;
        try {
            //调取服务，传入参数  后两个为sms服务必备参数
            smsUtils.sendSms(phone, code, prop.getSignName(), prop.getVerifyCodeTemplate());
            log.info("没有发送，只是保存到redis，模拟成功");
//            if (!"OK".equalsIgnoreCase(resp.getMessage())) {
//                throw new RuntimeException("发送短信失败");
//            }
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
