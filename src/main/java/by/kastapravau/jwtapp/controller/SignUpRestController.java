package by.kastapravau.jwtapp.controller;

import by.kastapravau.jwtapp.dto.UserRequest;
import by.kastapravau.jwtapp.model.User;
import by.kastapravau.jwtapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SignUpRestController {

    UserService userService;

    @Autowired
    public SignUpRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registration(@RequestBody UserRequest userRequest) {
        if (userRequest.isValid()) {
            String login = userRequest.getLogin();
            if (userService.isUserExists(login)) {
                return ResponseEntity.badRequest().body("User with login " + login + " is exists");
            }
            User user = userService.createUser(userRequest);
            userService.save(user);
            return ResponseEntity.ok("User " + login + " saved");
        }
        return ResponseEntity.badRequest().body("Please, fill all fields");
    }
}
