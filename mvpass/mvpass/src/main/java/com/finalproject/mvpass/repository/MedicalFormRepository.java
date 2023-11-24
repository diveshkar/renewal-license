package com.finalproject.mvpass.repository;

import com.finalproject.mvpass.entity.MedicalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFormRepository extends JpaRepository<MedicalForm,Long> {
    MedicalForm findByNicNumber(String user);
}
