package com.example.devsecops_demo.Controller;

import com.example.devsecops_demo.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController
{

    private String instance = "DevSecops_Machine";
    private String name = "Welcome to Automated JENKINS DEPLOYMENT to K8S";

    @Autowired
    private HelloService hs;

    @GetMapping("/health")
    public ResponseEntity<String> health(){
        return hs.getHealth(instance);
    }

    @GetMapping("/")
    public ResponseEntity<String> hello(){
        return hs.getHello(name);
    }
}
