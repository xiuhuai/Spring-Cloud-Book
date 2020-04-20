package com.example.demo.service;

import com.example.demo.Mapper.GatewayRoutesMapper;
import com.example.demo.entity.GatewayDefinition.GatewayRouteDefinition;
import com.example.demo.entity.Routes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: longzhonghua
 * @date: 2020/4/7
 * Description:
 */
@Service
public class RoutesServiceImpl implements RoutesService {

    @Resource
    private GatewayRoutesMapper gatewayRoutesMapper;

    /**查询路由信息     */
    @Override
    public List<Routes> getRoutes(Routes route) {
        return gatewayRoutesMapper.getRoutes(route);
    }

    /**返回网关需要的路由信息     */
    @Override
    public List<GatewayRouteDefinition> getRouteDefinitions() {
        List<GatewayRouteDefinition> routeDefinitions = new ArrayList<>();
        Routes route = new Routes();
        List<Routes> routes = getRoutes(route);
        for (Routes gatewayRoute : routes) {
            GatewayRouteDefinition routeDefinition = new GatewayRouteDefinition();
            routeDefinition.setId(gatewayRoute.getRouteId());
            routeDefinition.setUri(gatewayRoute.getRouteUri());
            routeDefinition.setFilters(gatewayRoute.getFilterDefinition());
            routeDefinition.setPredicates(gatewayRoute.getPredicateDefinition());
            routeDefinitions.add(routeDefinition);
        }
        return routeDefinitions;
    }


}
