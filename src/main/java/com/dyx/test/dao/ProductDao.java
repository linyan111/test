package com.dyx.test.dao;

import com.dyx.test.pojo.Category;
import com.dyx.test.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer>{
    Page<Product> findByCategory(Category category, Pageable pageable);
}
