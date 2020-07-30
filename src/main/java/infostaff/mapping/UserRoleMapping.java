package infostaff.mapping;

import infostaff.entity.TblRoleEntity;
import infostaff.entity.TblUserEntity;
import infostaff.entity.TblUserRoleEntity;
import infostaff.model.UserModel;
import infostaff.model.UserRoleModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRoleMapping {

    public UserRoleModel entityToModel(TblUserRoleEntity entity) {

        try {
            UserRoleModel model = new UserRoleModel();
            model.setId(entity.getId());
            model.setUserName(entity.getTblUserEntity().getUserName());
            model.setRoleId(entity.getTblRoleEntity().getRoleId());
            model.setCreatedUser(entity.getCreatedUser());
            model.setCreatedDate(entity.getCreatedDate());

            return model;
        } catch (Exception ex) {
            log.error("Parsing error: " + ex.toString());
        }
        return null;
    }

    public TblUserRoleEntity modelToEntity(UserRoleModel model) {

        try {
            TblUserRoleEntity entity = new TblUserRoleEntity();
            entity.setId(model.getId());
            TblUserEntity userEntity = new TblUserEntity();
            userEntity.setUserName(model.getUserName());
            entity.setTblUserEntity(userEntity);
            TblRoleEntity roleEntity = new TblRoleEntity();
            roleEntity.setRoleId(model.getRoleId());
            entity.setTblRoleEntity(roleEntity);
            entity.setCreatedUser(model.getCreatedUser());
            entity.setCreatedDate(model.getCreatedDate());

            return entity;
        } catch (Exception ex) {
            log.error("Parsing error: " + ex.toString());
        }
        return null;
    }
}
