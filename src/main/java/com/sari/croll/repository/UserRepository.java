package com.sari.croll.repository;

import java.util.List;

import com.sari.croll.model.User;

public interface UserRepository {
User findByid(String username, String password);
}
