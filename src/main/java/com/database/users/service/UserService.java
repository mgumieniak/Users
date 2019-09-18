package com.database.users.service;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Optional<User> user = userRepository.findById(userId);
        return mapOptionalUser(user);
    }

    private Optional<UserDTO> mapOptionalUser(Optional<User> user){
        Type optionalType= new TypeToken<Optional<UserDTO>>(){
        }.getType();
        return mapper.map(user, optionalType);
    }

    public List<UserDTO> getUsers(int page, int size, String direction, String... properties) {
        PageRequest pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), properties);
        List<User> users = userRepository.findAll(pageable).getContent();
        return mapList(users);
    }

    public List<UserDTO> getUsers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        List<User> users = userRepository.findAll(pageable).getContent();
        return mapList(users);
    }

    private List<UserDTO> mapList(List<User> users) {
        Type listType = new TypeToken<Iterable<UserDTO>>() {
        }.getType();
        return mapper.map(users, listType);
    }

    public UserDTO createUser(UserDTO userToCreate) {
        User user = mapper.map(userToCreate,User.class);
        user = new User.Builder(user,
                UUID.randomUUID().toString(),
                LocalDate.now()).build();
        userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }


}
