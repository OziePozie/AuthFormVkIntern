package org.example.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DbCreate {


    public static void create(){
        File file = new File("Users.json");
        if (!file.exists()){
            try {
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.append("[]");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
