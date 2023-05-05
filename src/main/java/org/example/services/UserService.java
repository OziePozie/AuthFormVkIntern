package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import org.example.cipher.KeyGen;
import org.example.entity.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;



public class UserService {

    File file = new File("Users.json");
    ObjectMapper objectMapper = new ObjectMapper();

    public UserService()  {
    }

    public void addUser(User user){
        try {
            User[] users = objectMapper.readValue(file, User[].class);
            file.delete();
            FileWriter fileWriter = new FileWriter(file, true);
            SequenceWriter s = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValuesAsArray(fileWriter);
            int id;
            if (users == null){
                id = 1;
            } else
            {
                id = users.length+1;
                s.writeAll(users);
            }

            s.write(new User(id,user.getName(), KeyGen.encrypt(user.getPassword(), KeyGen.getSecretKey())));
            s.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public boolean isAuthorized(String name, String password){
        User[] user;
        try {
             user = objectMapper.readValue(file, User[].class);
            for (User u:user) {
                if (u.getName().equals(name)){
                    if (KeyGen.decrypt(u.getPassword(), KeyGen.getSecretKey()).equals(password)){
                        return true;
                    }
                }
            }
        } catch (Exception ignored) {

        }
        return false;
    }
    public boolean isExist(String name){
        User[] user;
        try {
            user = objectMapper.readValue(file, User[].class);
            return Arrays.stream(user)
                    .anyMatch(user1 -> user1.getName().equals(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteUser(){

    }
    public User[] getUsers(){
        User[] user;
        try {
            user = objectMapper.readValue(file, User[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }



}
