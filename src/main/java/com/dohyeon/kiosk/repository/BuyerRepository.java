package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Buyer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    
    // 전체데이터를 뽑을때는 EntityGraphType.LOAD 즉시로딩이 효율적임 / 지연로딩을 하게되면 쿼리문 발생이 많이 일어나 성능저하가 일어남
    @EntityGraph(attributePaths = {"order"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select b from Buyer b")
    List<Buyer> getList();


    @Transactional
    @Modifying
    @Query("update Buyer b set b.bt_cancel=1 where b.id=:buyer_id and b.order.id=:order_id")
    void updateInfo(Long buyer_id, Long order_id);
}
