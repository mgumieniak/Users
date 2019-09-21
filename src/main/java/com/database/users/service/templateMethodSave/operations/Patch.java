package com.database.users.service.templateMethodSave.operations;

import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;

public class Patch extends AbstractSave {

    public Patch(ModelMapper mapper, UserRepository repo) {
        super(mapper, repo);
    }

    @Override
    public User operateAddField(User user) {
        return new User.Builder(user.getName(), user.getSurname(),
                user.getEmail(), user.getCreationAccountDate())
                .phoneNumber(user.getPhoneNumber())
                .userId(user.getUserId())
                .build();
    }
}
