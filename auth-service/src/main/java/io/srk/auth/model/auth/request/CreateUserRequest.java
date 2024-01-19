package io.srk.auth.model.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@Schema(description = "Create user request")
public class CreateUserRequest {

    @NotBlank(message = "Username can not be blank")
    @Schema(description = "Username (can be used for login)")
    private String username;

    @Schema(description = "User first name")
    private String firstName;

    @Schema(description = "User last name")
    private String lastName;

    @NotBlank(message = "Email can not be blank")
    @Schema(description = "User email (can be used for login)")
    private String email;

    @ToString.Exclude
    @NotBlank(message = "Password can not be blank")
    @Schema(description = "User password")
    private String password;
}
