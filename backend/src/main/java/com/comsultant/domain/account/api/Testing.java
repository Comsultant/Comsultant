package com.comsultant.domain.account.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Testing {
    @GetMapping("")
    public void print(){
        System.out.println("success");
    }
}
