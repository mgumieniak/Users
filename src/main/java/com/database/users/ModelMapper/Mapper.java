package com.database.users.ModelMapper;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;


public class Mapper {

    public static User map(UserDTO userDTO){
        return new User.Builder(userDTO.getName(),userDTO.getSurname(),
                userDTO.getEmail(),userDTO.getPhoneNumber(),userDTO.getCreationAccountDate())
                .phoneNumber(userDTO.getPhoneNumber()).build();
    }

    public static UserDTO map(User user){
        return new UserDTO(user.getName(),user.getSurname(),
                user.getEmail(),user.getPhoneNumber(),user.getCreationAccountDate());
    }

    public  List<BasicUser> map(List<BasicUser> usersDTO){
        return usersDTO.stream().map(Mapper::)
                .collect(Collectors.toList());
    }



}
