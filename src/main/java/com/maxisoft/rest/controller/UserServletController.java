package com.maxisoft.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxisoft.rest.model.User;
import com.maxisoft.rest.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class UserServletController extends HttpServlet {

    private final UserService userService = new UserService();
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");

        String path = req.getRequestURI();

        String[] userById = path.split("/");

        try{
            Long id = Long.parseLong(userById[userById.length-1]);

            User user = userService.getById(id);

            String result = GSON.toJson(user);

            resp.getWriter().println(result);

        }catch (NumberFormatException e){
            List<User> users = userService.getAll();

            String result = GSON.toJson(users);

            resp.getWriter().println(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        User user = GSON.fromJson(body, User.class);

        userService.save(user);

        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        User user = GSON.fromJson(body, User.class);

        userService.update(user);

        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        User user = GSON.fromJson(body, User.class);

        userService.delete(user.getId());

        doGet(req, resp);
    }
}
