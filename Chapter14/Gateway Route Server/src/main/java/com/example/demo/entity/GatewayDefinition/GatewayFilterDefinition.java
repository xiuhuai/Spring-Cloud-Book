package com.example.demo.entity.GatewayDefinition;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器模型
 */
@Data
public class GatewayFilterDefinition {

    //Filter的Name
    private String name;
    //对应的路由规则
    private Map<String, String> args = new LinkedHashMap<>();


}
