package infostaff.controller;

import java.util.Iterator;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import infostaff.config.JwtTokenUtil;
import infostaff.model.JwtRequest;
import infostaff.model.UserModel;
import infostaff.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1.0/infostaff")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Autowired
	private UserDetailsServiceImpl jwtInMemoryUserDetailsService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		
		UserModel userModel = new UserModel();
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		userModel.setUserName(userDetails.getUsername());
		userModel.setEncrytedPassword(userDetails.getPassword());
		userModel.setToken(token);
		Iterator<GrantedAuthority> grantList = (Iterator<GrantedAuthority>) userDetails.getAuthorities().iterator();
		//List<GrantedAuthority> grantList = (List<GrantedAuthority>) userDetails.getAuthorities();
		if(grantList.hasNext()) {
			userModel.setRoleName(grantList.next().toString());
		}
		
		return ResponseEntity.ok(userModel);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
