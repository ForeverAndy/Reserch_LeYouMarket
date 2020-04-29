package com.leyou.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 过滤白名单配置类
 * ConfigurationProperties表示前缀自动注入
 */
@ConfigurationProperties(prefix = "leyou.filter")
@Component
@Getter
@Setter
public class AllowProperties {

    private List<String> allowPaths;

}
