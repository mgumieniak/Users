package com.database.users.service.templateMethodSave;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Operation {
    private final Function<UserDTO, User> mapToUser;
    private final UnaryOperator<User> addField;
    private final UnaryOperator<User> save;
    private final Function<User, UserDTO> mapToUserDTO;

    public Operation(Function<UserDTO, User> mapToUser, UnaryOperator<User> addField,
                     UnaryOperator<User> save, Function<User, UserDTO> mapToUserDTO) {
        this.mapToUser = mapToUser;
        this.addField = addField;
        this.save = save;
        this.mapToUserDTO = mapToUserDTO;
    }

    public UserDTO save(UserDTO userToUpdate){
        User user = mapToUser.apply(userToUpdate);
        user = addField.apply(user);
        save.apply(user);
        return mapToUserDTO.apply(user);
    }
}
