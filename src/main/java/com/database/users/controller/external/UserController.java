package com.database.users.controller.external;

import com.database.users.model.dto.UserDTO;
import com.database.users.model.entity.User;
import com.database.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{/id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserByUserId(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public List<UserDTO> getUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int limit,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String surname,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String creationAccountDate,
                                  @RequestParam(defaultValue = "DESC") String sortDirection) {

        List<String> propertyList = Arrays.asList(name, surname, email, creationAccountDate);
        String[] properties = propertyList.stream().filter(Objects::nonNull).toArray(String[]::new);

        return sortDependingOfProperties(page, limit, sortDirection, properties);
    }

    private List<UserDTO> sortDependingOfProperties(int page, int limit, String sortDirection, String... properties) {
        List<UserDTO> users;
        if (properties.length == 0) {
            users = userService.getUsers(page, limit);
        } else {
            users = userService.getUsers(page, limit, sortDirection, properties);
        }
        return users;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
