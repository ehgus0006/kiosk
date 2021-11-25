package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {


}
