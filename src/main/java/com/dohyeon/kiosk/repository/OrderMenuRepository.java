package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {

}
