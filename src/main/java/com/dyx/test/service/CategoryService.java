package com.dyx.test.service;

import com.dyx.test.dao.CategoryDao;
import com.dyx.test.pojo.Category;
import com.dyx.test.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

   //带参数的list方法，分页
    public Page4Navigator<Category> list(int start,int size,int navigatePages){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page pageFromJPA = categoryDao.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    //新增分类
    public void add(Category bean){
        categoryDao.save(bean);
    }

}
