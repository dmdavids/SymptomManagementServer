package org.skywomantech.cloud.symptommanagement.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.skywomantech.cloud.symptommanagement.controller.SymptomManagementService;
import com.skywomantech.cloud.symptommanagement.repository.UserCredential;
import com.skywomantech.cloud.symptommanagement.repository.UserCredential.UserRole;
import com.skywomantech.cloud.symptommanagement.repository.UserCredentialRepository;

public class SymptomManagmentUserDetailsManager implements UserDetailsService {

	static final Logger LOG = LoggerFactory
			.getLogger(SymptomManagmentUserDetailsManager.class);
	
	public static final String admin_username = "admin";
	public static final String admin_password = "pass";
	
	@Autowired
	private UserCredentialRepository credentials;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		if (username == null || username.isEmpty()) 
			throw new UsernameNotFoundException("Invalid user name.  User name is null or empty");
		
		LOG.debug("USERNAME is : " + username);
		
		Collection<UserCredential> userCredentials = null;
		
		// Get the credentials
		if (username.toLowerCase().contentEquals("admin")) {
			// hard-coded admin stuff
			userCredentials = new HashSet<UserCredential>();
			UserCredential adminCredential = new UserCredential();
			adminCredential.setId(null);
			adminCredential.setUserName(admin_username);
			adminCredential.setPassword(admin_password);
			adminCredential.setUserType(UserRole.ADMIN);
			userCredentials.add(adminCredential);
		} else {
			// try to find the username in the patients and physicians credential repository
			// TODO: try - catch this for NullPointerException?
			LOG.debug("Finding Credential by USER NAME : " + username);
			try {
				userCredentials = credentials.findByUserName(username);
			} catch (Exception e) {
				LOG.debug("Unable to find Credentials for " + username);
			}
		}
		
		// validate the credentials
		if(userCredentials == null || userCredentials.size() <= 0) {
			throw new UsernameNotFoundException("User details not found with this username: " 
												+ username);  
		}
		if (userCredentials.size() > 1) {
			throw new UsernameNotFoundException("ERROR Too Many User details found with this username: " 
												+ username + " It should be unique.");  
		}
		
		// find the authorities related to the user role
		UserCredential[] creds = userCredentials.toArray(new UserCredential[userCredentials.size()]);
		List<String> authList = getAuthorities(creds[0].getUserType()); 
		
		LOG.debug("Creating userdetails for : " + username + " password " + creds[0].getPassword());
		return User.create(username, creds[0].getPassword(), authList.toArray(new String[authList.size()]));
	}
	
	
	private List<String> getAuthorities(UserCredential.UserRole role) {
		LOG.debug("Setting up the User Authorities list for User Role of " + role);
		
		if (role == null || role == UserCredential.UserRole.NOT_ASSIGNED)
			return null;
		
		List<String> authList = new ArrayList<String>();
		switch (role) {
			case ADMIN: {
				authList.add("ROLE_ADMIN");
				authList.add("ROLE_PATIENT");
				authList.add("ROLE_PHYSICIAN");
				break;
			}
			case PATIENT: {
				authList.add("ROLE_PATIENT");
				break;
			}
			case PHYSICIAN: {
				authList.add("ROLE_PHYSICIAN");
				break;
			}
			default:
				return null;
		}
		return authList;
	}

}
