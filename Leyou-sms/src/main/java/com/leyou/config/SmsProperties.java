package com.leyou.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 定义了配置类，这个属性在yml中，所以加ConfigurationProperties注解，实现自动装填
 */
@ConfigurationProperties(prefix = "ly.sms")
@Data
public class SmsProperties {

    String accessKeyId;

    String accessKeySecret;

    String signName;

    String verifyCodeTemplate;


}