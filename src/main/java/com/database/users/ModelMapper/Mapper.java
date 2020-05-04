package com.database.users.ModelMapper;

import com.database.users.model.dto.UserDto;
import com.database.users.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        applyOwnMapRules(mapper);
        return mapper;
    }

    private void applyOwnMapRules(ModelMapper mapper) {
        mapper.createTypeMap(UserDto.class, User.class).setProvider(
                request -> {
                    UserDto u = (UserDto) request.getSource();
                    return new User.Builder(u.getName(), u.getSurname(),
                            u.getEmail(), u.getCreationAccountDate())
                            .phoneNumber(u.getPhoneNumber())
                            .build();
                }
        );

        mapper.createTypeMap(User.class, UserDto.class).setProvider(
                request -> {
                    User u = (User) request.getSource();
                    return new UserDto.Builder(u.getCreationAccountDate())
                            .name(u.getName())
                            .surname(u.getSurname())
                            .email(u.getEmail())
                            .phoneNumber(u.getPhoneNumber())
                            .build();
                }
        );
    }
}
