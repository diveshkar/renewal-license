package com.finalproject.mvpass.repository;

import com.finalproject.mvpass.entity.LicenseData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseDatasRepository extends JpaRepository<LicenseData,Long> {
    LicenseData findByLicenseNo(String licenceno);

    LicenseData findByLicenseNoAndNic(String userLicenseNo, String userNicNo);

//    LicenseData findByNic(String user);

//    LicenseData findByLicenseNo(String user);
}
