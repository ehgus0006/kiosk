package com.dohyeon.kiosk.dto;


import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {




    private Menu menu;
    private Order order;
    private LocalDateTime regDate;

    List<OrderMenu> orderMenus = new ArrayList<>();


    public OrderListDTO(Order order) {
        this.order = order;
        this.orderMenus = order.getOrderMenus();
    }
}
