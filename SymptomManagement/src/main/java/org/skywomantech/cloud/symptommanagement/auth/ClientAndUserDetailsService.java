/* 
 **
 ** Copyright 2014, Jules White
 **
 ** 
 */
package org.skywomantech.cloud.symptommanagement.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * A class that combines a UserDetailsService and ClientDetailsService
 * into a single object.
 * 
 * @author jules
 *
 */
@Service
public class ClientAndUserDetailsService implements UserDetailsService,
		ClientDetailsService {

	private final ClientDetailsService mClients;

	private final UserDetailsService mUsers;
	
	private final ClientDetailsUserDetailsService mClientDetailsWrapper;

	public ClientAndUserDetailsService(ClientDetailsService clients,
			UserDetailsService users) {
		super();
		mClients = clients;
		mUsers = users;
		mClientDetailsWrapper = new ClientDetailsUserDetailsService(mClients);
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId)
			throws ClientRegistrationException {
		return mClients.loadClientByClientId(clientId);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails user = null;
		try{
			user = mUsers.loadUserByUsername(username);
		}catch(UsernameNotFoundException e){
			user = mClientDetailsWrapper.loadUserByUsername(username);
		}
		return user;
	}

}
