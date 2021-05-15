package com.example.demo;

import com.example.demo.controllers.CartController;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.hibernate.annotations.SourceType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.JsonPathRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SareetaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private UserController controller;

//	@BeforeEach
//	public void beforeEach() {
//		mockMvc = MockMvcBuilders.standaloneSetup(UserController.class).build();
//	}

	@Test
  public void t(){
    System.out.println("hi");
  }
//
//	@Test
//	public void createNewUser() throws Exception {
//
//		User newObjectInstance = new User("Robert");
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create")
//		.content(asJsonString(newObjectInstance))
//  		.contentType(MediaType.APPLICATION_JSON)
//  		.accept(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(jsonPath("$.username", Matchers.is("Robert")));
//
//
//	}
//
//	public static String asJsonString(final Object obj) {
//		try {
//			final ObjectMapper mapper = new ObjectMapper();
//
//			final String jsonContent = mapper.writeValueAsString(obj);
//
//			return jsonContent;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

}
