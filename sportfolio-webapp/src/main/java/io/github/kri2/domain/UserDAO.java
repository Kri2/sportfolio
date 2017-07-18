package io.github.kri2.domain;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserDAO extends CrudRepository<User,Integer>{
	public User findById(Integer id);
	public User findByName(String name);
}
