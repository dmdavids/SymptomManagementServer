package com.skywomantech.cloud.symptommanagement.integration.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.skywomantech.cloud.symptommanagement.Application;
import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;
import com.skywomantech.cloud.symptommanagement.controller.SymptomManagementService;
import com.skywomantech.cloud.symptommanagement.repository.Medication;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.Physician;
import com.skywomantech.cloud.symptommanagement.testdata.TestData;

/**
 * 
 * This test shows how to fully setup and configuration a controller (or you could
 * setup all controllers) for an integration test. The real Application class is used
 * to configure the application. Spring sets up almost all of the supporting infrastructure
 * that the Application has when you run it.
 * 
 * A MockMvc or "fake" web interface is create to send requests to your controller and
 * validate the responses. Unlike the unit test, integration testing requires
 * sending mock HTTP requests to the controller and encoding the test data into JSON before
 * the mock HTTP request is sent.
 * 
 * There are a variety of annotations that are used to setup this integration test. These
 * annotations will primarily be the same across all of the integration tests for your
 * controllers. You can base other integration tests off the test below by simply
 * changing the controller that is @Autowired and passed into MockMvcBuilder.standaloneSetup(..)
 * 
 * @author jules
 *
 */

// Tell Spring to setup a web container configuration for testing
@WebAppConfiguration

// Tell JUnit to run using Spring's special JUnit runner
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })

// This is where you tell Spring the Application or Configuration object to use
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class SymptomManagementIntegrationTest {

	// Ask Spring to automatically construct and inject your VideoSvc
	// into the test
	@Autowired
	private SymptomManagementService smController;

	// This is the mock interface to our application that we will use to 
	// send mock HTTP requests
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		// Setup Spring test in standalone mode with our controller that it built
		mockMvc = MockMvcBuilders.standaloneSetup(smController).build();
	}
	
	// This test is the integration testing equivalent of the
	// VideoSvcTest.testVideoAddAndList() test. The key difference is that
	// this integration testing version sets up the entire Spring infrastructure
	// supporting your application and configures your application using the
	// real objects that you specify in your Application class.
	@Test
	public void testVideoAddAndList() throws Exception {
		Patient p = TestData.randomPatient("Donald", "Duck", "12/11/1944");
		Physician dr = TestData.randomPhysician("Minnie", "Mouse");
		Medication med = TestData.randomMedication("hugs");
		String pJson = TestData.toJson(p);
		String drJson = TestData.toJson(dr);
		String medJson = TestData.toJson(med);
		
		// Send a request that should invoke addPatient
		// and check that the request succeeded
		MvcResult result0 = mockMvc.perform(
				post(SymptomManagementApi.PATIENT_PATH)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(pJson))
	            //.andExpect(status().isOk())
	            .andReturn();
		
		String patient = result0.getResponse().getContentAsString();
		
		// Send a request that should invoke getAllPatients()
		// and check that the Patient object that we added above (as JSON)
		// is in the list of returned patients
		// TODO:  Need to check the object without the id value
		MvcResult result1 = mockMvc.perform(
				get(SymptomManagementApi.PATIENT_PATH))
	            .andExpect(status().isOk())
	            //.andExpect(content().string(containsString(pJson)))
	            .andReturn();
		
		// ditto for doctors
		MvcResult result2 = mockMvc.perform(
				post(SymptomManagementApi.PHYSICIAN_PATH)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(drJson))
	           // .andExpect(status().isOk())
	            .andReturn();
		
		MvcResult result3 = mockMvc.perform(
				get(SymptomManagementApi.PHYSICIAN_PATH))
	            .andExpect(status().isOk())
	            //.andExpect(content().string(containsString(drJson)))
	            .andReturn();
		
		// ditto for medications
		MvcResult result4 = mockMvc.perform(
				post(SymptomManagementApi.MEDICATION_PATH)
				.contentType(MediaType.APPLICATION_JSON)
	            .content(medJson))
	           // .andExpect(status().isOk())
	            .andReturn();
		
		MvcResult result5 = mockMvc.perform(
				get(SymptomManagementApi.MEDICATION_PATH))
	            .andExpect(status().isOk())
	            //.andExpect(content().string(containsString(medJson)))
	            .andReturn();		
	}

}
