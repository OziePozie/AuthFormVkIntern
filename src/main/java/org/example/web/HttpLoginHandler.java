package org.example.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.commands.Login;
import org.example.exceptions.WrongPassword;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpLoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getRequestMethod();
        Map<String, String> map = queryToMap(exchange.getRequestURI().getQuery());
        String name = map.get("name");
        String password = map.get("password");
        String text;
        try {
            new Login().execute(name, password);
            text = "Successful Login, Welcome " + name;
        } catch (WrongPassword e){
            text = "Incorrect login or password";
        }
            exchange.sendResponseHeaders(200, text.getBytes().length);
            final OutputStream output = exchange.getResponseBody();
            output.write(text.getBytes());
            output.flush();
            exchange.close();


    }
    public Map<String, String> queryToMap(String query) {
        if(query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }
}
