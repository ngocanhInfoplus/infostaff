package infostaff.model.mapper;

import infostaff.entity.TblStaffEntity;
import infostaff.model.request.AddStaffRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    TblStaffEntity addRequestToEntity(AddStaffRequest addStaffRequest);

}
