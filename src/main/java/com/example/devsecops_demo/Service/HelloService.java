package com.example.devsecops_demo.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    private String result;

    public ResponseEntity<String> getHealth(String instance) {
        result = instance + " is OK";
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> getHello(String name) {
        result = "<html>\n"+"<title>\n"+"Hello App"+"</title>\n"+
                "<body>\n"+"<center>\n"+"<h1>\n"+"Hello!! "+name+
                "</h1>\n"+"</center>\n"+"</body>\n"+"</html>";

        return ResponseEntity.ok(result);
    }
}
