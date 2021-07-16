package com.itheima.service;

import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.pojo.Setmeal;

public interface SetmealService {
    public void add(Setmeal setmeal,Integer[] checkgroupIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
}
