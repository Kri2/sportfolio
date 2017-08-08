package io.github.kri2.dataaccess;

import org.springframework.stereotype.Repository;

import io.github.kri2.domain.User;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface UserDao extends CrudRepository<User, Long>{
	public User findById(Long id);
	public User findByName(String name);
}