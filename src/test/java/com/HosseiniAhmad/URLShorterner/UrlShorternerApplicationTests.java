package com.HosseiniAhmad.URLShorterner;

import com.HosseiniAhmad.URLShorterner.controller.UrlController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShorternerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetLongUrl() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/short-url"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("https://example.com"));
	}
}
