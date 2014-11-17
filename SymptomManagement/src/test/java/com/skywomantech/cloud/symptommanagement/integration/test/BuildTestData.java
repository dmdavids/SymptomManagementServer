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

public class BuildTestData {

	/*
	 * private final String TEST_URL = "http://localhost:8080";
	 * 
	 * private SymptomManagementApi smController = new RestAdapter.Builder()
	 * .setEndpoint(TEST_URL).setLogLevel(LogLevel.FULL).build()
	 * .create(SymptomManagementApi.class);
	 */

	public static final String CLIENT_ID = "mobile";
	public final static String SERVER_ADDRESS = "https://192.168.0.34:8443";

	SymptomManagementApi smController = new SecuredRestBuilder()
			.setLoginEndpoint(SERVER_ADDRESS + SymptomManagementApi.TOKEN_PATH)
			.setUsername("admin").setPassword("pass").setClientId(CLIENT_ID)
			.setClient(new ApacheClient(new EasyHttpClient()))
			.setEndpoint(SERVER_ADDRESS).setLogLevel(RestAdapter.LogLevel.FULL)
			.build().create(SymptomManagementApi.class);

	private Patient harryPotter = TestData.randomPatient("Harry", "Potter",
			"4/14/1965");
	private Patient hanSolo = TestData.randomPatient("Han", "Solo",
			"12/21/1933");
	private Patient bellaSwan = TestData.randomPatient("Bella", "Swan",
			"5/30/1995");

	private Physician martyMcFly = TestData.randomPhysician("Marty", "McFly");
	private Physician maryPoppins = TestData.randomPhysician("Mary", "Poppins");
	private Physician drWho = TestData.randomPhysician("Dr", "Who");

	private Medication oxycontin = TestData.randomMedication("OxyContin");
	private Medication lortab = TestData.randomMedication("Lortab");
	private Medication aspirin = TestData.randomMedication("Aspirin");

	@Test
	public void createDatabaseForTesting() throws Exception {

		try {
			harryPotter = smController.addPatient(harryPotter);
			assertNotNull(harryPotter);

			hanSolo = smController.addPatient(hanSolo);
			assertNotNull(hanSolo);

			bellaSwan = smController.addPatient(bellaSwan);
			assertNotNull(bellaSwan);

			martyMcFly = smController.addPhysician(martyMcFly);
			assertNotNull(martyMcFly);

			maryPoppins = smController.addPhysician(maryPoppins);
			assertNotNull(maryPoppins);

			drWho = smController.addPhysician(drWho);
			assertNotNull(drWho);

			oxycontin = smController.addMedication(oxycontin);
			assertNotNull(oxycontin);

			lortab = smController.addMedication(lortab);
			assertNotNull(lortab);

			aspirin = smController.addMedication(aspirin);

			harryPotter = TestData.addPhysicianToPatient(maryPoppins,
					harryPotter);
			harryPotter = TestData.addPhysicianToPatient(drWho, harryPotter);
			bellaSwan = TestData.addPhysicianToPatient(maryPoppins, bellaSwan);
			hanSolo = TestData.addPhysicianToPatient(martyMcFly, hanSolo);
			hanSolo = TestData.addPhysicianToPatient(drWho, hanSolo);

			harryPotter = TestData.addPrescriptionToPatient(oxycontin,
					harryPotter);
			TestData.addPrescriptionToPatient(lortab, harryPotter);
			TestData.addPrescriptionToPatient(oxycontin, hanSolo);
			TestData.addPrescriptionToPatient(aspirin, bellaSwan);

			for (int i = 0; i < 50; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						harryPotter);
				for (Medication med : harryPotter.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							harryPotter);
			}
			harryPotter = smController.updatePatient(harryPotter.getId(),
					harryPotter);

			for (int i = 0; i < 50; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(), hanSolo);
				for (Medication med : hanSolo.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							hanSolo);
			}
			hanSolo = smController.updatePatient(hanSolo.getId(), hanSolo);

			for (int i = 0; i < 50; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						bellaSwan);
				for (Medication med : bellaSwan.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							bellaSwan);
			}
			bellaSwan = smController
					.updatePatient(bellaSwan.getId(), bellaSwan);
		} catch (Exception e) {
			// this will have problems after it has been run once.. not sure why
			// it is being run?
		}

	}

}
