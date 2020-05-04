package com.database.users.utils.templateMethodSave.operations;

import com.database.users.model.dto.UserDto;
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
    public User operateMapToUser(UserDto userDTO) {
        return mapper.map(userDTO, User.class);
    }

    @Override
    abstract public User operateAddField(User user);

    @Override
    public User operateSave(User user) {
        return repo.save(user);
    }

    @Override
    public UserDto operateMapToUserDTO(User user) {
        return mapper.map(user, UserDto.class);
    }
}

