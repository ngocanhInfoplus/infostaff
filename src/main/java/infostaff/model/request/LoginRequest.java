package infostaff.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by https://github.com/kwanpham
 */
@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
