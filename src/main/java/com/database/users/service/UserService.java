package com.database.users.service;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import com.database.users.service.templateMethodSave.*;
import com.database.users.service.templateMethodSave.operations.Create;
import com.database.users.service.templateMethodSave.operations.Patch;
import com.database.users.service.templateMethodSave.operations.Update;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class UserService {
    private ModelMapper mapper;
    private UserRepository userRepository;

    @Autowired
    public UserService(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public Optional<UserDTO> getUserByUserId(String userId) {
        return userRepository.findById(userId)
                .map(user -> mapper.map(user, UserDTO.class));
    }


    public List<UserDTO> getUsers(int page, int size,
                                  String direction, String... properties) {
        PageRequest pageable = PageRequest.of(page, size,
                Sort.Direction.fromString(direction), properties);
        List<User> users = userRepository.findAll(pageable).getContent();
        return mapList(users);
    }

    public List<UserDTO> getUsers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        List<User> users = userRepository.findAll(pageable).getContent();
        return mapList(users);
    }

    private List<UserDTO> mapList(List<User> users) {
        Type listType = new TypeToken<List<UserDTO>>() {
        }.getType();
        return mapper.map(users, listType);
    }

    public UserDTO createUser(UserDTO userToCreate) {
        Operation createUser = new SaveOperation(new Create(mapper,userRepository));
        return createUser.save(userToCreate);
    }

    public UserDTO updateUser(UserDTO userToUpdate) {
        Operation updateUser = new SaveOperation(new Update(mapper,userRepository));
        return updateUser.save(userToUpdate);
    }

    public UserDTO patchUser(UserDTO userToUpdate) {
        Operation patchUser = new SaveOperation(new Patch(mapper,userRepository));
        return patchUser.save(userToUpdate);
    }

}
