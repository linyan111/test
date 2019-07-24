package com.dyx.test.web;

import com.dyx.test.pojo.Category;
import com.dyx.test.pojo.Property;
import com.dyx.test.service.PropertyService;
import com.dyx.test.util.Page4Navigator;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PropertyController {

    @Autowired PropertyService propertyService;

    @GetMapping("/categories/{cid}/properties")
    public Page4Navigator list(@PathVariable("cid") int cid, @RequestParam(value = "start",defaultValue = "0")
                               int start,@RequestParam(value = "size",defaultValue = "5") int size) throws Exception{
        start=start<0?0:start;
        Page4Navigator<Property> page = propertyService.list(cid,start,size,5);
        return page;
    }

    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id") int id) throws Exception{
        Property bean = propertyService.get(id);
        return bean;
    }

    @PostMapping("/properties")
    public Property add(@RequestBody Property bean) throws Exception{
        propertyService.add(bean);
        return bean;
    }

    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception{
        propertyService.delete(id);
        return null;
    }

    @PutMapping("/properties")
    public Property update(@RequestBody Property bean) throws Exception{
        propertyService.update(bean);
        return bean;
    }
}
