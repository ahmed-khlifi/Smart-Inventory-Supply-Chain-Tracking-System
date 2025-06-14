package com.invent.inventory.service;

import com.invent.inventory.Iservice.IUserService;
import com.invent.inventory.entity.User;
import com.invent.inventory.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public List<User> listAll() {
        return repo.findAll();
    }

    @Override
    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User create(User user) {
        if (repo.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username taken");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User update(Long id, User u) {
        User existing = getById(id);
        existing.setRole(u.getRole());
        existing.setActive(u.isActive());
        return repo.save(existing);
    }

    @Override
    public void deactivate(Long id) {
        User u = getById(id);
        u.setActive(false);
        repo.save(u);
    }

    @Override
    public void resetPassword(Long id, String rawPassword) {
        User u = getById(id);
        u.setPassword(encoder.encode(rawPassword));
        repo.save(u);
    }
}
