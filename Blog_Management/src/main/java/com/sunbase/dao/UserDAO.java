package com.sunbase.dao;

import com.sunbase.models.User;

public interface UserDAO {
    boolean registerUser(User user);
    User loginUser(String username, String password);
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
