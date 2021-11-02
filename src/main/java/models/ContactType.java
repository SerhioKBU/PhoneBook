package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContactType {
    @JsonProperty("e-mail")
    EMAIL("E-Mail"),
    @JsonProperty("phone")
    PHONE("Telephone");
    private final String name;

    public int getId() {
        for (int i = 0; i < values().length; i++) {
            if (values()[i] == this) return i;
        }
        return 0;
    }
}
