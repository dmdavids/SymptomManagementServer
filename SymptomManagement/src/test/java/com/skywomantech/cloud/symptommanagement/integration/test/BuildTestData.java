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
	private Patient martyMcFly = TestData.randomPatient("Marty", "McFly",
			"03/15/1965");
	private Patient mickeyMouse = TestData.randomPatient("Mickey", "Mouse",
			"04/11/1947");
	private Patient minnieMouse = TestData.randomPatient("Minnie", "Mouse",
			"04/29/1948");
	private Patient donaldDuck = TestData.randomPatient("Donald", "Duck",
			"06/15/1957");
	private Patient daiseyDuck = TestData.randomPatient("Daisey", "Duck",
			"09/21/1977");
	
	private Physician maryPoppins = TestData.randomPhysician("Mary", "Poppins");
	private Physician drWho = TestData.randomPhysician("Dr", "Who");
	private Physician drJules = TestData.randomPhysician("Dr", "Jules");
	private Physician drAdam = TestData.randomPhysician("Dr", "Adam");
	private Physician drDoug = TestData.randomPhysician("Dr", "Doug");
	private Physician bonesMccoy = TestData.randomPhysician("Bones", "McCoy");

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

			martyMcFly = smController.addPatient(martyMcFly);
			assertNotNull(martyMcFly);
			
			mickeyMouse = smController.addPatient(mickeyMouse);
			assertNotNull(mickeyMouse);
			
			donaldDuck = smController.addPatient(donaldDuck);
			assertNotNull(donaldDuck);
			
			daiseyDuck = smController.addPatient(daiseyDuck);
			assertNotNull(daiseyDuck);
			
			minnieMouse = smController.addPatient(minnieMouse);
			assertNotNull(minnieMouse);

			maryPoppins = smController.addPhysician(maryPoppins);
			assertNotNull(maryPoppins);

			drWho = smController.addPhysician(drWho);
			assertNotNull(drWho);

			drJules = smController.addPhysician(drJules);
			assertNotNull(drJules);
			
			drAdam = smController.addPhysician(drAdam);
			assertNotNull(drAdam);
			
			drDoug = smController.addPhysician(drDoug);
			assertNotNull(drDoug);
			
			bonesMccoy = smController.addPhysician(bonesMccoy);
			assertNotNull(bonesMccoy);
			
			oxycontin = smController.addMedication(oxycontin);
			assertNotNull(oxycontin);

			lortab = smController.addMedication(lortab);
			assertNotNull(lortab);

			aspirin = smController.addMedication(aspirin);

			harryPotter = TestData.addPhysicianToPatient(maryPoppins, harryPotter);
			harryPotter = TestData.addPhysicianToPatient(drWho, harryPotter);
			bellaSwan = TestData.addPhysicianToPatient(maryPoppins, bellaSwan);
			hanSolo = TestData.addPhysicianToPatient(maryPoppins, hanSolo);
			hanSolo = TestData.addPhysicianToPatient(drWho, hanSolo);
			martyMcFly = TestData.addPhysicianToPatient(bonesMccoy, martyMcFly);
			martyMcFly = TestData.addPhysicianToPatient(drWho, martyMcFly);
			martyMcFly = TestData.addPhysicianToPatient(maryPoppins, martyMcFly);
			mickeyMouse = TestData.addPhysicianToPatient(maryPoppins, mickeyMouse);
			mickeyMouse = TestData.addPhysicianToPatient(bonesMccoy, mickeyMouse);
			mickeyMouse = TestData.addPhysicianToPatient(drWho, mickeyMouse);
			minnieMouse = TestData.addPhysicianToPatient(drJules, minnieMouse);
			daiseyDuck = TestData.addPhysicianToPatient(drAdam, daiseyDuck);
			donaldDuck = TestData.addPhysicianToPatient(drDoug, donaldDuck);

			harryPotter = TestData.addPrescriptionToPatient(oxycontin, harryPotter);
			harryPotter = TestData.addPrescriptionToPatient(lortab, harryPotter);
			hanSolo = TestData.addPrescriptionToPatient(oxycontin, hanSolo);
			bellaSwan = TestData.addPrescriptionToPatient(aspirin, bellaSwan);
			martyMcFly = TestData.addPrescriptionToPatient(lortab, martyMcFly);
			mickeyMouse = TestData.addPrescriptionToPatient(lortab, mickeyMouse);
			minnieMouse = TestData.addPrescriptionToPatient(lortab, minnieMouse);
			donaldDuck = TestData.addPrescriptionToPatient(lortab, donaldDuck);
			daiseyDuck = TestData.addPrescriptionToPatient(lortab, daiseyDuck);


			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						harryPotter);
				for (Medication med : harryPotter.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							harryPotter);
			}
			harryPotter = smController.updatePatient(harryPotter.getId(),
					harryPotter);
			TestData.resetRandomDate();

			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(), hanSolo);
				for (Medication med : hanSolo.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							hanSolo);
			}
			hanSolo = smController.updatePatient(hanSolo.getId(), hanSolo);
			TestData.resetRandomDate();

			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						bellaSwan);
				for (Medication med : bellaSwan.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							bellaSwan);
			}
			bellaSwan = smController
					.updatePatient(bellaSwan.getId(), bellaSwan);
			TestData.resetRandomDate();
			
			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						martyMcFly);
				for (Medication med : martyMcFly.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							martyMcFly);
			}
			martyMcFly = smController
					.updatePatient(martyMcFly.getId(), martyMcFly);
			TestData.resetRandomDate();
			
			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						minnieMouse);
				for (Medication med : minnieMouse.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							minnieMouse);
			}
			minnieMouse = smController
					.updatePatient(minnieMouse.getId(), minnieMouse);
			TestData.resetRandomDate();
			
			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						mickeyMouse);
				for (Medication med : mickeyMouse.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							mickeyMouse);
			}
			mickeyMouse = smController
					.updatePatient(mickeyMouse.getId(), mickeyMouse);
			TestData.resetRandomDate();
			
			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						donaldDuck);
				for (Medication med : donaldDuck.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							donaldDuck);
			}
			donaldDuck = smController
					.updatePatient(donaldDuck.getId(), donaldDuck);
			TestData.resetRandomDate();
			
			for (int i = 0; i < 70; i++) {
				TestData.addPainLogToPatient(TestData.randomPainLog(),
						daiseyDuck);
				for (Medication med : daiseyDuck.getPrescriptions())
					TestData.addMedLogToPatient(TestData.randomMedLog(med),
							daiseyDuck);
			}
			daiseyDuck = smController
					.updatePatient(daiseyDuck.getId(), daiseyDuck);
			TestData.resetRandomDate();
			
			
		} catch (Exception e) {
			// this will have problems after it has been run once.. 
		   // probably because they exist already in db
		}

	}

}
