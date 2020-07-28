package infostaff.service;

import infostaff.entity.TblStaffLeavingEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.mapping.StaffLeavingMapping;
import infostaff.model.StaffLeavingModel;
import infostaff.repository.TblStaffLeavingRepository;
import infostaff.validation.StaffLeavingValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class StaffLeavingServiceImpl implements IStaffLeavingService{

    @Autowired
    TblStaffLeavingRepository repo;

    @Override
    public StaffLeavingModel CreateLeaving(StaffLeavingModel model, User user)
            throws ResourceNotFoundException {

        StaffLeavingValidation validation = new StaffLeavingValidation();
        StaffLeavingMapping mapping = new StaffLeavingMapping();

        if(!validation.validate(model))
            throw new ResourceNotFoundException("Validation error");

        TblStaffLeavingEntity entity = mapping.modelToEntity(model);

        if(entity == null)
            throw new ResourceNotFoundException("Parsing error");



        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());
        final StaffLeavingModel insertedModel = mapping.entityToModel(repo.save(entity));

        return insertedModel;

    }
}
