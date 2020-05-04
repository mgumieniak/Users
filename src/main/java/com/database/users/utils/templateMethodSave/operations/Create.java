package com.database.users.utils.templateMethodSave.operations;

import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

public class Create extends AbstractSave {

    public Create(ModelMapper mapper, UserRepository repo) {
        super(mapper, repo);
    }

    @Override
    public User operateAddField(User user) {
        return new User.Builder(user.getName(), user.getSurname(),
                user.getEmail(), LocalDate.now())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles())
                .permissions(user.getPermissions())
                .build();
    }
}
