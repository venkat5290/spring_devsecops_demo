package com.example.devsecops_demo.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public final String instance = "DevSecops_Machine";
    public final String name = "Welcome to Automated JENKINS DEPLOYMENT to K8S";

    private String result;

    public String getHealth() {
        result = this.instance + " is OK";
        return result;
    }

    public ResponseEntity<String> getHello() {
        result = "<html>\n"+"<title>\n"+"Hello App"+"</title>\n"+
                "<body>\n"+"<center>\n"+"<h1>\n"+"Hello!!Welcome "+this.name+
                "</h1>\n"+"</center>\n"+"</body>\n"+"</html>";

        return ResponseEntity.ok(result);
    }
}
