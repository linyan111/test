package com.dyx.test.web;

import com.dyx.test.pojo.Category;
import com.dyx.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//RestController会将方法返回值以json的格式发送到界面上
@RestController
public class CategoryController {

    @Autowired CategoryService categoryService;

    //获取所有的分类，通过JPA内置的方法进行查询
    @GetMapping("/categories")
    public List<Category> list() throws Exception{
        return categoryService.list();
    }
}
