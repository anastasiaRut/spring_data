package com.it.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * The main configuration class
 */
@Configuration
@ComponentScan(basePackages = "com.it")
@Import({DBConfiguration.class, WebConfiguration.class, MessagesConfiguration.class, SecurityConfiguration.class, SwaggerConfiguration.class})
public class AppConfiguration {
}
