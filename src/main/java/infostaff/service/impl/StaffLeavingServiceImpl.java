package infostaff.service.impl;

import infostaff.entity.TblStaffLeavingEntity;
import infostaff.exception.BadRequestException;
import infostaff.mapping.StaffLeavingMapping;
import infostaff.model.StaffLeavingModel;
import infostaff.repository.TblStaffLeavingRepository;
import infostaff.service.IStaffLeavingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class StaffLeavingServiceImpl implements IStaffLeavingService {

    @Autowired
    TblStaffLeavingRepository repo;

    @Override
    public StaffLeavingModel CreateLeaving(StaffLeavingModel model, User user)
            throws BadRequestException {

        StaffLeavingMapping mapping = new StaffLeavingMapping();

        TblStaffLeavingEntity entity = mapping.modelToEntity(model);

        if(entity == null)
            throw new BadRequestException("Parsing error");

        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());
        final StaffLeavingModel insertedModel = mapping.entityToModel(repo.save(entity));

        return insertedModel;

    }
}
