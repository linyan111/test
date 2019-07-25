package com.dyx.test.dao;

import com.dyx.test.pojo.Category;
import com.dyx.test.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyDao extends JpaRepository<Property,Integer>{
    Page<Property> findByCategory(Category category, Pageable pageable);
    List<Property> findByCategory(Category category);
}
