package models;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "person")
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class Contact implements Serializable {

    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private ContactType contactType;

    @JsonProperty("value")
    private String value;

    public Contact() {
    }

    @Override
    public String toString() {
        return "Contact: " +
                "id ='" + id + '\'' +
                ", name: '" + name + '\'' +
                ", contact type: " + contactType +
                ", value ='" + value;
    }
}
