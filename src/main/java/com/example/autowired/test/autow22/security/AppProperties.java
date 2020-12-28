package com.example.autowired.test.autow22.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class AppProperties {

    @Autowired
    private Environment environment;

    public String getTokenSecret() {
        return environment.getProperty("tokenSecret");
    }

}
