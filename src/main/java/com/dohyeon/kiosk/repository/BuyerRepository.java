package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

}
