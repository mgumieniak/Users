package com.database.users.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

}
