package com.it.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.it")
@Import({DBConfiguration.class, WebConfiguration.class})
public class AppConfiguration {

}