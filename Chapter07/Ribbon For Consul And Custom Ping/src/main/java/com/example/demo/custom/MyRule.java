package com.example.demo.custom;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class MyRule implements IRule {
    ILoadBalancer lb;

    public MyRule(){

    }

    public MyRule(ILoadBalancer lb){
        this.lb = lb;
    }

    @Override
    public Server choose(Object o) {
        //获取全部服务器
        List<Server> servers = lb.getAllServers();
        //只返回第一个Server对象 s(此处可定义规则返回Server)
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.lb;
    }
}