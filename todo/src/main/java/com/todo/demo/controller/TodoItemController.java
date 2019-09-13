package com.todo.demo.controller;

import com.todo.demo.model.Todo;
import com.todo.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/admin")
public class TodoItemController

{
    @Autowired
    private TodoService todoitemService;

    @GetMapping (value = "/todoitems/{id}",
                produces = {"application/json"})
    public ResponseEntity<?> updateTodo()

    {
        return new ResponseEntity<>(todoitemService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{type}",
                produces = {"application/json"})
    public ResponseEntity<?> findtodobyName(
            @PathVariable
                    String type)
    {
        Todo t = todoitemService.findtodoByName(type);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

}

