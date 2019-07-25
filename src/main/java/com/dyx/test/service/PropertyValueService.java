package com.dyx.test.service;

import com.dyx.test.dao.PropertyValueDao;
import com.dyx.test.pojo.Product;
import com.dyx.test.pojo.Property;
import com.dyx.test.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueService {

    @Autowired PropertyValueDao propertyValueDao;
    @Autowired PropertyService propertyService;

    public void update(PropertyValue bean){
        propertyValueDao.save(bean);
    }

    public PropertyValue getByPropertyAndProduct(Product product,Property property){
        return propertyValueDao.getByPropertyAndProduct(property,product);
    }

    public List<PropertyValue> list(Product product){
        return  propertyValueDao.findByProductOrderByIdDesc(product);
    }

    public void init(Product product){
        List<Property> properties = propertyService.listByCategory(product.getCategory());
        for(Property property : properties){
            PropertyValue propertyValue = getByPropertyAndProduct(product,property);
            if(null==propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueDao.save(propertyValue);
            }
        }
    }

}
