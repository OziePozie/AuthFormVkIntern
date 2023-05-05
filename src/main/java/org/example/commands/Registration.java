package org.example.commands;

import org.example.entity.User;
import org.example.exceptions.RegistrationException;
import org.example.services.UserService;

public class Registration {
    UserService userService  = new UserService();
    public void execute(String name, String password) {
        if (userService.isExist(name)){
            throw new RegistrationException();
        } else {
            userService.addUser(new User(name, password));
        }
    }
}
