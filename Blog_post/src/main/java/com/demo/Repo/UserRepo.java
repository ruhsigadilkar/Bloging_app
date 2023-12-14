package com.demo.Repo;



import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.User;

public interface UserRepo extends JpaRepository<User,Long> {

}
