package rest;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse {
	String userId;
	String username;
	String password;
	String token;
	String expires;
	String created_date;
	String isActive;
}
