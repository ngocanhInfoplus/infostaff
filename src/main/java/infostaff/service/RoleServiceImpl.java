package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import infostaff.entity.TblRoleEntity;
import infostaff.mapping.RoleMapping;
import infostaff.model.ResponseModel;
import infostaff.model.RoleModel;
import infostaff.repository.TblRoleRepository;
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
	public ResponseModel insertRole(RoleModel model) {

		RoleMapping roleMapping = new RoleMapping();
		TblRoleEntity entity = roleMapping.modelToEntity(model);

		if (entity != null) {

			try {
				TblRoleEntity result = roleRepo.save(entity);
				
				if(result != null) 
					return createResponseModelByCode("00");
				else
					return createResponseModelByCode("10");
				
			} catch (Exception ex) {
				log.error("Insert Role error: " + ex.toString());
				return createResponseModelByCode("10");
			}
		}else {
			return createResponseModelByCode("13");
		}
	}

	private ResponseModel createResponseModelByCode(String code) {

		switch (code) {
		case "00":
			return new ResponseModel(code, "Execute successed");
		case "10":
			return new ResponseModel(code, "Execute database failed");
		case "11":
			return new ResponseModel(code, "Duplicated data");
		case "12":
			return new ResponseModel(code, "No data found");
		case "13":
			return new ResponseModel(code, "Convert data error");
		case "14":
			return new ResponseModel(code, "Validation error");
		default:
			return new ResponseModel();
		}
	}

	@Override
	public ResponseModel updateRole(RoleModel model) {
		RoleMapping roleMapping = new RoleMapping();
		TblRoleEntity entity = roleMapping.modelToEntity(model);

		if (entity != null) {

			try {
				TblRoleEntity result = roleRepo.save(entity);
				
				if(result != null) 
					return createResponseModelByCode("00");
				else
					return createResponseModelByCode("10");
				
			} catch (Exception ex) {
				log.error("Update Role error: " + ex.toString());
				return createResponseModelByCode("10");
			}
		}else {
			return createResponseModelByCode("13");
		}
	}

}
