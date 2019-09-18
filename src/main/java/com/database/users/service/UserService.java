package com.database.users.service;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
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

    public ResponseEntity<UserDTO> getUserByUserId(String userId) {
        return userRepository.findById(userId)
                .map(user ->
                        ResponseEntity.ok().body(mapper.map(user,UserDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        Type listType = new TypeToken<List<UserDTO>>() {
        }.getType();
        return mapper.map(users, listType);
    }

    public UserDTO createUser(UserDTO userToCreate) {
        User user = mapper.map(userToCreate, User.class);
        user = new User.Builder(user.getName(), user.getSurname(),
                user.getEmail(), LocalDate.now())
                .phoneNumber(user.getPhoneNumber())
                .build();
        userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    public UserDTO updateUser(UserDTO userToUpdate) {
        User user = mapper.map(userToUpdate, User.class);
        user = new User.Builder(user.getName(), user.getSurname(),
                user.getEmail(), user.getCreationAccountDate())
                .phoneNumber(user.getPhoneNumber())
                .build();
        userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }


}
