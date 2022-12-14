package com.maxisoft.rest.service;

import com.maxisoft.rest.model.User;
import com.maxisoft.rest.repository.impHibernate.UserHibernateRepoImpl;

import java.util.List;

public class UserService implements GenericService<User, Long>{
    private final UserHibernateRepoImpl userRepository;

    public UserService() {
        userRepository = new UserHibernateRepoImpl();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
