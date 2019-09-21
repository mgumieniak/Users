package com.database.users.service.templateMethodSave.operations;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;

public abstract class AbstractSave implements Save {
    private ModelMapper mapper;
    private UserRepository repo;

    public AbstractSave(ModelMapper mapper, UserRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public User operateMapToUser(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

    @Override
    abstract public User operateAddField(User user);

    @Override
    public User operateSave(User user) {
        return repo.save(user);
    }

    @Override
    public UserDTO operateMapToUserDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
}

