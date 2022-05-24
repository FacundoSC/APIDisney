package com.javadabadu.disney.controller;


import com.javadabadu.disney.util.Uri;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void mainMethod() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(Uri.PATH_BASE + "/")
                        .accept(MediaType.APPLICATION_JSON_VALUE)).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.Genero:").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.Character:").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.Movie:").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.Serie:").isNotEmpty());
    }
}
