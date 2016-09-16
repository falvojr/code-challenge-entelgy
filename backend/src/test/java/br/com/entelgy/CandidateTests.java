package br.com.entelgy;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.entelgy.model.Candidate;
import br.com.entelgy.model.rest.CandidateRestResource;
import br.com.entelgy.util.JsonTestUtil;
import br.com.entelgy.util.StringTestUtil;

/**
 * Unit tests for HTTP methods has following roles in RESTful services.
 * 
 * @author falvojr
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CandidateTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CandidateRestResource candidatesRepository;

	@After
	public void deleteAllAfterTest() throws Exception {
		//TODO Comment the following line to keep the test data.
		//candidatesRepository.deleteAll();
	}

	/**
	 * Method GET: View.
	 */
	@Test
	public void shouldReturnRepositoryIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._links.candidates").exists());
	}

	/**
	 * Method POST: Create.
	 */
	@Test
	public void shouldCreateEntity() throws Exception {
		final String jsonContent = JsonTestUtil.convertObjectToJsonString(mock());
		mockMvc.perform(post("/candidates").content(jsonContent))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString("candidates/")));
	}

	/**
	 * Method PUT: Total update.
	 */
	@Test
	public void shouldUpdateEntity() throws Exception {
		final Candidate candidate = mock();
		final MvcResult mvcResult = mockMvc.perform(post("/candidates").content(JsonTestUtil.convertObjectToJsonString(candidate)))
				.andExpect(status().isCreated())
				.andReturn();
		
		final String name = "Barack";
		final String overview = "Yes we can!";
		final String photo = "svg-4";
		candidate.setName(name);
		candidate.setOverview(overview);
		candidate.setAvatar(photo);
		
		final String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(put(location).content(JsonTestUtil.convertObjectToJsonString(candidate)))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(name))
				.andExpect(jsonPath("$.overview").value(overview))
				.andExpect(jsonPath("$.avatar").value(photo));
	}

	/**
	 * Method PATCH: Partial update
	 */
	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(post("/candidates").content(JsonTestUtil.convertObjectToJsonString(mock())))
				.andExpect(status().isCreated())
				.andReturn();
		
		final Candidate candidate = new Candidate();
		final String name = "Bear";
		final String photo = "svg-5";
		candidate.setName(name);
		candidate.setAvatar(photo);

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content(JsonTestUtil.convertObjectToJsonString(candidate)))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(name));
	}

	/**
	 * Method DELETE: Delete.
	 */
	@Test
	public void shouldDeleteEntity() throws Exception {
		final String jsonContent = JsonTestUtil.convertObjectToJsonString(mock());
		final MvcResult mvcResult = mockMvc.perform(post("/candidates").content(jsonContent))
				.andExpect(status().isCreated())
				.andReturn();

		final String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location))
				.andExpect(status().isNotFound());
	}

	/**
	 * Create a new mock for {@link Candidate} class.
	 * 
	 * @return mocked {@link Candidate}.
	 */
	private Candidate mock() throws IOException {
		final Candidate candidate = new Candidate();
		candidate.setName(StringTestUtil.randomString(15));
		candidate.setOverview(StringTestUtil.randomString(500));
		candidate.setAvatar("svg-1");
		return candidate;
	}
}
