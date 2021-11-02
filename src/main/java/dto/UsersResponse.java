package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UsersResponse {

	@JsonProperty("users")
	private List<User> users;

	@JsonProperty("status")
	private String status;
}