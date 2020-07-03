package infostaff.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import infostaff.common.CommonFunc;
import infostaff.common.CommonParam;
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
	public ResponseModel insertRole(RoleModel model, User loginedUser) {

		RoleMapping roleMapping = new RoleMapping();
		TblRoleEntity entity = roleMapping.modelToEntity(model);
		String code = StringUtils.EMPTY;
		log.info("Login user: " + loginedUser.getUsername());
		
		if (entity != null) {

			try {
				
				TblRoleEntity result = roleRepo.save(entity);
				code = (result != null)? CommonParam.CODE_SUCCESS : CommonParam.CODE_FAILED;
				
			} catch (Exception ex) {
				code = CommonParam.CODE_FAILED;
				log.error("Insert Role error: " + ex.toString());
			}
		}else {
			code = CommonParam.CODE_CONVERT_ERROR;
		}
		
		return CommonFunc.createResponseModelByCode(code);
	}

	@Override
	public ResponseModel updateRole(RoleModel model, User loginedUser) {
		RoleMapping roleMapping = new RoleMapping();
		TblRoleEntity entity = roleMapping.modelToEntity(model);
		String code = StringUtils.EMPTY;
		
		if (entity != null) {

			try {
				
				TblRoleEntity result = roleRepo.save(entity);
				code = (result != null)? CommonParam.CODE_SUCCESS : CommonParam.CODE_FAILED;
				
			} catch (Exception ex) {
				code = CommonParam.CODE_FAILED;
				log.error("Update Role error: " + ex.toString());
			}
		}else {
			code = CommonParam.CODE_CONVERT_ERROR;
		}
		return CommonFunc.createResponseModelByCode(code);
	}

}
