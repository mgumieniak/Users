package com.database.users.service.templateMethodSave.operations;

import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;

public class Update extends AbstractSave {

    public Update(ModelMapper mapper, UserRepository repo) {
        super(mapper, repo);
    }

    @Override
    public User operateAddField(User user) {
        return new User.Builder(user.getName(), user.getSurname(),
                user.getEmail(), user.getCreationAccountDate())
                .userId(user.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
