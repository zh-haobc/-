package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.service.CheckItemService;
import com.itheima.dao.CheckItemDao;
import com.itheima.entiy.PageResult;
import com.itheima.entiy.QueryPageBean;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    //注入dao对象
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //检查项分页查询方法
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        //当前页码
        Integer currentPage = queryPageBean.getCurrentPage();
        //每页记录数
        Integer pageSize = queryPageBean.getPageSize();
        //查询条件
        String queryString = queryPageBean.getQueryString();
        // 完成分页查询，基于mybatis框架提供的分页助手插件来完成

        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);

    }

    //根据ID来删除检查项
    public void deleteById(Integer id) {
        long count = checkItemDao.findCountByCheckItemId(id);
        if (count>0){
            // 说明当前检查项已经被关联到检查组了，不允许删除
            new RuntimeException();
        }
        checkItemDao.deleteById(id);

    }

    // 编辑检查项
    public void edit(CheckItem checkItem) {
       checkItemDao.edit(checkItem);
    }

    //  回显检查项
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

}
