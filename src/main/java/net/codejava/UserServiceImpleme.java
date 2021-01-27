package net.codejava;

import java.util.List;

public class UserServiceImpleme implements UserService {
	
	private UserRepository theUserRepo;
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		
		return theUserRepo.findAll();
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return theUserRepo.getUserByUsername(username);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		theUserRepo.save(user);
	}

}
