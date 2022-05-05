package by.kastapravau.jwtapp.controller;

import by.kastapravau.jwtapp.dto.UserInfo;
import by.kastapravau.jwtapp.dto.UserRequest;
import by.kastapravau.jwtapp.model.User;
import by.kastapravau.jwtapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserInfo> response = users.stream()
                                       .map(userService::convertUserToUserInfo)
                                       .collect(toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id, UserRequest userRequest) {
        User user = userService.findUser(id);
        if (user == null) {
            return ResponseEntity.badRequest().body("User with id " + id + " not found");
        }
        if (userRequest.isValid()) {
            userService.updateUser(user, userRequest);
            userService.save(user);
            return ResponseEntity.ok("Updated");
        }
        return ResponseEntity.badRequest().body("Please, fill all fields");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        if (userService.isUserExists(id)) {
            userService.deleteUser(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.badRequest().body("User with id " + id + " is not exists");
    }
}
