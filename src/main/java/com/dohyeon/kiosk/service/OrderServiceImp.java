package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.MenuDTO;
import com.dohyeon.kiosk.dto.OrderDTO;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImp implements OrderService{

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MenuDTO> findAllMenu() {
                return menuRepository.findAllMenu().stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
    }

    // 주문
    @Transactional
    @Override
    public Long order(Long menu_code, int count) {
        
        log.info( "orderService order메소드"+menu_code);

        // 엔티티 조회
        Menu menu = menuRepository.findById(menu_code).get();

        // 주문 상품 생성
        OrderMenu orderMenu = OrderMenu.createOrderMenu(menu, menu.getMenu_price(), count);

        log.info("orderMenu"+ orderMenu.getMenu());

        //주문 생성
        Order order = Order.createOrder(orderMenu);

        //주문 저장
        orderRepository.save(order);

        log.info("order"+order.getId());

        List<OrderMenu> orderMenus = order.getOrderMenus();

        System.out.println(orderMenus.stream().map(OrderDTO::new).collect(Collectors.toList()));

        return order.getId();
    }

    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId).get();
        //주문 취소
        order.cancel();
    }

}
