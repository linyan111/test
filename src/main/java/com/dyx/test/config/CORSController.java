package com.dyx.test.config;

//用于允许所有请求都跨域
//因为是二次请求，第一次获取html页面，第二次通过html页面上的js代码异步获取数据，一旦部署到服务器就容易
//面临跨域请求问题，允许所有访问都跨域就不会出现通过ajax获取数据获取不到的问题


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration相当于把该类看做spring的xml配置文件中的<bean>，作用为配置spring容易（应用上下文）
@Configuration
public class CORSController extends WebMvcConfigurerAdapter{

    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
