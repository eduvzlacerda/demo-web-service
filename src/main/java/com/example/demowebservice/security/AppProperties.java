package com.example.demowebservice.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppProperties {

    private Environment environment;

    public String getTokenSecret(){
        return environment.getProperty("tokenSecret");
    }
}
