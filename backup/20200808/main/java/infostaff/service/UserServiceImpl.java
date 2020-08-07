package infostaff.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import infostaff.common.CommonParam;
import infostaff.entity.TblUserEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.UserMapping;
import infostaff.model.UserModel;
import infostaff.repository.TblUserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	TblUserRepository repo;

	@Autowired
	UserMapping mapping;

	@Value("${password.default}")
	String DEFAULT_PWD;

	@Override
	public UserModel create(User user, UserModel model) throws ResourceNotFoundException {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		TblUserEntity entity = new TblUserEntity();
		entity.setUserName(model.getUserName());
		entity.setEnabled(true);
		
		
		entity.setEncrytedPassword(bCryptPasswordEncoder.encode(DEFAULT_PWD));
		entity.setCreatedUser(user.getUsername());
		entity.setCreatedDate(new Date());
		
		try {
			TblUserEntity newEntity = repo.save(entity);
			return mapping.entityToModel(newEntity);
		}catch(Exception ex) {
			log.info("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}

	}

	@Override
	public UserModel update(User user, String username, UserModel model) throws ResourceNotFoundException {

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(model.getPassword()))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		TblUserEntity newEntity = mapping.modelToEntity(model);

		if (newEntity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		// find old data
		TblUserEntity oldEntity = repo.findByUserName(username);

		if (oldEntity == null)
			throw new ResourceNotFoundException("The user not found for this username :: " + username);

		try {
			// setup update
			newEntity.setEnabled(true);
			newEntity.setCreatedUser(oldEntity.getCreatedUser());
			newEntity.setCreatedDate(oldEntity.getCreatedDate());

			final UserModel updatedModel = mapping.entityToModel(repo.save(newEntity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public UserModel changePassword(User user, String username, UserModel model) throws ResourceNotFoundException {

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(model.getPassword()))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// parsing data
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		model.setEncrytedPassword(bCryptPasswordEncoder.encode(model.getPassword()));
		TblUserEntity newEntity = mapping.modelToEntity(model);

		if (newEntity == null)
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);

		// find old data
		TblUserEntity oldEntity = repo.findByUserName(username);

		if (oldEntity == null)
			throw new ResourceNotFoundException("The user not found for this username :: " + username);

		try {
			// setup update
			newEntity.setEnabled(true);
			newEntity.setCreatedUser(oldEntity.getCreatedUser());
			newEntity.setCreatedDate(oldEntity.getCreatedDate());

			final UserModel updatedModel = mapping.entityToModel(repo.save(newEntity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public UserModel disableUser(User user, String username) throws ResourceNotFoundException {

		if (StringUtils.isEmpty(username))
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_VAL);

		// find old data
		TblUserEntity oldEntity = repo.findByUserName(username);

		if (oldEntity == null)
			throw new ResourceNotFoundException("The user not found for this username :: " + username);

		try {
			// setup update
			oldEntity.setEnabled(false);

			final UserModel updatedModel = mapping.entityToModel(repo.save(oldEntity));
			return updatedModel;

		} catch (Exception ex) {
			log.error("Error: " + ex.toString());
			throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
		}
	}

	@Override
	public UserModel getUser(User user) throws ResourceNotFoundException {
	
		// find old data
		TblUserEntity oldEntity = repo.findByUserName(user.getUsername());

		if (oldEntity == null)
			throw new ResourceNotFoundException("The user not found for this username :: " + user.getUsername());
		final UserModel updatedModel = mapping.entityToModel(oldEntity);
		
		return updatedModel;

	}

}
