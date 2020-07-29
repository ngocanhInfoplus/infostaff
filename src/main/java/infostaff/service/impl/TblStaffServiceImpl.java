package infostaff.service.impl;

import infostaff.entity.TblStaffEntity;
import infostaff.exception.ResourceNotFoundException;
import infostaff.model.mapper.LeavingMapper;
import infostaff.model.mapper.StaffMapper;
import infostaff.model.request.AddStaffRequest;
import infostaff.repository.TblStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TblStaffServiceImpl {

    @Autowired
    TblStaffRepository tblStaffRepository;

    @Autowired
    StaffMapper staffMapper;

    public Long addStaff(AddStaffRequest addStaffRequest) {
        TblStaffEntity tblStaffEntity = staffMapper.addRequestToEntity(addStaffRequest);
        return tblStaffRepository.save(tblStaffEntity).getStaffId();
    }

    public TblStaffEntity getStaffById(Long id) throws ResourceNotFoundException {
        return tblStaffRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Staff not found - " + id));
    }


}
