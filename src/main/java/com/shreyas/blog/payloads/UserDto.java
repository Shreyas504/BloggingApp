package com.shreyas.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message="UserName must be min of 4 characters")
	private String name;
	
	@Email(message = "Email address is NOT valid!!!")
	private String email;
	
	@NotEmpty
	@Size(min=4, max=10, message="Password must be min of 4 characters and max 10 characters!")
	private String password;
	
	@NotEmpty
	private String about;
	
}
