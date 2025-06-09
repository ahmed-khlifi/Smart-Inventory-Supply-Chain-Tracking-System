package com.invent.inventory.service;

import com.invent.inventory.Iservice.IUserService;
import com.invent.inventory.entity.User;
import com.invent.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repo;
    public List<User> findAll() { return repo.findAll(); }
    public Optional<User> findById(Long id) { return repo.findById(id); }
    public User save(User user) { return repo.save(user); }
    public void deleteById(Long id) { repo.deleteById(id); }
    public Optional<User> findByUsername(String username) { return repo.findByUsername(username); }
}
