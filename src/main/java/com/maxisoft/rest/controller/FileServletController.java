package com.maxisoft.rest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maxisoft.rest.model.File;
import com.maxisoft.rest.service.FileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FileServletController extends HttpServlet {

    private FileService fileService;
    private Gson GSON;

    @Override
    public void init() {
        fileService = new FileService();
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/json");

        String path = req.getRequestURI();

        String[] userById = path.split("/");

        try {
            Long id = Long.parseLong(userById[userById.length - 1]);

            File file = fileService.getById(id);

            String result = GSON.toJson(file);

            resp.getWriter().println(result);

        }
        catch (NumberFormatException e){
            List<File> files = fileService.getAll();

            String result = GSON.toJson(files);

            resp.getWriter().println(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");

        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        File file = GSON.fromJson(body, File.class);

        fileService.save(file);

        doGet(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");

        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        File file = GSON.fromJson(body, File.class);

        fileService.update(file);

        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");

        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        File file = GSON.fromJson(body, File.class);

        fileService.delete(file.getId());

        doGet(req, resp);
    }
}
