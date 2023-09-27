package rest;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisteredUsesResponse {
	String userID;
	String username;
 	List<String> books;
}
