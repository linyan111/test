package com.dyx.test.service;

import com.dyx.test.dao.CategoryDao;
import com.dyx.test.dao.PropertyDao;
import com.dyx.test.pojo.Category;
import com.dyx.test.pojo.Property;
import com.dyx.test.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired PropertyDao propertyDao;
    @Autowired CategoryService categoryService;

    public void add(Property bean){
        propertyDao.save(bean);
    }

    public void delete(int id){
        propertyDao.delete(id);
    }

    public Property get(int id){
        return propertyDao.findOne(id);
    }

    public void update(Property bean){
        propertyDao.save(bean);
    }

    public Page4Navigator<Property> list(int cid,int start,int size,int navigatePages){
        Category category =categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(start,size,sort);
        Page<Property> pageFromJPA = propertyDao.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Property> listByCategory(Category category){
        return propertyDao.findByCategory(category);
    }
}
