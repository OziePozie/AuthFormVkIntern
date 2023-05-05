package org.example.commands;

import org.example.exceptions.WrongPassword;
import org.example.services.UserService;

public class Login{
    UserService userService = new UserService();
    public void execute(String name, String password) {
        if (!userService.isAuthorized(name, password)){
            throw new WrongPassword();
        }

    }
}
