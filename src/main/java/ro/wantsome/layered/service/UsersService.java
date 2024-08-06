package ro.wantsome.layered.service;

import org.springframework.stereotype.Service;
import ro.wantsome.layered.domain.UserValidation;
import ro.wantsome.layered.domain.UsersRepository;

import java.util.List;

@Service
public class UsersService {

	private final UsersRepository usersRepository;

	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public List<UserValidation> listUsers() {
		return usersRepository.findAll();
	}

	public Long saveUser(UserValidation userValidation) {
		return usersRepository.save(userValidation);
	}
}
