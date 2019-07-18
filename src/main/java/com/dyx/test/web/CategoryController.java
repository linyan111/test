package com.dyx.test.web;

import com.dyx.test.pojo.Category;
import com.dyx.test.service.CategoryService;
import com.dyx.test.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//RestController会将方法返回值以json的格式发送到界面上
@RestController
public class CategoryController {

    @Autowired CategoryService categoryService;

    //获取所有的分类，通过JPA内置的方法进行查询,没有分页
    /*@GetMapping("/categories")
    public List<Category> list() throws Exception{
        return categoryService.list();
    }*/

    /**
     * 获取所有的分类，有分页,通过工具类page4Navigator对数据进行封装
     * @param start 表示开始的位置
     * @param size  表示每页显示的个数
     * navigatePages 表示的是分页条最多显示的个数
     * @return  返回分类的信息
     * @throws Exception
     * @RequestParam,springmvc获取参数的一种方式
     */
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start",defaultValue = "0") int start,
                                         @RequestParam(value = "size",defaultValue = "5") int size) throws Exception{
        start = start<0?0:start;
        Page4Navigator<Category> page = categoryService.list(start,size,5);
        return page;
    }
}
