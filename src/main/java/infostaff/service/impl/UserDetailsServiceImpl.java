package infostaff.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import infostaff.entity.TblUserEntity;
import infostaff.repository.TblUserRepository;
import infostaff.repository.TblUserRoleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private TblUserRepository tblUserRepo;
 
    @Autowired
    private TblUserRoleRepository tblUserRoleRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		TblUserEntity userEntity = tblUserRepo.findByUserName(username);
		
		if(userEntity == null) {
			log.error("User not found: " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
			
		log.info("Found user: " + username);
		List<String> roleNames = tblUserRoleRepo.getRoleNames(userEntity.getUserName());
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(userEntity.getUserName(), //
        		userEntity.getEncrytedPassword(), grantList);
 
        return userDetails;
	}

}
