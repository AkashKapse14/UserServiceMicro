package com.user.service.dao;

import com.user.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoDao extends JpaRepository<User,Integer> {
}
