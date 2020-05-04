package com.database.users.utils.templateMethodSave;

import com.database.users.model.dto.UserDto;
import com.database.users.model.entity.User;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Operation {
    private final Function<UserDto, User> mapToUser;
    private final UnaryOperator<User> addField;
    private final UnaryOperator<User> save;
    private final Function<User, UserDto> mapToUserDTO;

    protected Operation(Function<UserDto, User> mapToUser, UnaryOperator<User> addField,
                        UnaryOperator<User> save, Function<User, UserDto> mapToUserDTO) {
        this.mapToUser = mapToUser;
        this.addField = addField;
        this.save = save;
        this.mapToUserDTO = mapToUserDTO;
    }

    public UserDto save(UserDto userToUpdate){
        User user = mapToUser.apply(userToUpdate);
        user = addField.apply(user);
        save.apply(user);
        return mapToUserDTO.apply(user);
    }
}
