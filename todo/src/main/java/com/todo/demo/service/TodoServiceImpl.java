package com.todo.demo.model;


import com.todo.demo.repository.TodosRepository;
import com.todo.demo.service.TodoService;
import org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "todoService")
public abstract class TodoServiceImpl implements TodoService
{
    private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Autowired
    private TodosRepository todorepos;

    @Override
    public ArrayList<Todo> findAll()
    {
        ArrayList<Todo> list = new ArrayList<>();
        todorepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Todo findtodoById(long id)
    {
        return todorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Todo findtodoByName(String name) throws EntityNotFoundException
    {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (todorepos.findById(id).isPresent())
        {
            todorepos.deleteTodos(id);
            todorepos.deleteById(id);

            logger.info("Todo Deleted");
        }else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }


//    @Transactional
//    @Override
//    public Todo save(Todo todo)
//    {
//        Todo newTodo = new Todo();
//
//        newTodo.setTodoname(todo.getTodoname());
//
//        for (TodoItem t:todo.getTodoItems())
//        {
//            newTodo.getTodoItems().add(new TodoItem(t.getTodoTask(),t.getPhonenumber(), newTodo));
//        }
//
//        logger.info("Updating a Todo");
//        return todorepos.save(newTodo);
//    }
//
//
//    @Transactional
//    @Override
//    public Todo update(Todo todo, long id)
//    {
//        Todo currentTodo = todorepos.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
//
//        if (todo.getTodoname() != null)
//        {
//            currentTodo.setTodoame(todo.getTodoname());
//        }
//
//        if (todo.getTodoItems().size() > 0)
//        {
//            // adds new phone numbers to list
//            for (TodoItem t:todo.getTodoItems())
//            {
//                currentTodo.getTodoItems().add(new Telephone(t.getTodoName(), currentTodo));
//            }
//        }
//
//        logger.info("Creating a Todo");
//        return todo.save(currentTodo);
//    }
}
