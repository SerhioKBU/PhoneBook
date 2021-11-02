package dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginUserRequest {
    @JsonProperty("password")
    private String password;

    @JsonProperty("login")
    private String login;
}
