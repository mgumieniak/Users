package com.database.users.controller.external;

import com.database.users.model.dto.UserDto;
import com.database.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/users",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        return userService.getUserByUserId(userId)
                .map(user -> ResponseEntity.ok().body(user))
//                .orElseThrow(() -> new WebServerException(HttpStatus.BAD_REQUEST,"Not found user with id: " + userId));
                .orElseThrow(() -> new IllegalArgumentException("error"));
    }

    @GetMapping()
    public List<UserDto> getUsers(
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int limit,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String surname,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String creationAccountDate,
                                  @RequestParam(defaultValue = "DESC") String sortDirection) {

        List<String> propertyList = Arrays.asList(name, surname, email, creationAccountDate);
        String[] properties = propertyList.stream().filter(Objects::nonNull).toArray(String[]::new);
        return sortDependingOfProperties(page, limit, sortDirection, properties);
    }

    private List<UserDto> sortDependingOfProperties(int page, int limit, String sortDirection, String... properties) {
        List<UserDto> users;
        if (properties.length == 0) {
            users = userService.getUsers(page, limit);
        } else {
            users = userService.getUsers(page, limit, sortDirection, properties);
        }
        return users;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @PutMapping(path = "/{userId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto updateUser(@PathVariable("userId") String userId,
                              @RequestBody UserDto user) {
        return userService.updateUser(userId, user);
    }

    @PatchMapping(path = "/{userId}", consumes = "application/json")
    public ResponseEntity<UserDto> patchUser(@PathVariable("userId") String userId,
                                             @RequestBody UserDto patchUser) {
        return userService.getUserByUserId(userId)
                .map(userToUpdate -> ResponseEntity.status(201)
                        .body(userService.patchUser(userId, patchUser, userToUpdate)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
