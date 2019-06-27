package com.dyx.test.service;

import com.dyx.test.dao.CategoryDao;
import com.dyx.test.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    //用Autowired自动注解将categoryDao注解进来
    @Autowired CategoryDao categoryDao;

    //查询出所有的分类，并进行排序
    public List<Category> list(){
        //用Sort对id进行倒叙排列
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        //JPA内置CRUD，可以根据方法名进行判断，所以在到层不用写基础的CRUD方法，只要
        //按照JPA的方法来规范命名即可
        return categoryDao.findAll(sort);
    }
}
