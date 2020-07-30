package infostaff.service.impl;

import infostaff.common.CommonParam;
import infostaff.entity.TblUserEntity;
import infostaff.entity.TblUserRoleEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.UserMapping;
import infostaff.mapping.UserRoleMapping;
import infostaff.model.UserModel;
import infostaff.model.UserRoleModel;
import infostaff.repository.TblRoleRepository;
import infostaff.repository.TblUserRepository;
import infostaff.repository.TblUserRoleRepository;
import infostaff.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    TblUserRepository userPepo;

    @Autowired
    TblUserRoleRepository userRolePepo;

    @Autowired
    TblRoleRepository roleRepo;

    @Override
    public List<UserRoleModel> getAllUserRole(User user) {
        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        List<UserRoleModel> models = null;
        List<TblUserRoleEntity> entities = userRolePepo.findAll();

        if (!entities.isEmpty()) {

            models = new ArrayList<UserRoleModel>();
            UserRoleModel model = new UserRoleModel();
            UserRoleMapping userRoleMapping = new UserRoleMapping();

            for (TblUserRoleEntity entity : entities) {

                model = userRoleMapping.entityToModel(entity);

                if (model != null)
                    models.add(model);
            }
        }

        return models;
    }

    @Override
    public UserRoleModel getUserRole(Long id, User user) throws ResourceNotFoundException {

        return new UserRoleModel();
    }

    @Override
    public UserRoleModel insertUserRole(UserRoleModel model, User user) throws ResourceNotFoundException {
        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        UserRoleMapping mapping = new UserRoleMapping();
        TblUserRoleEntity entity = mapping.modelToEntity(model);
        if(entity == null)
            throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);
        if(userRolePepo.existsByTblUserEntityAndTblRoleEntity(entity.getTblUserEntity(),entity.getTblRoleEntity()))
            throw new ResourceNotFoundException("this UserRole already exists");
        if(!userPepo.existsByUserName(entity.getTblUserEntity().getUserName()))
            throw new ResourceNotFoundException("UserID not exists");
        if(!roleRepo.existsById(entity.getTblRoleEntity().getRoleId()))
            throw new ResourceNotFoundException("RoleID not exists");
        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());

        final UserRoleModel inserted = new UserRoleMapping().entityToModel(userRolePepo.save(entity));
        return inserted;
    }

    @Override
    public UserRoleModel updateUserRole(Long id, UserRoleModel model, User user) throws ResourceNotFoundException {
        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        TblUserRoleEntity entity = new UserRoleMapping().modelToEntity(model);
        UserRoleMapping mapping = new UserRoleMapping();
        if(!userRolePepo.existsById(id))
            throw new ResourceNotFoundException("UserRole not exists");
        if(userRolePepo.existsByTblUserEntityAndTblRoleEntity(entity.getTblUserEntity(),entity.getTblRoleEntity()))
            throw new ResourceNotFoundException("this UserRole already exists");
        if(!userPepo.existsByUserName(model.getUserName()))
            throw new ResourceNotFoundException("UserID not exists");
        if(!roleRepo.existsById(model.getRoleId()))
            throw new ResourceNotFoundException("RoleID not exists");
        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());

        final UserRoleModel updated = mapping.entityToModel(userRolePepo.save(entity));
        return updated;
    }

    @Override
    public UserRoleModel deleteUserRole(Long id, User user) throws ResourceNotFoundException {
        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        TblUserRoleEntity entity = userRolePepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(CommonParam.ERR_MSG_NOT_FOUND_ID + id));
        try
        {
            userRolePepo.deleteById(id);
        } catch (Exception ex) {
            log.error("Error: " + ex.toString());
            throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
        }

        return new UserRoleMapping().entityToModel(entity);
    }
}
