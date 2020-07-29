package infostaff.mapping;

import infostaff.entity.TblRoleEntity;
import infostaff.entity.TblUserEntity;
import infostaff.model.RoleModel;
import infostaff.model.UserModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMapping {

    public UserModel entityToModel(TblUserEntity entity) {

        try {
            UserModel model = new UserModel();

            model.setUserName(entity.getUserName());
            model.setEncrytedPassword(entity.getEncrytedPassword());
            model.setEnabled(entity.isEnabled());
            model.setCreatedUser(entity.getCreatedUser());
            model.setCreatedDate(entity.getCreatedDate());

            return model;
        } catch (Exception ex) {
            log.error("Parsing error: " + ex.toString());
        }
        return null;
    }

    public TblUserEntity modelToEntity(UserModel model) {

        try {
            TblUserEntity entity = new TblUserEntity();

            entity.setUserName(model.getUserName());
            entity.setEncrytedPassword(model.getEncrytedPassword());
            entity.setEnabled(model.isEnabled());
            entity.setCreatedUser(model.getCreatedUser());
            entity.setCreatedDate(model.getCreatedDate());

            return entity;
        } catch (Exception ex) {
            log.error("Parsing error: " + ex.toString());
        }
        return null;
    }
}
