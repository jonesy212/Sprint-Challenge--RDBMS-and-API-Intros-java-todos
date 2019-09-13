package com.todo.demo.repository;

import com.todo.demo.model.Todo;
import com.todo.demo.views.CreateTodos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface TodosRepository extends CrudRepository<Todo, Long>
{
    @Modifying
    @Query(value = "DELETE FROM todoitems WHERE todoid = :todoid", nativeQuery = true)
    void deleteTodos(long todoid);

    @Query(value = "SELECT u.username as username, COUNT(*) as createtodos FROM t JOIN users u ON t.userid= u.userid GROUP BY u.username ", nativeQuery = true)
    ArrayList<CreateTodos> getCreateTodos();
}




