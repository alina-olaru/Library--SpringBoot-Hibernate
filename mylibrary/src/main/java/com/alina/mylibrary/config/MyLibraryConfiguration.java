package com.alina.mylibrary.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyLibraryConfiguration {

    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBean() {
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(Arrays.asList("dozer-mapping.xml"));
        return dozerBean;
    }
}
