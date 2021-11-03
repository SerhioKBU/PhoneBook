package dto.addition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusAddResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty("status")
    private String status;
    @JsonProperty("error")
    private String error;
}
