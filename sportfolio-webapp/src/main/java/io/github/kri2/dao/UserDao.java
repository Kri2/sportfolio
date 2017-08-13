package io.github.kri2.dao;

import java.util.ArrayList;
import io.github.kri2.domain.User;
import org.springframework.data.repository.CrudRepository;



public interface UserDao extends CrudRepository<User,Long>{
	public User findByName(String name);
	public ArrayList<User> findAll();
}