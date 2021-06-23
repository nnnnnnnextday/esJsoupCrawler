package com.example.esdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping({"/","/index"})// "/"或者"/index"都可以访问
    public String index(){
        return "index";
    }
}
