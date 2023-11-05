package com.finalproject.mvpass.repository;

import com.finalproject.mvpass.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByNic(String nic);

    Optional<User> findOneByNicAndPassword(String nic, String encodedPassword);

    boolean existsByEmail(String email);

    boolean existsByMobile(int mobile);
}
