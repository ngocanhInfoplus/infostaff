package infostaff.service.impl;

import infostaff.entity.TblStaffLeavingEntity;
import infostaff.exception.BadRequestException;
import infostaff.model.StaffLeavingModel;
import infostaff.model.mapper.LeavingMapper;
import infostaff.model.request.StaffLeavingRequest;
import infostaff.model.response.StaffLeavingResponse;
import infostaff.repository.TblStaffLeavingRepository;
import infostaff.service.IStaffLeavingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Period;
import java.util.Date;

@Slf4j
@Service
public class StaffLeavingServiceImpl implements IStaffLeavingService {

    @Autowired
    TblStaffLeavingRepository repo;

    @Autowired
    LeavingMapper leavingMapper;

    @Override
    public StaffLeavingResponse createLeaving(StaffLeavingRequest request, User user)
            throws BadRequestException {

        TblStaffLeavingEntity entity = leavingMapper.requestToEntity(request);

        if(entity == null)
            throw new BadRequestException("Parsing error");

        entity.setCreatedUser(user.getUsername());
        entity.setCreatedDate(new Date());
        entity.setApproveStatus("created");

        String timeOffType = entity.getTotalHour() >=5 ? "HALFTIME" : "FULLTIME" ;
        entity.setTimeOffType(timeOffType);

        repo.save(entity);

        StaffLeavingResponse response = new StaffLeavingResponse();
        response.setMessage("Add success");
        return response;

    }
}
