package com.todo.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCreation;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "todos")
public class Todo extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todosid;

    @Column(nullable = false)
    private String todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",
                nullable = false)
    @JsonIgnoreProperties({"todo", "hibernateLazyInitializer"})
    private User user;

    @Column(nullable = false)
    private DateTimeAtCreation datetime;

     @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties({"datetime", "hibernateLazyInitializer"})

    @Column(name = "completed",
            nullable = false)
    private boolean completed;
    @JsonIgnoreProperties({"completed", "hibernateLazyInitializer"})


    //default constructor
    public Todo()
    {
    }

    public Todo(String todo, User user)
    {
        this.todo = todo;
        this.user = user;
    }

    public long getTodosid()
    {
        return todosid;
    }

    public void setTodosid(long todosid)
    {
        this.todosid = todosid;
    }

    public String getTodo()
    {
        return todo;
    }

    public void setTodo(String todo)
    {
        this.todo = todo;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public DateTimeAtCreation getDatetime()
    {
        return datetime;
    }

    public void setDatetime(DateTimeAtCreation
                                    datetime)
    {
        this.datetime = datetime;
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }
}
