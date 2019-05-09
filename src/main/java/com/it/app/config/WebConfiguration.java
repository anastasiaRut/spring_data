package com.it.app.config;

import com.it.app.config.customMapper.CourseMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfiguration {
    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(customerMap().getCourseMappingRequestDto());
        mapper.addMapping(customerMap().getCourseMappingResponseDto());
        return mapper;
    }

    @Bean
    public static CourseMapper customerMap() {
        return new CourseMapper();
    }
}