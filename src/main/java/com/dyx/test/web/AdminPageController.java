package com.dyx.test.web;

//做前后端分析数据都从RESTFUL接口获取，controller只进行json数据的传输不进行页面跳转
//该类专门负责后台接麦你的跳转

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    @GetMapping(value = "/admin")
    public String admin(){
        return "redirect:admin_category_list";
    }

    @GetMapping(value = "admin_category_list")
    public String listCategory(){
        return "admin/listCategory";
    }

}
