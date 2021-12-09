package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {
}
