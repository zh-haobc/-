package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entiy.PageResult;
import com.itheima.pojo.CheckGroup;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void SetCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> findByConition(String queryString);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup);
    public void deleteAssoication(Integer id);
    public List<CheckGroup> findAll();
}
