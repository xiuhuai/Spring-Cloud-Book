package com.example.demo.service;

import com.example.demo.entity.GatewayDefinition.GatewayRouteDefinition;
import com.example.demo.entity.Routes;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author: longzhonghua
 * @date: 2020/4/7
 * Description:
 */
@Service
public interface RoutesService {
    /** 查询路由信息     */
    List<Routes> getRoutes(Routes route);

    /**返回网关需要的路由信息     */
    List<GatewayRouteDefinition> getRouteDefinitions();

}
