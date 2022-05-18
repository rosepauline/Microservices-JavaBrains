package com.example.springbootconfig;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.env.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GreetingController {

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("${app.description}")
    private String descriptionMessage;

    @Value("some static message")
    private String staticMessage;

    @Value("${my.list.values}")
    private List<String> listValues;

    @Value("#{${dbValues: {connectionString: 'http://____',userName: 'foo', password: 'pass'}}}")
    private Map<String, String> dbValues;

    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private Environment env;

    @GetMapping("/db-settings")
    public String dbSettings(){
        return dbSettings.getConnection()+dbSettings.getHost();
    }

    @GetMapping("/greeting")
    public String greeting(){
        return greetingMessage +" "+ staticMessage + listValues+" "+dbValues;
    }

    @GetMapping("/description")
    public String description(){
        return descriptionMessage;
    }

    @GetMapping("/envdetails")
    private String envDetails(){
        return env.toString();
    }
}
