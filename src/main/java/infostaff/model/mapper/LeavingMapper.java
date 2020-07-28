package infostaff.model.mapper;

import infostaff.common.DateUtils;
import infostaff.entity.TblStaffLeavingEntity;
import infostaff.model.request.StaffLeavingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface LeavingMapper {

    @Mapping(source = "toDate" , target = "toDate" , qualifiedByName = "StringToDate")
    @Mapping(source = "fromDate", target = "fromDate", qualifiedByName = "StringToDate")
    TblStaffLeavingEntity requestToEntity(StaffLeavingRequest leavingRequest);

    @Named("StringToDate")
    default Date fromString(String date) {
        return DateUtils.convertStringToDate(date , "yyyy-MM-dd");
    }

}
