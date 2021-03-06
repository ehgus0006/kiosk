package com.dohyeon.kiosk.dto;

import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Menu menu;
    private Order order; // 주문
    private int orderPrice; //주문 가격
    private int count; // 주문 수량
    private List<OrderDTO> orderDTOList = new ArrayList<>();
    private LocalDateTime orderDate;

    public OrderDTO(OrderMenu orderMenu) {
        this.id = orderMenu.getOrder().getId();
        this.orderPrice = orderMenu.getOrder().getTotalPrice();
        this.count = orderMenu.getCount();
        this.menu = orderMenu.getMenu();
        this.order = orderMenu.getOrder();
        this.orderDate = orderMenu.getOrder().getRegDate();
    }


}
