package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.*;
import com.dohyeon.kiosk.entity.*;
import com.dohyeon.kiosk.repository.BuyerRepository;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.repository.OrderMenuRepository;
import com.dohyeon.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImp implements OrderService {

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;
    private final OrderMenuRepository orderMenuRepository;

    List<OrderMenu> testOrder = null;

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

        log.info("orderService order메소드" + menu_code);

        // 엔티티 조회
        Menu menu = menuRepository.findById(menu_code).get();

        // 주문 상품 생성 데이터 쌓여야함
        OrderMenu orderMenu = OrderMenu.createOrderMenu(menu, menu.getMenu_price(), count);
        //주문 생성
        Order order = Order.createOrder(orderMenu);

        //주문 저장
        orderRepository.save(order);

        log.info("order" + order.getId());

        List<OrderMenu> orderMenus = order.getOrderMenus();

        System.out.println(orderMenus.stream().map(OrderDTO::new).collect(Collectors.toList()));

        return order.getId();
    }

    @Override
    public Long orderTest(List<OrderMenuDTO> orderMenuList) {

        List<OrderMenu> orderMenus = new ArrayList<>();
        for (OrderMenuDTO orderMenuDTO : orderMenuList) {
            Menu menu = menuRepository.findById(orderMenuDTO.getMenu_code()).get();
            OrderMenu orderMenu = OrderMenu.createTestOrder(menu, orderMenuDTO.getRcount());
            orderMenus.add(orderMenu);
        }
        // Order Entity 클래스에 존재하는 createOrder 메소드로 Order 생성 및 저장
        Order order = Order.createTestOrder(orderMenus);
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public List<OrderListDTO> orderIn() {
        return orderRepository.findOrderIn().stream()
                .map(OrderListDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void refundDel(Long buyer_id, Long order_id) {
        buyerRepository.updateInfo(buyer_id, order_id);
        orderRepository.orderCancel(order_id);
    }

    @Override
    public void payment(BuyerDTO buyerDTO) {

        Order order = orderRepository.findById(buyerDTO.getOrder_id()).get();

        Buyer buyerPt = Buyer.builder()
                .imp_uid(buyerDTO.getImp_uid())
                .merchant_uid(buyerDTO.getMerchant_uid())
                .pay_method(buyerDTO.getPay_method())
                .all_price(buyerDTO.getAll_price())
                .apply_num(buyerDTO.getApply_num())
                .bt_cancel(buyerDTO.getBt_cancel())
                .order(order)
                .build();

        buyerRepository.save(buyerPt);
    }




    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findById(orderId).get();
        //주문 취소
        order.cancel();
    }

}
