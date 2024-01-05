package com.example.devsecops_demo.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloServiceTest {

    @Autowired
    private HelloService hs;

    @Test
    void testGetHealth(){
        String expected = "DevSecops_Machine is OK";
        String result = this.hs.getHealth();

        Assertions.assertNotNull(result);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testGetHello(){
        String expected  = "<html>\n"+"<title>\n"+"Hello App"+"</title>\n"+
                "<body>\n"+"<center>\n"+"<h1>\n"+"Hello!! "+"Welcome to Automated JENKINS DEPLOYMENT to K8S"+
                "</h1>\n"+"</center>\n"+"</body>\n"+"</html>";
        String result = this.hs.getHello().getBody();

        Assertions.assertNotNull(result);

        Assertions.assertEquals(expected, result);

    }
}
