package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {


    @Query("select o from Order o left outer join OrderMenu om on om.order.id=o.id where o.status='ORDER'")
    List<Order> findOrderIn();
}
