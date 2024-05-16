package com.radiantdelta.sbertask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.radiantdelta.sbertask.SberTaskApplication;
import com.radiantdelta.sbertask.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SberTaskApplication.class)
class ProductControllerTest {
    @InjectMocks
    ProductController controller ;

    @Autowired
    WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getNotExisted() throws Exception {
        mvc.perform(get("/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }
    @Test
    public void seeOther() throws Exception {
        Product p1 = mockProduct("create");
        log.info(p1.getId() + " Original id");
        byte[] p1Json = toJson(p1);
        MvcResult result = mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(p1Json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        int id = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class).getId();

        Product p2 = mockProduct("seeOther");
        p2.setId(id);
        byte[] p2Json = toJson(p2);
        MvcResult result2 = mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(p2Json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isSeeOther())
                .andReturn();
    }
    @Test
    public void createGetDelete() throws Exception {
        Product p1 = mockProduct("createAndGetAndDelete");
        log.info(p1.getId() + " Original id");
        byte[] p1Json = toJson(p1);
        MvcResult result = mvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(p1Json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        ObjectMapper objectMapper = new ObjectMapper();
        int id = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class).getId();
        p1.setId(id);

        log.info("response id of created Product is "+id);
        // GET
        MvcResult result2 = mvc.perform(get("/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) id)))
                .andExpect(jsonPath("$.amount", is(p1.getAmount())))
                .andExpect(jsonPath("$.productName", is(p1.getProductName())))
                .andReturn();
        //DELETE
        mvc.perform(delete("/" + id))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteNotExisted() throws Exception {
        mvc.perform(delete("/" + 20))
                .andExpect(status().isNoContent());
    }


    @Test
    public void createAndUpdateAndDelete() throws Exception {
        Product p1 = mockProduct("createAndUpdate");
        byte[] p1Json = toJson(p1);
        //CREATE
        MvcResult result = mvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(p1Json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        ObjectMapper objectMapper = new ObjectMapper();
        int id = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class).getId();
        log.info("response id of created Product is "+id);

        Product p2 = mockProduct("createAndUpdate2");
        p2.setId(id);
        byte[] p2Json = toJson(p2);

        //UPDATE
        result = mvc.perform(put("/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(p2Json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //GET updated
        mvc.perform(get("/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) id)))
                .andExpect(jsonPath("$.amount", is(p2.getAmount())))
                .andExpect(jsonPath("$.productName", is(p2.getProductName())))
                .andReturn();

        //DELETE
        mvc.perform(delete("/" + id))
                .andExpect(status().isOk());

    }


    private Product mockProduct(String prefix) {
        Product p = new Product();
        p.setAmount(new Random().nextInt(100));
        p.setProductName(prefix + "_name");
        p.setId(new Random().nextInt(10));
        return p;
    }

    private byte[] toJson(Object p) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(p).getBytes();
    }




}