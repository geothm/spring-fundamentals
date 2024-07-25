package ro.wantsome.layered.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

//UsersDAO
@Repository
public class UsersRepository {

	static long idCount = 1L;

	public List<User> findAll() {
		return List.of(
			new User(1L, "John Doe", "johndoes@gmail.com"),
			new User(2L, "Jane Doe", "janedoe@gmail.com"),
			new User(3L, "Alice", "alice@gmail.com"));
	}

	public Long save(User user) {
		user.setId(idCount++);

		System.out.println("User saved: " + user);

		return user.getId();
	}
}
