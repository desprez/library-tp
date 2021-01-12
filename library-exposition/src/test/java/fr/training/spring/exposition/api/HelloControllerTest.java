package fr.training.spring.exposition.api;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Unit Test exemple with mocked server
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = { HelloController.class })
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	// Nominal scenario
	@Test
	public void getHelloSuccess() throws Exception {

		mvc.perform(MockMvcRequestBuilders.get("/hello?name=Martin") //
				.accept(MediaType.TEXT_PLAIN)) //
		.andExpect(status().isOk()) //
		.andExpect(content().string(equalTo("Hello Martin !")));
	}

	// Exception scenario
	@Test
	public void getHelloWithoutArgumentShouldFails() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello") //
				.accept(MediaType.TEXT_PLAIN)) //
		.andExpect(status().isBadRequest());
	}

}