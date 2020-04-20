package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.Service.RouteService;
import com.example.demo.entity.GatewayFilterDefinition;
import com.example.demo.entity.GatewayPredicateDefinition;
import com.example.demo.entity.GatewayRouteDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务，获取路由信息
 */
@Component
//添加了lombok依赖可以RoutePredicateFactory直接使用此注解来使用日志
@Slf4j
public class RoutesScheduling {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**设置路由服务器的id*/
    private static final String routeServer = "route-server";
    /**设置本地路由信息的版本号为0*/
    private static Long versionId = 0L;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RouteService routeService;

    /**计划任务，每60秒中执行一次，如果版本号不相等则获取最新路由信息并更新网关路由*/
    @Scheduled(cron = "*/10 * * * * ?")
    public void getRoutes() {
        try {
            log.info("获取路由:" + dateFormat.format(new Date()));
            //获取版本信息
            Long lastVersionId = restTemplate.getForObject("http://" + routeServer + "/version/lastVersion", Long.class);
            log.info("路由版本信息：本地版本号：" + versionId + "，远程版本号：" + lastVersionId);
            /*判断路由版本情况，如果不为空且版本号不等于远程版本，则去路由服务器获取路由信息*/
            if (lastVersionId != null && versionId != lastVersionId) {
                //获取路由信息
                String routesResult = restTemplate.getForObject("http://" + routeServer + "/routes/", String.class);
                /**获取的路由信息不为空，则更新路由信息*/
                log.info("routesResult" + routesResult);
                if (!StringUtils.isEmpty(routesResult)) {
                    List<GatewayRouteDefinition> list = JSON.parseArray(routesResult, GatewayRouteDefinition.class);
                    log.info("routesResult" + list);
                    for (GatewayRouteDefinition definition : list) {
                        /*把传递来的参数转换成路由对象*/
                        RouteDefinition routeDefinition = assembleRouteDefinition(definition);
                        //更新路由对象
                        log.info("routeDefinition" + routeDefinition);
                        routeService.update(routeDefinition);
                    }
                    log.info("设置版本信息");
                    /*更新本地版本信息*/
                    versionId = lastVersionId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**转换路由对象*/
    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gatewayRouteDefinition) {
        //实例化RouteDefinition
        RouteDefinition  routeDefinition = new RouteDefinition();
        //初始化RouteDefinition
        routeDefinition.setId(gatewayRouteDefinition.getId());
        routeDefinition.setOrder(gatewayRouteDefinition.getOrder());

        /**设置断言*/
        List<PredicateDefinition> pdList = new ArrayList<>();
        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gatewayRouteDefinition.getPredicates();
        for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(gpDefinition.getArgs());
            predicate.setName(gpDefinition.getName());
            pdList.add(predicate);
        }
        routeDefinition.setPredicates(pdList);

        /*设置过滤器*/
        List<FilterDefinition> filters = new ArrayList();
        List<GatewayFilterDefinition> gatewayFilters = gatewayRouteDefinition.getFilters();
        for (GatewayFilterDefinition filterDefinition : gatewayFilters) {
            FilterDefinition filter = new FilterDefinition();
            filter.setName(filterDefinition.getName());
            filter.setArgs(filterDefinition.getArgs());
            filters.add(filter);
        }
        routeDefinition.setFilters(filters);

        URI uri = null;
        if (gatewayRouteDefinition.getUri().startsWith("http")) {
            uri = UriComponentsBuilder.fromHttpUrl(gatewayRouteDefinition.getUri()).build().toUri();
        } else {
            uri = URI.create(gatewayRouteDefinition.getUri());
        }
        routeDefinition.setUri(uri);
        return routeDefinition;
    }
}
