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
public class AdminController
{
    @Autowired
    TodoService todoService;

    @PutMapping(value = "/todo/{id}",
                produces = {"application/json"},
                consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(
            @RequestBody
                    Todo updateTodo,
            @PathVariable
                    long id)
    {
        TodoService.update(updateTodo, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST localhost:2019/admin/todos
    @PostMapping(value = "/todos",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewTodo(HttpServletRequest request, @Valid
    @RequestBody
            Todo newTodo) throws URISyntaxException
    {
        newTodo = todoService.save(newTodo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        // URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{todoid}").buildAndExpand(newTodo.getTodoid()).toUri();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/todos/todos/{todoid}").buildAndExpand(newTodo.getTodosid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/todos/{todosid}")
    public ResponseEntity<?> deleteTodoById(
            @PathVariable
                    long todoid)
    {
        todoService.delete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/todos/{todoid}/todos/{todoitemid}")
    public ResponseEntity<?> saveTodoItemCombo(HttpServletRequest request,
                                                @PathVariable("todoid")
                    long todoid,
                                                @PathVariable("todoitemid")
                    long todoitemid)
    {
//        todoService.saveTodoCombo(todoid, todoitemid);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        // URI newTodoURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{todoid}").buildAndExpand(newTodo.getTodoid()).toUri();
        URI newTodoURI = ServletUriComponentsBuilder.fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/todos/todos/{todoid}").buildAndExpand(todoid).toUri();
        responseHeaders.setLocation(newTodoURI);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
