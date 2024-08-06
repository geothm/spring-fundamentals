package ro.wantsome.layered.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

//UsersDAO
@Repository
public class UsersRepository {

	static long idCount = 1L;

	public List<UserValidation> findAll() {
		return List.of(
			new UserValidation(1L, "John Doe", "johndoes@gmail.com"),
			new UserValidation(2L, "Jane Doe", "janedoe@gmail.com"),
			new UserValidation(3L, "Alice", "alice@gmail.com"));
	}

	public Long save(UserValidation userValidation) {
		userValidation.setId(idCount++);

		System.out.println("User saved: " + userValidation);

		return userValidation.getId();
	}
}
