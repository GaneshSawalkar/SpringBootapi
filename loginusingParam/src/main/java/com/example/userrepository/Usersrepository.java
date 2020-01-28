package com.example.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.users;

@Repository
public interface Usersrepository extends JpaRepository<users, Integer> {

}
