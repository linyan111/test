package com.dyx.test.dao;

import com.dyx.test.pojo.Product;
import com.dyx.test.pojo.Property;
import com.dyx.test.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDao extends JpaRepository<PropertyValue,Integer> {

    List<PropertyValue> findByProductOrderByIdDesc(Product product);
    PropertyValue getByPropertyAndProduct(Property property,Product product);
}
