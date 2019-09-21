package com.database.users.service.templateMethodSave.operations;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.HttpServerErrorException;

public interface Save {

    User operateMapToUser(UserDTO userDTO);

    User operateAddField(User user);

    User operateSave(User user);

    UserDTO operateMapToUserDTO(User user);

}
