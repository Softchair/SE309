//package com.infoFootball.SpringBackend;
//
//import com.infoFootball.SpringBackend.Register.RegisterController;
//import com.infoFootball.SpringBackend.User.User;
//import com.infoFootball.SpringBackend.User.UserRepository;
//import io.restassured.RestAssured;
//import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
////import static org.mockito.Mockito.when;
////import static org.mockito.Mockito.any;
////import static org.hamcrest.CoreMatchers.*;
////import static org.hamcrest.Matchers.*;
////import static org.hamcrest.Matchers.is;
////import org.junit.Test;
////import org.junit.runner.RunWith;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.test.context.junit4.SpringRunner;
////import org.springframework.test.web.servlet.MockMvc;
////import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
////import org.springframework.boot.test.mock.mockito.MockBean;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.hamcrest.Matchers.*;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = RegisterController.class)
//@WebMvcTest(RegisterController.class)
//public class RegisterControllerTest {
//
//    @Autowired
//    private MockMvc controller;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void testCreateUser() throws Exception {
//        //Creating user JSON object
//        JSONObject jsonUser = new JSONObject();
//        jsonUser.put("username", "testUser");
//        jsonUser.put("password","testPassword");
//        jsonUser.put("email","testEmail@email");
//        jsonUser.put("phoneNum","123456789");
//        jsonUser.put("permLv","Admin");
//
//        controller.perform(post("/login/register")
//                .contentType(MediaType.APPLICATION_JSON).content(jsonUser.toString())) //Sends in the json user
//                .andExpect(status().isOk()) //Test that it gets a request
//                .andExpect(content().string("Success")); //Checks to see response is correct
//    }
//
//
//}
