package com.skywomantech.cloud.symptommanagement.integration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;
import com.skywomantech.cloud.symptommanagement.repository.Medication;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.Physician;
import com.skywomantech.cloud.symptommanagement.testdata.TestData;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
 * 
 * This integration test sends a POST request to the VideoServlet to add a new video 
 * and then sends a second GET request to check that the video showed up in the list
 * of videos. Actual network communication using HTTP is performed with this test.
 * 
 * The test requires that the VideoSvc be running first (see the directions in
 * the README.md file for how to launch the Application).
 * 
 * To run this test, right-click on it in Eclipse and select
 * "Run As"->"JUnit Test"
 * 
 * Pay attention to how this test that actually uses HTTP and the test that just
 * directly makes method calls on a VideoSvc object are essentially identical.
 * All that changes is the setup of the videoService variable. Yes, this could
 * be refactored to eliminate code duplication...but the goal was to show how
 * much Retrofit simplifies interaction with our service!
 * 
 * @author jules
 *
 */
public class SymptomManagementClientApiTest {

	private final String TEST_URL = "http://localhost:8080";

	private SymptomManagementApi smController = new RestAdapter.Builder()
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(SymptomManagementApi.class);

	private Patient p = TestData.randomPatient();
	private Physician dr = TestData.randomPhysician();
	private Medication med = TestData.randomMedication();
	
	/**
	 * This test creates a Video, adds the Video to the VideoSvc, and then
	 * checks that the Video is included in the list when getVideoList() is
	 * called.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testVideoAddAndList() throws Exception {
		
		Patient updatedPatient = smController.addPatient(p);
		assertNotNull(updatedPatient);

	    Collection<Patient> patients = smController.getAllPatients();
		assertTrue(patients.contains(updatedPatient));


		Physician dr2 = smController.addPhysician(dr);
		assertNotNull(dr2);
		
		Collection<Physician> drs = smController.getAllPhysicians();
		assertTrue(drs.contains(dr2));
	
		 Medication m2 = smController.addMedication(med);

		 Collection<Medication> meds = smController.getAllMedications();
		assertTrue(meds.contains(m2));		
		
	}

}
