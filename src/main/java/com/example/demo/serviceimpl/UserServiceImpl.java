package com.example.demo.serviceimpl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public User save(User user) {
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User update(Long id, User user) {
        User u = getById(id);
        if (u == null) return null;
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        return repo.save(u);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
