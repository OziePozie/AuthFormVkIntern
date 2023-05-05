package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.db.DbCreate;
import org.example.web.HttpLoginHandler;
import org.example.web.HttpRegistrationHandler;

import java.io.*;
import java.net.InetSocketAddress;


public class Main {
        public static void main(String[] args) throws IOException {
                DbCreate.create();
                HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
                server.createContext("/login", new HttpLoginHandler());
                server.createContext("/registration", new HttpRegistrationHandler());
                server.start();
        }


}