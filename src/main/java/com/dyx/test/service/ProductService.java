package com.dyx.test.service;

import com.dyx.test.dao.ProductDao;
import com.dyx.test.pojo.Category;
import com.dyx.test.pojo.Product;
import com.dyx.test.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired ProductDao productDao;
    @Autowired CategoryService categoryService;

    public void add(Product bean){
        productDao.save(bean);
    }

    public void delete(int id){
        productDao.delete(id);
    }

    public void update(Product bean){
        productDao.save(bean);
    }

    public Product get(int id){
        return productDao.findOne(id);
    }

    public Page4Navigator<Product> list(int cid,int start,int size,int navigatePages){
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Product> pageFromJPA = productDao.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
}
