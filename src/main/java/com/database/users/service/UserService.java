package com.database.users.service;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import com.database.users.service.templateMethodSave.Operation;
import com.database.users.service.templateMethodSave.SaveOperation;
import com.database.users.service.templateMethodSave.operations.Create;
import com.database.users.service.templateMethodSave.operations.Patch;
import com.database.users.service.templateMethodSave.operations.Save;
import com.database.users.service.templateMethodSave.operations.Update;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

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
        Operation createUser = new SaveOperation(new Create(mapper, userRepository));
        return createUser.save(userToCreate);
    }

    public UserDTO updateUser(String userId, UserDTO userToUpdate) {
        Operation updateUser = new SaveOperation(new Update(mapper, userRepository, userId));
        return updateUser.save(userToUpdate);
    }

    public UserDTO patchUser(String userId, UserDTO patchUser, UserDTO userToUpdate) {
        Patch patch = new Patch(mapper, userRepository, userId);
        UserDTO userDTO = patch.patchUser(patchUser,userToUpdate);
        Operation patchUserOperation = new SaveOperation(patch);
        return patchUserOperation.save(userDTO);
    }

}
