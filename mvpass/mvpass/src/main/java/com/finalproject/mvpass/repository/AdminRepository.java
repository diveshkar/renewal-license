package com.finalproject.mvpass.repository;

import com.finalproject.mvpass.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByAdminname(String adminname);

    Admin findOneByAdminnameAndAdminpassword(String password, String storedPassword);
}
