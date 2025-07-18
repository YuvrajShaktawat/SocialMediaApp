package com.springproject.rest.webservices.restful_web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class someBeanController {

    @GetMapping("/some-bean")
    public MappingJacksonValue retrieveSomeBean() {
        someBean someBeanObj= new someBean("value1", "value2", "value3");
        return Filtering(someBeanObj, "field1", "field2");
    }

    @GetMapping("/some-bean-filtering-List")
    public MappingJacksonValue retrieveSomeBeanUsingFiltering() {
        List<someBean> beans = Arrays.asList(
            new someBean("null", "raju", "mohan"),
            new someBean("value1", "value2", "value3")
        );
        return Filtering(beans, "field1", "field2");
    }

    public MappingJacksonValue Filtering(Object data, String... fields) {
        MappingJacksonValue mapping = new MappingJacksonValue(data);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        mapping.setFilters(filters);
        return mapping;
    }
}