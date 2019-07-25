package com.dyx.test.dao;

import com.dyx.test.pojo.Product;
import com.dyx.test.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDao extends JpaRepository<ProductImage,Integer> {
    public List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product,String type);
}
