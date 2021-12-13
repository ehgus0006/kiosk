package com.dohyeon.kiosk.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_menu")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_code")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문

    private int orderPrice; //주문 가격
    private int count; // 주문 수량

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = CascadeType.ALL)
//    private List<Menu> menus = new ArrayList<>();
//
//    public void addOrderMenu(Menu menu) {
//        menus.add(menu);
//    }

    public static OrderMenu createOrderMenu(Menu menu, int orderPrice, int count) {

        OrderMenu orderMenu = new OrderMenu();

        orderMenu.setMenu(menu);
        orderMenu.setOrderPrice(orderPrice);
        orderMenu.setCount(count);

        menu.removeStock(count);

        return orderMenu;
    }

    public static OrderMenu createTestOrder(Menu menu, int rcount) {
            OrderMenu orderMenu = new OrderMenu();

            orderMenu.setMenu(menu);
            orderMenu.setOrderPrice(menu.getMenu_price());
            orderMenu.setCount(rcount);
            menu.removeStock(rcount);

        return orderMenu;
    }


    public void cancel() {
        getMenu().addStock(count);
    }

    /** 주문상품 전체 가격 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


}
