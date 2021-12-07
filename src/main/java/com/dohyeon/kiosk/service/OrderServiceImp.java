package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.*;
import com.dohyeon.kiosk.entity.Buyer;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import com.dohyeon.kiosk.repository.BuyerRepository;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImp implements OrderService {

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;

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
    public void testOrder(List<Map<String, Object>> orderMenuList) {

        Map<String, Object> menuMap = new HashMap<>();

        for (Map<String, Object> mapKey : orderMenuList) {
            Long menu_code = Long.valueOf((String) mapKey.get("menu_code"));
            int rcount = Integer.valueOf((Integer) mapKey.get("rcount"));
            log.info("mapKey:" + mapKey.size());
            log.info("menu_code:" + menu_code);
            log.info("rcount:" + rcount);

            Menu menu = menuRepository.findById(menu_code).get();

            // 주문 상품 생성  메뉴 봉골레 2 +

            List<OrderMenu> testOrder = OrderMenu.createTestOrder(rcount, menu);
            System.out.println(testOrder.size());
//주문 생성

            List<Order> orders = null;
            for (OrderMenu orderMenu : testOrder) {
                List<Order> testOrders = Order.createTestOrder(orderMenu);
                orderRepository.saveAll(testOrders);

            }

            //주문 저장

//            for (OrderMenu orderMenu : testOrder) {
//                List<Order> testOrders = Order.createTestOrder(orderMenu);
//                orderRepository.saveAll(testOrders);
//            }

        }

    }



//    @Override
//    public void testOrder(List<OrderMenuDTO> arr_title) {
//        log.info("service 로직 실행된다");
//        // 메뉴 엔티티 조회
//        List<Menu> menuList = new ArrayList<>();
//        for (OrderMenuDTO orderMenuDTO : arr_title) {
//            Menu menu = menuRepository.findById(orderMenuDTO.getMenu_code()).get();
//            int rcount = orderMenuDTO.getRcount();
//            menuList.add(menu);
//        }
//
//        for (Menu menuDTO : menuList) {
//            log.info("menupk " + menuDTO.getMenu_code());
//        }
//        // 주문 상품 생성
//        OrderMenu orderMenu = OrderMenu.createOrderTestMenu(menuList);
//
//
//    }

    @Override
    public void payment(BuyerPTDTO buyerPTDTO) {
        Buyer buyerPt = Buyer.builder()
                .imp_uid(buyerPTDTO.getImp_uid())
                .merchant_uid(buyerPTDTO.getMerchant_uid())
                .pay_method(buyerPTDTO.getPay_method())
                .pt_amount(buyerPTDTO.getPt_amount())
                .apply_num(buyerPTDTO.getApply_num())
                .bt_cancel(buyerPTDTO.getBt_cancel())
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
