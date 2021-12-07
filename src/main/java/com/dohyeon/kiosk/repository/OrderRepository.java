package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Order;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {

    @Query("select o from Order o")
    List<Order> findOrderIn();
}
