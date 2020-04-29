package com.leyou.config;

import com.leyou.auth.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PublicKey;

/**
 * 保存此授权中心微服务的公钥以及全局配置
 */
@ConfigurationProperties(prefix = "leyou.jwt"  )
@Component
@Data
@Slf4j
public class JwtProperties {

    private String pubKeyPath;// 公钥路径
    private String cookieName;


    private PublicKey publicKey; // 公钥



    /**
     * PostConstruct表示在构造函数后执行，是在javax包下的注解
     * 在构造函数后(即从配置文件获取到属性后)读取公钥
     * 构造函数 -> 自动注入@Autowired -> @PostConstruct -> InitializingBean -> xml中配置的init-method="init"方法
     */
    @PostConstruct
    public void init(){
        try {
            File pubKey = new File(pubKeyPath);
            // 从文件读取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥失败！", e);
            throw new RuntimeException();
        }
    }

}