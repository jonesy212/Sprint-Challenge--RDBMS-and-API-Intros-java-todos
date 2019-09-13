package com.todo.demo.controller;

import com.todo.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todoss")
public class TodoController
{
    @Autowired
    private TodoService TodoService;

    // GET: localhost:2019/todos/todos
    @GetMapping(value = "/todos", produces = {"application/json"})
    public ResponseEntity<?> listAllTodoss()
    {
        return new ResponseEntity<>(TodoService.findAll(), HttpStatus.OK);
    }
}
