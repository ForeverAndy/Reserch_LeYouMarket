package com.leyou.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.leyou.config.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component //声明自动注入到ioc容器
@EnableConfigurationProperties(SmsProperties.class)   //读取配置文件
@Slf4j  //日志
public class SmsUtils {

    //有一点疑惑，不是已经在listener中注入了吗，这写两次不是重复了吗
    @Autowired
    private SmsProperties prop;

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    //设置前缀，以防止重名
    private  static final String key_prefix = "sms:phone:";

    //设置的失效时间
    private  static final Long sms_min_interval_in_mills = 60000L;

    //redis的模板，这个在maven中导入相关的包之后，就会自动注入
    @Autowired
    private StringRedisTemplate redistemplate;

    //发送消息的主逻辑
    public SendSmsResponse sendSms(String phone, String code, String signName, String template) throws ClientException, ClientException {

        // 按照手机号限流，这里加入前缀
        String key =key_prefix+phone;
        //读取时间
        String lastTime = redistemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(lastTime)){
            Long last = Long.valueOf(lastTime);
            if(System.currentTimeMillis() - last < sms_min_interval_in_mills){
                log.info("两次发送请求小于60秒"+phone);
                //小于一分钟，直接结束
                return null;
            }
        }

        //可自助调整超时时间  也就是说如果大于10秒，才会抛出异常
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        //这些目前都是无用操作，没有发送
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                prop.getAccessKeyId(), prop.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(template);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("123456");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        log.info("发送短信状态：{}", sendSmsResponse.getCode());
        log.info("发送短信消息：{}", sendSmsResponse.getMessage());

        log.info(phone+"+"+code);

        //发送短信成功后，写入redis，指定生存时间为一分钟
        //写入的k为手机号，为时间戳 ，失效时间为一分钟
        redistemplate.opsForValue().set(key,String.valueOf(System.currentTimeMillis()),1,TimeUnit.MINUTES);


        return sendSmsResponse;
    }




}