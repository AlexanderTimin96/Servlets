package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    private final Gson gson;

    public PostController(PostService service) {
        this.service = service;
        gson = new Gson();
    }

    public void all(HttpServletResponse response) throws IOException {
        final var data = service.all();
        sendResponceJSON(data, response);
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        final var data = service.getById(id);
        sendResponceJSON(data, response);
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        sendResponceJSON(data, response);
    }

    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
    }

    public <T> void sendResponceJSON(T data, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.getWriter().print(gson.toJson(data));
    }
}
