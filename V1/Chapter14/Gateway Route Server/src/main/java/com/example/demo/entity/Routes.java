package com.example.demo.entity;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.GatewayDefinition.GatewayFilterDefinition;
import com.example.demo.entity.GatewayDefinition.GatewayPredicateDefinition;
import org.springframework.util.StringUtils;


import java.util.Date;
import java.util.List;
//@Data
public class Routes {

    private Long id;

    private String routeId;

    private String routeUri;

    private Integer routeOrder;

    private Date createTime;

    private Date updateTime;

    private String predicates;

    private String filters;

    /**
     * 获取断言集合
     * @return
     */
    public List<GatewayPredicateDefinition> getPredicateDefinition(){
        if(!StringUtils.isEmpty(this.predicates)){
            return JSON.parseArray(this.predicates , GatewayPredicateDefinition.class);
        }
        return null;
    }

    /**
     * 获取过滤器集合
     */
    public List<GatewayFilterDefinition> getFilterDefinition(){
        if(!StringUtils.isEmpty(this.filters)){
            return JSON.parseArray(this.filters , GatewayFilterDefinition.class);
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId == null ? null : routeId.trim();
    }

    public String getRouteUri() {
        return routeUri;
    }

    public void setRouteUri(String routeUri) {
        this.routeUri = routeUri == null ? null : routeUri.trim();
    }

    public Integer getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(Integer routeOrder) {
        this.routeOrder = routeOrder;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPredicates() {
        return predicates;
    }

    public void setPredicates(String predicates) {
        this.predicates = predicates == null ? null : predicates.trim();
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters == null ? null : filters.trim();
    }


    @Override
    public String toString() {
        return "Routes{" +
                "id=" + id +
                ", routeId='" + routeId + '\'' +
                ", routeUri='" + routeUri + '\'' +
                ", routeOrder=" + routeOrder +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", predicates='" + predicates + '\'' +
                ", filters='" + filters + '\'' +
                '}';
    }
}