package com.invent.inventory.Iservice;

import com.invent.inventory.entity.User;

import java.util.List;

public interface IUserService {
    List<User> listAll();

    User getById(Long id);

    User create(User user);

    User update(Long id, User user);

    void deactivate(Long id);

    void resetPassword(Long id, String rawPassword);
}
