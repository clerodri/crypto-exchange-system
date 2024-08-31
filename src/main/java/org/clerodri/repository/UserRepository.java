package org.clerodri.repository;

import org.clerodri.entity.User;

import java.util.HashSet;
import java.util.Set;

public class UserRepository {

    //Store  users data for entire application
    private static Set<User> users = new HashSet<>();


    //user to initialize static members (executed only 1 time )
    static {
        User user1 = new User("admin","admin@gmail.com","123456");
        users.add(user1);

    }
}
