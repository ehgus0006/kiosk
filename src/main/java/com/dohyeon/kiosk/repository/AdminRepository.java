package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {


    @Query("select a from Admin a where a.user_id=:user_id")
    Optional<Admin> loginCheck(String user_id);
}
