package com.itheima.service;

import com.itheima.constant.RedisMessageConstant;
import com.itheima.entiy.Result;

import java.util.Map;

public interface OrderService {
    public Result order(Map map) throws Exception;
    public Map findById(Integer id) throws Exception;
}
