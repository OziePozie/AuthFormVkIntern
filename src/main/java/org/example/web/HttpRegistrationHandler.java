package org.example.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.example.commands.Registration;
import org.example.exceptions.RegistrationException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpRegistrationHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getRequestMethod();
        Map<String, String> map = queryToMap(exchange.getRequestURI().getQuery());
        String name = map.get("name");
        String password = map.get("password");
        String text;
        try {
            new Registration().execute(name, password);
            text = "You successfully create account";
        } catch (RegistrationException e){
            text = "This name already exists";
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
