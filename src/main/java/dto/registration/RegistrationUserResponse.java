package dto.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistrationUserResponse {
    @JsonProperty("error")
    private String error;

    @JsonProperty("status")
    private String status;
}
