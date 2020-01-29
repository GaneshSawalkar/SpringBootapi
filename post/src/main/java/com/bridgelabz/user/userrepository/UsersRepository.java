package com.bridgelabz.user.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.user.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
