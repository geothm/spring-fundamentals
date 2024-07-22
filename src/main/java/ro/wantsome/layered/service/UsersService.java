package ro.wantsome.layered.service;

import org.springframework.stereotype.Service;
import ro.wantsome.layered.domain.User;
import ro.wantsome.layered.domain.UsersRepository;

import java.util.List;

@Service
public class UsersService {

	private final UsersRepository usersRepository;

	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public List<User> listUsers() {
		return usersRepository.findAll();
	}
}
