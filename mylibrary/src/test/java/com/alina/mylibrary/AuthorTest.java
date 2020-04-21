package com.alina.mylibrary;


import com.alina.mylibrary.controller.Interfaces.AuthorApi;
import com.alina.mylibrary.repository.Admin.AuthorRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AuthorApi.class)

public class AuthorTest {


    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;


    @Before
    public void setup() throws Exception{
        this.mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAuthors() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/author")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author[*].authorId").isNotEmpty());


    }
}
