package com.hhx.service;

import com.hhx.entity.User;
import com.hhx.entity.condition.UserQueryCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getById(Integer id);

    User getByName(String username);

    User save(User user);

    User updateUser(User user);

    void deleteById(Integer id);

    Page<User> getPage(Pageable pageable);
}
