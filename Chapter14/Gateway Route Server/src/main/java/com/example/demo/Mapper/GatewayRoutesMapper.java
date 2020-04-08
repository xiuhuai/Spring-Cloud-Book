package com.example.demo.Mapper;

import com.example.demo.entity.GatewayDefinition.GatewayRouteDefinition;
import com.example.demo.entity.Routes;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface GatewayRoutesMapper {
    //添加路由信息

    @Insert({" INSERT INTO gateway_routes (id, routeId, routeUri,routeOrder, create_time, update_time, predicates,filters)" +
            " values (#{id,jdbcType=BIGINT}, #{routeId,jdbcType=VARCHAR}, #{routeUri,jdbcType=VARCHAR}, #{routeOrder,jdbcType=INTEGER},#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, #{predicates,jdbcType=LONGVARCHAR}, #{filters,jdbcType=LONGVARCHAR})"})
    int add(Routes route);

    //返回组装后网关需要的路由信息
    @Select("SELECT * FROM gateway_routes")
    List<Routes> getRoutes(Routes route);


}
