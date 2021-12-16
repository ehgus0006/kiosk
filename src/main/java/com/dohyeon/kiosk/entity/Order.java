package com.dohyeon.kiosk.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderMenu> orderMenus = new ArrayList<>();

    //주문상태 주문중, 주문완료, 주문취소[ORDER, ORDER_COMPLETE, ORDER_END ,CANCEL]
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    // 주문 총 금액
    private int totalPrice;

    public static Order createTestOrder(List<OrderMenu> orderMenus) {
        Order order = new Order();

        for (OrderMenu orderMenu : orderMenus) {
            order.addOrderItem(orderMenu);
            order.setTotalPrice(order.getTotalPrice());

        }
        order.setStatus(OrderStatus.ORDER);


        return order;
    }

    public void addOrderItem(OrderMenu orderMenu) {
        orderMenus.add(orderMenu);
        orderMenu.setOrder(this);
    }

    // 생성 메서드
    public static Order createOrder(OrderMenu... orderMenus) {

        Order order = new Order();

        for (OrderMenu orderMenu : orderMenus) {
            order.addOrderItem(orderMenu);
        }
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    // 주문 취소
    public void cancel() {

        this.setStatus(OrderStatus.CANCEL);
        for (OrderMenu orderMenu : orderMenus) {
            orderMenu.cancel();
        }
    }

    // 전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderMenu orderMenu : orderMenus) {
            totalPrice += orderMenu.getTotalPrice();
        }
        return totalPrice;
    }

}
