package org.example;

import org.example.commands.Login;
import org.example.commands.Registration;

import java.io.IOException;
import java.util.Scanner;

public class CommandHandler {
    private boolean isRunning = true;
    private static final Scanner SCANNER = new Scanner(System.in);

    public CommandHandler() {
    }

    public void commandExecuter() throws IOException {
        while (isRunning) {
            message();
            switch (SCANNER.next()) {
                case "1" -> {
                    System.out.println("Введите имя и пароль");
                    new Login().execute(SCANNER.next().trim(), SCANNER.next().trim());
                }
                case "2" -> {
                    System.out.println("Введите имя и пароль");
                    new Registration().execute(SCANNER.next().trim(), SCANNER.next().trim());
                }
                case "3" -> {
                    isRunning = false;
                    SCANNER.close();
                    System.exit(0);
                }
            }
        }
    }
    public void message() {
        String str = "Input ID of command: \n" +
                " 1. Login" +
                " 2. Registration" +
                " 3. Exit.";
        System.out.println(str);
    }
}
