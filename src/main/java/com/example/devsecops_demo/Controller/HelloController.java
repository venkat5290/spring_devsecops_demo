package com.example.devsecops_demo.Controller;

import com.example.devsecops_demo.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class HelloController
{
    @Autowired
    private HelloService hs;

    @GetMapping("/health")
    String health(){
        return hs.getHealth();
    }

    @GetMapping("/")
    ResponseEntity<String> hello(){
        return hs.getHello();
    }

}
