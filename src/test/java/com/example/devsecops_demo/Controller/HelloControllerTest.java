package com.example.devsecops_demo.Controller;

import com.example.devsecops_demo.Service.HelloService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HelloService hs;

    @Test
    void testHello() throws Exception{

        String name = "Welcome to Junit Test";

        Mockito.when(this.hs.getHello()).thenReturn(ResponseEntity.ok("Hello "+ name));

         this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(name)));

    }

    @Test
    void testHealth() throws Exception{

        String instance = "test_machine";

        Mockito.when(this.hs.getHealth())
                .thenReturn(instance);

        this.mockMvc.perform(get("/health"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(instance)));

    }

}
