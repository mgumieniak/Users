package com.database.users.ModelMapper;

import com.database.users.model.dto.UserDTO;
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
        mapper.createTypeMap(UserDTO.class, User.class).setProvider(
                request -> {
                    UserDTO u = UserDTO.class.cast(request.getSource());
                    return new User.Builder(u.getName(), u.getSurname(),
                            u.getEmail(), u.getPhoneNumber(),
                            u.getCreationAccountDate()).build();
                }
        );

        mapper.createTypeMap(User.class, UserDTO.class).setProvider(
                request -> {
                    User u = User.class.cast(request.getSource());
                    return new UserDTO(u.getName(), u.getSurname(),
                            u.getEmail(), u.getPhoneNumber(),
                            u.getCreationAccountDate());
                }
        );
    }
}
