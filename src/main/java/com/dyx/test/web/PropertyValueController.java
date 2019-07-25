package com.dyx.test.web;

import com.dyx.test.pojo.Product;
import com.dyx.test.pojo.PropertyValue;
import com.dyx.test.service.ProductService;
import com.dyx.test.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PropertyValueController {

    @Autowired PropertyValueService propertyValueService;
    @Autowired ProductService productService;

    @GetMapping("/products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception{
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValues = propertyValueService.list(product);
        return propertyValues;
    }

    public PropertyValue update(@RequestBody PropertyValue bean) throws Exception{
        propertyValueService.update(bean);
        return bean;
    }
}
