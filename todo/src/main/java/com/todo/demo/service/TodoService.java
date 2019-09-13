package com.todo.demo.service;

import com.todo.demo.model.Todo;

import java.util.ArrayList;

public interface TodoService
{
    ArrayList<Todo> findAll();

    Todo findtodoById(long id);

    Todo findtodoByName(String name);

    void delete(long id);

    Todo save(Todo todo);

    Todo update(Todo todo, long id);
}
