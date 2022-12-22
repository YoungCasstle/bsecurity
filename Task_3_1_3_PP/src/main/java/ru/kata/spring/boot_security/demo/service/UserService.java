package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void deleteUserById(int id);

    void updateUser(User user);

    void addUser(User user);

    User getUserById(int id);

    User getUserByName(String username);
}
