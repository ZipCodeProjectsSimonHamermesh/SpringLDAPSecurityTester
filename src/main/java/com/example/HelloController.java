package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by simonhamermesh on 3/24/16.
 */

@RestController
public class HelloController {

    @RequestMapping
    public String index(){
        return "Welcome to the home page!";
    }

}
