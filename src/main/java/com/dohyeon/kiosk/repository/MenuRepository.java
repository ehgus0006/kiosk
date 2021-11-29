package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {


    @Query("select m from Menu m order by m.menu_priority asc")
    List<Menu> findpriority();

    @Query("select m from Menu m where m.menu_stat='0' order by m.menu_priority asc")
    List<Menu> findAllMenu();
}
