package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("error")
    private String error;
}
