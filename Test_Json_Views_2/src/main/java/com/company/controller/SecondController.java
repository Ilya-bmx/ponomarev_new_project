package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.Path;

/**
 * Created by ponomarev_ia on 28.08.2019.
 */
@Controller
public class SecondController {

    @RequestMapping("/")
    public String helloController(){
        System.out.println(getClass().getName());
        return "hello";
    }

}
