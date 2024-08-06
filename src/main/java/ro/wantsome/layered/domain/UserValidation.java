package ro.wantsome.layered.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class UserValidation {

	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
	private String name;
	private String email;

	public UserValidation() {
	}

	public UserValidation(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
	}
}
