package com.directi.train.twimini;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

@Configuration
public class TwiConfig {
    @Bean
    public SimpleJdbcTemplate simpleJdbcTemplate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/twiminidb");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        SimpleJdbcTemplate db = new SimpleJdbcTemplate(dataSource);
//        db.update("CREATE TABLE users (\n" +
//                "         username VARCHAR(20),\n" +
//                "         password VARCHAR(20)\n" +
//                "       );");
        return db;
    }

    @Bean
    public ThreadLocal<Long> userID() {
        return new ThreadLocal<Long>();
    }
}