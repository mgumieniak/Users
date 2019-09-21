package com.database.users.service.templateMethodSave.operations;

import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;

public class Update extends AbstractSave {

    private String userId;

    public Update(ModelMapper mapper, UserRepository repo, String userId) {
        super(mapper, repo);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public User operateAddField(User user) {
        return new User.Builder(user.getName(), user.getSurname(),
                user.getEmail(), user.getCreationAccountDate())
                .userId(this.getUserId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
