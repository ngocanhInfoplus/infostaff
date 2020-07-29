package infostaff.service.impl;

import infostaff.common.CommonParam;
import infostaff.entity.TblRoleEntity;
import infostaff.entity.TblUserEntity;
import infostaff.entity.TblUserRoleEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.RoleMapping;
import infostaff.mapping.UserMapping;
import infostaff.model.RoleModel;
import infostaff.model.UserModel;
import infostaff.repository.TblUserRepository;
import infostaff.repository.TblUserRoleRepository;
import infostaff.service.IUserService;
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
public class UserServiceImpl implements IUserService {

    @Autowired
    TblUserRepository userPepo;

    @Autowired
    TblUserRoleRepository userRolePepo;

    @Override
    public List<UserModel> getAllUser(User user) {

        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        List<UserModel> models = null;
        List<TblUserEntity> entities = userPepo.findAll();

        if (!entities.isEmpty()) {

            models = new ArrayList<UserModel>();
            UserModel model = new UserModel();
            UserMapping userMapping = new UserMapping();

            for (TblUserEntity entity : entities) {

                model = userMapping.entityToModel(entity);

                if (model != null)
                    models.add(model);
            }
        }

        return models;
    }

    @Override
    public UserModel getUser(String id, User user) throws ResourceNotFoundException {

        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        TblUserEntity entity = userPepo.findByUserName(id);
        if (entity == null) {
            throw new ResourceNotFoundException(CommonParam.ERR_MSG_NOT_FOUND_ID);
        }

        if (entity.isEnabled())
            return new UserMapping().entityToModel(entity);
        return new UserModel();
    }

    @Override
    public UserModel insertUser(UserModel model, User user) throws ResourceNotFoundException {

        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        UserMapping mapping = new UserMapping();
        TblUserEntity entity = mapping.modelToEntity(model);
        if(entity == null)
            throw new ResourceNotFoundException(CommonParam.ERR_MSG_PARSE);
        if(userPepo.existsByUserName(entity.getUserName()))
            throw new ResourceNotFoundException("User is exists");
        String password = entity.getEncrytedPassword();
        entity.setEncrytedPassword(new BCryptPasswordEncoder().encode(password));
        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());

        final UserModel inserted = new UserMapping().entityToModel(userPepo.save(entity));
        return inserted;
    }

    @Override
    public UserModel updateUser(String id, UserModel model, User user) throws ResourceNotFoundException {

        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        UserMapping mapping = new UserMapping();
        TblUserEntity entity = userPepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(CommonParam.ERR_MSG_NOT_FOUND_ID + id));
        String password = model.getEncrytedPassword();
        entity.setEncrytedPassword(new BCryptPasswordEncoder().encode(password));
        entity.setEnabled(model.isEnabled());
        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());

        final UserModel updated = mapping.entityToModel(userPepo.save(entity));
        return updated;
    }

    @Override
    public UserModel deleteUser(String id, User user) throws ResourceNotFoundException {

        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        TblUserEntity entity = userPepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(CommonParam.ERR_MSG_NOT_FOUND_ID + id));
        try
        {
            userRolePepo.deleteUserRoleByUserName(entity.getUserName());
            //throw new ResourceNotFoundException("Can't delete UserRole of this User");
            userPepo.deleteById(id);
        } catch (Exception ex) {
            log.error("Error: " + ex.toString());
            throw new ResourceNotFoundException(CommonParam.ERR_MSG_SYS);
        }

        return new UserMapping().entityToModel(entity);
    }


    @Override
    public UserModel enableUser(String id, UserModel model, User user) throws ResourceNotFoundException {

        if (user == null)
            return null;
        log.info("User name: " + user.getUsername());

        UserMapping mapping = new UserMapping();
        TblUserEntity entity = userPepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(CommonParam.ERR_MSG_NOT_FOUND_ID + id));
        entity.setEnabled(model.isEnabled());
        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());

        final UserModel userEnabled = mapping.entityToModel(userPepo.save(entity));
        return userEnabled;
    }


}
