package by.kastapravau.jwtapp.service;

import by.kastapravau.jwtapp.dto.UserInfo;
import by.kastapravau.jwtapp.dto.UserRequest;
import by.kastapravau.jwtapp.model.User;

import java.util.List;

public interface UserService {

    User findUser(String login);

    User findUser(Long id);

    List<User> findAll();

    boolean isUserExists(String login);
    
    boolean isUserExists(Long id);

    void save(User user);

    UserInfo convertUserToUserInfo(User user);

    void deleteUser(Long id);

    User createUser(UserRequest userRequest);

    void updateUser(User user, UserRequest userRequest);
}
