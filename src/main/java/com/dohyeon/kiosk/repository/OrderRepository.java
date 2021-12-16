package com.dohyeon.kiosk.repository;

import com.dohyeon.kiosk.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {



    // order id 는 무조건 1개 orderMenu 는 여러개 데이터 추출 방법
    @EntityGraph(attributePaths = {"orderMenus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select distinct o from Order o join OrderMenu om on om.order.id=o.id where o.status='ORDER'")
    List<Order> findOrderIn();


    //    @Query("update Buyer b set b.bt_cancel=1 where b.id=:buyer_id and b.order.id=:order_id")


    @Transactional
    @Modifying
    @Query("update Order o set o.status='CANCEL' where o.id=:order_id")
    void orderCancel(Long order_id);

    @Transactional
    @Modifying
    @Query("update Order o set o.status='ORDER_COMPLETE' where o.id=:order_id")
    void orderComplete(Long order_id);

    @Query("select o from Order o where o.status='ORDER'")
    List<Order> findOrderPrepare();

    @Query("select o from Order o where o.status='ORDER_COMPLETE'")
    List<Order> findOrderEnd();

    @Transactional
    @Modifying
    @Query("update Order o set o.status='ORDER_END' where o.id=:order_id")
    void OrderEnd(Long order_id);
}
