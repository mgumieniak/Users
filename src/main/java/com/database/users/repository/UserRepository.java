package com.database.users.repository;


import com.database.users.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {


}
