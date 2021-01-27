package net.codejava;

import java.util.List;

public interface UserService {
	
	public List<User> findAll();
	public User findByUsername(String username);
	public void save(User user);
}
