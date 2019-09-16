package com.database.users.repository;


import com.database.users.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
