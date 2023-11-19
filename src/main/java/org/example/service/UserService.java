package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;

public class UserService implements Service<User>{

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User add(User user) {
        return userRepository.create(user);
    }

    public User update(User user) {
       User user1 = userRepository.getById(user.getId());
       user1.setName(user.getName());
       user1.setRole(user.getRole());
       return userRepository.update(user1);
    }

    public boolean remove(Long id) {
        return userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.getById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).orElseThrow();
    }
}
