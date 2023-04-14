package com.app.jira.BugTracker.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.jira.BugTracker.service.ApplicationService;
@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(BugTrackerController.class)
public class BugTrackerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ApplicationService applicationService;

	@InjectMocks
	private BugTrackerController bugTrackerController;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(bugTrackerController);
	}
	@Test
	public void testAddApplication() throws Exception{
		String mockApplicationJson = "{\"name\":\"Test Application\",\"description\":\"A test application.\",\"owner\":\"Kesha Williams\"}";

		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/tza/application/")
				.accept(MediaType.APPLICATION_JSON).content(mockApplicationJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	 @Test
	    public void testFindApplication() throws Exception {

		 testAddApplication();

	        //create a request
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	                .get("/tza/application/1")
	                .accept(MediaType.APPLICATION_JSON);

	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	        String expected = "{\"id\":1,\"name\":\"Test Application\",\"description\":\"A test application.\",\"owner\":\"Kesha Williams\"}";

	        //Assert that response is what was expected
	        assertEquals(expected, result.getResponse().getContentAsString());
	    }

	    @Test
	    public void testUpdateApplication() throws Exception {
	    	testAddApplication();

	        String mockApplicationJson = "{\"id\":1,\"name\":\"Test Application Updated\",\"description\":\"An updated test application.\",\"owner\":\"Kesha Williams\"}";

	        //Create a put request with an accept header for application\json
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	                .put("/tza/application/")
	                .accept(MediaType.APPLICATION_JSON).content(mockApplicationJson)
	                .contentType(MediaType.APPLICATION_JSON);

	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	        String expected = "{\"id\":1,\"name\":\"Test Application Updated\",\"description\":\"An updated test application.\",\"owner\":\"Kesha Williams\"}";

	        //Assert that response is what was expected
	        assertEquals(expected, result.getResponse().getContentAsString());
	    }

	    @Test
	    public void testDeleteApplication() throws Exception {
	    	testAddApplication();

	        String mockApplicationJson = "{\"id\":1,\"name\":\"Test Application Updated\",\"description\":\"An updated test application.\",\"owner\":\"Kesha Williams\"}";

	        //Create a put request with an accept header for application\json
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	                .delete("/tza/application/1")
	                .accept(MediaType.APPLICATION_JSON).content(mockApplicationJson)
	                .contentType(MediaType.APPLICATION_JSON);

	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();

	        //Assert that the return status is 204 No Content
	        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	    }

	    @Test
	    public void testAddTicket() throws Exception {
	    	testAddApplication();

	        String mockTicketJson = "{\"title\": \"Multiple Assignees Needed\", \"description\" : \"There are cases where we need to assign a ticket to more than one person.\", \"application\": {\"id\":\"1\"},\"status\": \"Open\"}";

	        //Create a put request with an accept header for application\json
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	                .post("/tza//ticket")
	                .accept(MediaType.APPLICATION_JSON).content(mockTicketJson)
	                .contentType(MediaType.APPLICATION_JSON);

	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();

	        //Assert that the return status is 201 Created
	        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	    }
}
