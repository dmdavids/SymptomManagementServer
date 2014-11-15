package com.skywomantech.cloud.symptommanagement.integration.test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.skywomantech.app.symptommanagement.client.oauth.SecuredRestBuilder;
import com.skywomantech.app.symptommanagement.client.oauth.unsafe.EasyHttpClient;
import com.skywomantech.cloud.symptommanagement.client.SymptomManagementApi;
import com.skywomantech.cloud.symptommanagement.repository.Medication;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.Physician;
import com.skywomantech.cloud.symptommanagement.testdata.TestData;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.client.ApacheClient;


public class SymptomManagementClientApiTest {

/*	private final String TEST_URL = "http://localhost:8080";

	private SymptomManagementApi smController = new RestAdapter.Builder()
			.setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
			.create(SymptomManagementApi.class);*/
	
    public static final String CLIENT_ID = "mobile";
    public final static String SERVER_ADDRESS = "https://192.168.0.34:8443";
    
	SymptomManagementApi smController = new SecuredRestBuilder()
    .setLoginEndpoint(SERVER_ADDRESS + SymptomManagementApi.TOKEN_PATH)
    .setUsername("admin")
    .setPassword("pass")
    .setClientId(CLIENT_ID)
    .setClient(new ApacheClient(new EasyHttpClient()))
    .setEndpoint(SERVER_ADDRESS).setLogLevel(RestAdapter.LogLevel.FULL).build()
    .create(SymptomManagementApi.class);

	private Patient p = TestData.randomPatient("Donald", "Duck", "12/11/1944");
	private Physician dr = TestData.randomPhysician("Minnie", "Mouse");
	private Medication med = TestData.randomMedication("hugs");
	
	@Test
	public void testUsersAddAndList() throws Exception {
		
		Patient updatedPatient = smController.addPatient(p);
		assertNotNull(updatedPatient);

	    Collection<Patient> patients = smController.getPatientList();
		assertTrue(patients.contains(updatedPatient));


		Physician dr2 = smController.addPhysician(dr);
		assertNotNull(dr2);
		
		Collection<Physician> drs = smController.getPhysicianList();
		assertTrue(drs.contains(dr2));
	
		 Medication m2 = smController.addMedication(med);

		 Collection<Medication> meds = smController.getMedicationList();
		assertTrue(meds.contains(m2));		
		
	}

}
