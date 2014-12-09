package com.skywomantech.cloud.symptommanagement.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skywomantech.cloud.symptommanagement.controller.SymptomManagementController;
import com.skywomantech.cloud.symptommanagement.repository.Medication;
import com.skywomantech.cloud.symptommanagement.repository.MedicationRepository;
import com.skywomantech.cloud.symptommanagement.repository.Patient;
import com.skywomantech.cloud.symptommanagement.repository.PatientRepository;
import com.skywomantech.cloud.symptommanagement.repository.Physician;
import com.skywomantech.cloud.symptommanagement.repository.PhysicianRepository;
import com.skywomantech.cloud.symptommanagement.testdata.TestData;

/**
 * 
 * This test directly invokes the methods on the controller to test them. The test
 * uses the Mockito library to inject a mock repository through dependency
 * injection into the controller object.
 * 
 * To run this test, right-click on it in Eclipse and select
 * "Run As"->"JUnit Test"
 * 
 * Pay attention to how this test that actually uses HTTP and the test that just
 * directly makes method calls on a controller object are essentially identical.
 * All that changes is the setup of the controller variable. Yes, this could
 * be refactored to eliminate code duplication...but the goal was to show how
 * much Retrofit simplifies interaction with our service!
 * 
 * @author jules
 *
 */
public class SymptomManagementControllerTest {

	@Mock
	private PatientRepository patientRepository;
	
	@Mock 
	private PhysicianRepository physicianRepository;
	
	@Mock 
	private MedicationRepository medicationRepository;
	
	@InjectMocks
	private SymptomManagementController smController;

	private Patient p = TestData.randomPatient("Donald", "Duck", "12/11/1944");
	private Physician dr = TestData.randomPhysician("Minnie", "Mouse");
	private Medication med = TestData.randomMedication("hugs");

	@Before
	public void setUp() {
		// Process mock annotations and inject the mock VideoRepository
		// into the VideoSvc object
		MockitoAnnotations.initMocks(this);

		// always return the random object we create for findall
		when(patientRepository.findAll()).thenReturn(Arrays.asList(p));
		when(physicianRepository.findAll()).thenReturn(Arrays.asList(dr));
		when(medicationRepository.findAll()).thenReturn(Arrays.asList(med));
	}
	
	
	// Yes, this test doesn't do much because the Controller is
	// essentially delegating to repository. The goal is to
	// provide a simple example of testing controllers with mock
	// objects and dependency injection.
	@Test
	public void testPatientDrMeds() throws Exception {

		Patient p2 = smController.addPatient(p);
		//assertNotNull(p2);

		Collection<Patient> patients = smController.getPatientList();
		//assertTrue(patients.contains(p2));
		
		Physician dr2 = smController.addPhysician(dr);
		//assertNotNull(dr2);

		Collection<Physician> drs = smController.getPhysicianList();
		//assertTrue(drs.contains(dr2));
		
		Medication m2 =  smController.addMedication(med);
		//assertNotNull(m2);

		Collection<Medication> meds = smController.getMedicationList();
		//assertTrue(meds.contains(m2));
	}

}
