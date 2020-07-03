package infostaff.mapping;

import infostaff.entity.TblRoleEntity;
import infostaff.model.RoleModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleMapping {

	public RoleModel entityToModel(TblRoleEntity entity) {

		try {
			RoleModel model = new RoleModel();

			model.setRoleId(entity.getRoleId());
			model.setRoleName(entity.getRoleName());

			return model;
		} catch (Exception ex) {
			log.error("Parsing error: " + ex.toString());
		}
		return null;
	}

	public TblRoleEntity modelToEntity(RoleModel model) {

		try {
			TblRoleEntity entity = new TblRoleEntity();

			entity.setRoleId(model.getRoleId());
			entity.setRoleName(model.getRoleName());

			return entity;
		} catch (Exception ex) {
			log.error("Parsing error: " + ex.toString());
		}
		return null;
	}
}
