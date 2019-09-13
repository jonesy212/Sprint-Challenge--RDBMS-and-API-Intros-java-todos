package com.todo.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

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
    @JsonIgnoreProperties({"todos", "hibernateLazyInitializer"})
    private User user;

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
}
