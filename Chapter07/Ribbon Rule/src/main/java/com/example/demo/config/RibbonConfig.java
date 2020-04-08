package com.example.demo.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author: longzhonghua
 * @date: 2019/9/20
 * Description: Ribbon的配置类
 */
@Configuration
@RibbonClient(name = "provider", configuration = RibbonClientConfiguration.class)
public class RibbonConfig {

    /*
     *Ribbon的规则
     */
 @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
