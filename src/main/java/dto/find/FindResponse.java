package dto.find;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.Contact;

import java.util.List;

@Data
public class FindResponse{

    @JsonProperty("contacts")
    private List<Contact> contacts;

    @JsonProperty("status")
    private String status;
}
