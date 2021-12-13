package com.dohyeon.kiosk.dto;


import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuDTO {


    // 주문코드
    private Long order_id;

    // 메뉴코드
    private Long menu_code;

    // 메뉴이름
    private String name;

    // 메뉴 총가격
    private int price;

    // 수
    private int rcount;

    private int total;

    List<OrderMenu> orderMenus = new ArrayList<>();


    public OrderMenuDTO(Order order) {
        this.orderMenus = order.getOrderMenus();
        this.price = order.getTotalPrice();
        this.order_id = order.getId();
    }

//    public MenuDTO(Menu menu) {
//        this.menu_code = menu.getMenu_code();
//        this.menu_name = menu.getMenu_name();
//        this.menu_price = menu.getMenu_price();
//        this.img_url = menu.getImg_url();
//        this.real_img_url = menu.getReal_img_url();
//        this.menu_stat = menu.getMenu_stat();
//        this.menu_priority = menu.getMenu_priority();
//        this.category = String.valueOf(menu.getCategory());
//        this.admin_code = menu.getMenu_code();
//        this.stockQuantity = menu.getStockQuantity();
//    }
}
