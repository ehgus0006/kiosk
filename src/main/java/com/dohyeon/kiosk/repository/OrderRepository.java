package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {

}
