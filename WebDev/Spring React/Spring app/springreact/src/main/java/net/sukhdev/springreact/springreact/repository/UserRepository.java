package net.sukhdev.springreact.springreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sukhdev.springreact.springreact.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
