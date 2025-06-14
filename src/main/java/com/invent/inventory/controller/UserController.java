package com.invent.inventory.controller;

import com.invent.inventory.entity.User;
import com.invent.inventory.payload.ResetPasswordRequest;
import com.invent.inventory.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<User> list() {
        return svc.listAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return svc.getById(id);
    }

    @PostMapping
    public User create(@RequestBody User u) {
        return svc.create(u);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User u) {
        return svc.update(id, u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivate(@PathVariable Long id) {
        svc.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reset-password")
    public ResponseEntity<?> resetPassword(
            @PathVariable Long id,
            @RequestBody ResetPasswordRequest req) {
        svc.resetPassword(id, req.getNewPassword());
        return ResponseEntity.ok().build();
    }
}