package com.hhx.service.impl;

import com.hhx.entity.User;
import com.hhx.entity.condition.UserQueryCondition;
import com.hhx.repository.UserRepository;
import com.hhx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User getById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public User getByName(String username) {
        List<User> list = repository.getByUsername(username);
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
//        if(userQueryCondition == null){
//           return repository.findAll(pageable);
//        }
//        User user = new User();
//        user.setUsername(userQueryCondition.getUsername());
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("username", match -> match.contains());
//        Example<User> example = Example.of(user,matcher);
//        return repository.findAll(example,pageable);
        return repository.findAll(pageable);
    }
}
