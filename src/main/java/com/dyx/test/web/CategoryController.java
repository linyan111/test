package com.dyx.test.web;

import com.dyx.test.pojo.Category;
import com.dyx.test.service.CategoryService;
import com.dyx.test.util.ImageUtil;
import com.dyx.test.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    //新增方式，将图片复制到img目录下将其他信息存到数据库中，文件的命名2是根据id来的，显示时根据对对应的id显示
    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception{
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image,request);
        return bean;
    }

    /**
     * 因为新增和修改都要用到文件上传和文件夹的创建，所以抽象出来
     * @param bean 分类的具体信息
     * @param image 图片
     * @param request  请求
     * @throws IOException
     */
    public void saveOrUpdateImageFile(Category bean, MultipartFile image,HttpServletRequest request)
            throws IOException{
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img,"jpg",file);
    }

    /**
     * 删除
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id,HttpServletRequest request) throws Exception{
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    //查询分类
    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id) throws Exception{
        Category bean = categoryService.get(id);
        return bean;
    }

    @PutMapping("/categories/{id}")
    public Category update(Category bean,MultipartFile image,HttpServletRequest request) throws Exception{
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.update(bean);
        if(image != null){
            saveOrUpdateImageFile(bean,image,request);
        }
        return bean;
    }
}
