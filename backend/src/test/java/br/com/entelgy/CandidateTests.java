package br.com.entelgy;

import static br.com.entelgy.util.JacksonTestUtil.convertObjectToJsonString;
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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.com.entelgy.model.Candidate;
import br.com.entelgy.model.CandidateRepository;
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
	private CandidateRepository candidatesRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		candidatesRepository.deleteAll();
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
		final String jsonContent = convertObjectToJsonString(mock());
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
		final MvcResult mvcResult = mockMvc.perform(post("/candidates").content(convertObjectToJsonString(candidate)))
				.andExpect(status().isCreated())
				.andReturn();
		
		final String name = "Barack";
		final String overview = "Yes we can!";
		final String photo = "http://www.nocturnar.com/comunidad/attachments/obama-not-bad-campaign-poster-jpg.59235";
		candidate.setName(name);
		candidate.setOverview(overview);
		candidate.setPhoto(photo);
		
		final String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(put(location).content(convertObjectToJsonString(candidate)))
				.andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(name))
				.andExpect(jsonPath("$.overview").value(overview))
				.andExpect(jsonPath("$.photo").value(photo));
	}

	/**
	 * Method PATCH: Partial update
	 */
	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {
		final Candidate candidate = mock();
		final MvcResult mvcResult = mockMvc.perform(post("/candidates").content(convertObjectToJsonString(candidate)))
				.andExpect(status().isCreated())
				.andReturn();
		
		final String name = "Bear";
		candidate.setName(name);

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(patch(location).content(convertObjectToJsonString(candidate)))
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
		final String jsonContent = convertObjectToJsonString(mock());
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
		candidate.setPhoto("https://freeiconshop.com/files/edd/person-flat.png");
		return candidate;
	}
}
