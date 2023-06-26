package com.user.service.service;

import com.user.service.model.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public List<User> getALlUser();

    public User getSIngleUSer(int userId);

   // public User updateUser(User user,int userId);

   // public void deleteUser(int userId);
}
