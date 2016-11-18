package com.campus.repository;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaRepositories(basePackages = "com.campus.repository")
@PropertySource("classpath:application.yml")
@EntityScan(basePackages = {"com.campus.model"})
@EnableTransactionManagement
public class RepositoryConfig {
    
}
