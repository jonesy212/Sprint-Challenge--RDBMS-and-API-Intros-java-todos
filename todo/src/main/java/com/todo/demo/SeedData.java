package com.todo.demo;

// Vivek Vishwanath

import com.todo.demo.model.Role;
import com.todo.demo.model.User;
import com.todo.demo.model.UserRoles;
import com.todo.demo.repository.RoleRepository;
import com.todo.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{

    RoleRepository rolerepos;
    UserRepository userrepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("ADMIN");
        Role r2 = new Role("TODODATA");
        Role r3 = new Role("TODOITEMDATA");


        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);


        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        User u1 = new User("admin", "password", admins);
        userrepos.save(u1);

        ArrayList<UserRoles> tododata = new ArrayList<>();
        tododata.add(new UserRoles(new User(), r2));
        User u2 = new User("barnbarn", "ILuvM4th!", tododata);
        userrepos.save(u2);

        ArrayList<UserRoles> todoitemdata = new ArrayList<>();
        todoitemdata.add(new UserRoles(new User(), r3));
        User u3 = new User("jane", "password", todoitemdata);
        userrepos.save(u3);

        admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        User u5 = new User("hops", "password", admins);
        userrepos.save(u5);
    }
}