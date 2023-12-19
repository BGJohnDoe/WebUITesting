package rest;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Token {
	String token;
	String expires;
	String status;
	String result;
}
