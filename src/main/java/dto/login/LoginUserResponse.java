package dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginUserResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("token")
    private String message;
}