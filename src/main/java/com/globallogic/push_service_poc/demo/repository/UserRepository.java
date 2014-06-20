package com.globallogic.push_service_poc.demo.repository;

import com.globallogic.push_service_poc.demo.entity.User;

import java.util.List;

public interface UserRepository {

    public List<User> getUserList();

    public User getUser(Long userId);
}
