package infostaff.model.response;

import infostaff.entity.TblRoleEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Data
public class UserResponse {
    @NotBlank
    private String userName;
    @NotBlank
    private String encrytedPassword;
    @NotBlank
    private boolean enabled;

    private String createdUser;

    private Date createdDate;

    private String status;

    //    private List<String> roleNames;
    private Collection<TblRoleEntity> roles;

    private String token;
}
