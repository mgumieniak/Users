package com.database.users.utils.templateMethodSave.operations;

import com.database.users.model.dto.UserDto;
import com.database.users.model.entity.User;

public interface Save {

    User operateMapToUser(UserDto userDTO);

    User operateAddField(User user);

    User operateSave(User user);

    UserDto operateMapToUserDTO(User user);

}
