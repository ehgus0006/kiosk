package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {


    @Query("select m from Menu m order by m.menu_priority asc")
    List<Menu> findpriority();

    @Query("select m from Menu m where m.menu_stat='0' order by m.menu_priority asc")
    List<Menu> findAllMenu();

    @Query("select m from Menu m where m.menu_stat='0' and m.category='SPAGHETTI' order by m.menu_priority asc")
    List<Menu> findMenuPasta();

    @Query("select m from Menu m where m.menu_stat='0' and m.category='SIDE' order by m.menu_priority asc")
    List<Menu> findMenuSide();

    @Query("select m from Menu m where m.menu_stat='0' and m.category='PIZZA' order by m.menu_priority asc")
    List<Menu> findMenuPizza();

    @Query("select m from Menu m where m.menu_stat='0' and m.category='SPECIAL' order by m.menu_priority asc")
    List<Menu> findMenuSpecial();
}
