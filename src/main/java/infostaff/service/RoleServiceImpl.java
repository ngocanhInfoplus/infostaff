package infostaff.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import infostaff.common.CommonFunc;
import infostaff.common.CommonParam;
import infostaff.entity.TblRoleEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.RoleMapping;
import infostaff.model.ResponseModel;
import infostaff.model.RoleModel;
import infostaff.repository.TblRoleRepository;
import infostaff.validation.RoleValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	TblRoleRepository roleRepo;

	@Override
	public List<RoleModel> getAllRole() {

		List<RoleModel> models = null;
		List<TblRoleEntity> entities = roleRepo.findAll();

		if (!entities.isEmpty()) {

			models = new ArrayList<RoleModel>();
			RoleModel model = new RoleModel();
			RoleMapping roleMapping = new RoleMapping();

			for (TblRoleEntity entity : entities) {

				model = roleMapping.entityToModel(entity);

				if (model != null)
					models.add(model);
			}
		}

		return models;
	}
	
	@Override
	public RoleModel getRole(Long roleId) {
		
		Optional<TblRoleEntity> entities = roleRepo.findById(roleId);
		
		if (entities.isPresent()) {
			
			TblRoleEntity entity = entities.get();
			return new RoleMapping().entityToModel(entity);
		}

		return new RoleModel();
	}

	@Override
//	public ResponseModel insertRole(RoleModel model, User loginedUser) {
	public ResponseEntity<RoleModel> insertRole(RoleModel model) throws ResourceNotFoundException{
		
		if (model != null) {

			RoleValidation validation = new RoleValidation();

			if (validation.validate(model)) {
				
				TblRoleEntity entity = new RoleMapping().modelToEntity(model);
				
				final RoleModel insertedRole = new RoleMapping().entityToModel(roleRepo.save(entity));
				return ResponseEntity.ok(insertedRole);
			} else {
				throw new ResourceNotFoundException("Validation error");
			}
		} else {
			throw new ResourceNotFoundException("Role information is empty ");
		}
		
	}

	@Override
	public ResponseEntity<RoleModel> updateRole(Long roleId, RoleModel model) 
			throws ResourceNotFoundException {

		if (roleId != null) {

			RoleValidation validation = new RoleValidation();

			if (validation.validate(model)) {

				TblRoleEntity roleEntity = roleRepo.findById(roleId)
						.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));

				roleEntity.setRoleName(model.getRoleName());

				final RoleModel updatedRole = new RoleMapping().entityToModel(roleRepo.save(roleEntity));
				return ResponseEntity.ok(updatedRole);
			} else {
				throw new ResourceNotFoundException("Validation error");
			}
		} else {
			throw new ResourceNotFoundException("Role id is empty ");
		}
	}

	@Override
	// public Map<String, Boolean> deleteRole(Long roleId) throws
	// ResourceNotFoundException {
	public RoleModel deleteRole(Long roleId) throws ResourceNotFoundException {
		if (roleId != null) {

			TblRoleEntity roleEntity = roleRepo.findById(roleId)
					.orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + roleId));
			roleRepo.deleteById(roleId);

			return new RoleMapping().entityToModel(roleEntity);
		} else {
			throw new ResourceNotFoundException("Role id is empty ");
		}
	}

}
