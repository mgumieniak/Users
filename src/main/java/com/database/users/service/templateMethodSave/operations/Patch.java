package com.database.users.service.templateMethodSave.operations;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import com.database.users.repository.UserRepository;
import org.modelmapper.ModelMapper;

public class Patch extends AbstractSave {

    private String userId;

    public Patch(ModelMapper mapper, UserRepository repo, String userId) {
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
                .phoneNumber(user.getPhoneNumber())
                .userId(this.getUserId())
                .roles(user.getRoles())
                .permissions(user.getPermissions())
                .build();
    }

    public UserDTO patchUser(UserDTO patch, UserDTO userToUpdate) {

        UserDTO.Builder builder;
        if (patch.getCreationAccountDate() != null) {
            builder = new UserDTO.Builder(patch.getCreationAccountDate());
        } else {
            builder = new UserDTO.Builder(userToUpdate.getCreationAccountDate());
        }

        if (patch.getName() != null) {
            builder.name(patch.getName());
        } else {
            builder.name(userToUpdate.getName());
        }

        if (patch.getSurname() != null) {
            builder.surname(patch.getSurname());
        } else {
            builder.surname(userToUpdate.getSurname());
        }

        if (patch.getEmail() != null) {
            builder.email(patch.getEmail());
        } else {
            builder.email(userToUpdate.getEmail());
        }

        if (patch.getPhoneNumber() != null) {
            builder.phoneNumber(patch.getPhoneNumber());
        } else {
            builder.phoneNumber(userToUpdate.getPhoneNumber());
        }

        if (patch.getPermissions() != null) {
            builder.permissions(patch.getPermissions());
        } else {
            builder.permissions(userToUpdate.getPermissions());
        }

        if (patch.getRoles() != null) {
            builder.roles(patch.getRoles());
        } else {
            builder.roles(userToUpdate.getRoles());
        }

        return builder.build();
    }
}


