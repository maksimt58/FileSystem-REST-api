package com.maxisoft.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxisoft.rest.model.Event;
import com.maxisoft.rest.model.User;
import com.maxisoft.rest.service.EventService;

import com.maxisoft.rest.service.UserService;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class EventServletController extends HttpServlet {

    private EventService eventService;
    private UserService userService;
    private Gson GSON;

    @Override
    public void init() {
        eventService = new EventService();
        userService = new UserService();
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");

        String path = req.getRequestURI();

        String[] eventById = path.split("/");

        try {
            Long id = Long.parseLong(eventById[eventById.length - 1]);

            Event event = eventService.getById(id);

            String result = GSON.toJson(event);

            resp.getWriter().println(result);

        } catch (NumberFormatException e) {
            List<Event> events = eventService.getAll();

            String result = GSON.toJson(events);

            resp.getWriter().println(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            Long userId = Long.parseLong(req.getParameter("userId"));

            User user = userService.getById(userId);

            Event event = GSON.fromJson(body, Event.class);

            user.getEvents().add(event);

            eventService.save(event);

            userService.update(user);

            doGet(req, resp);

        }catch (Exception e){
            resp.sendError(400);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Event event = GSON.fromJson(body, Event.class);

        eventService.update(event);

        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Event event = GSON.fromJson(body, Event.class);

        eventService.delete(event.getId());

        doGet(req, resp);
    }
}
