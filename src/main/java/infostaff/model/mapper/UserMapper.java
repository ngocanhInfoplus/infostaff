package infostaff.model.mapper;

import infostaff.entity.TblUserEntity;
import infostaff.model.request.UserRequest;
import infostaff.model.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    TblUserEntity requestToEntity(UserRequest userRequest);
    UserResponse entityToResponse(TblUserEntity tblUserEntity);
}
