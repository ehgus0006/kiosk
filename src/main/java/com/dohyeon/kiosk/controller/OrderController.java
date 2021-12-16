package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.*;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import com.dohyeon.kiosk.repository.OrderRepository;
import com.dohyeon.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/orderMenu")
    public String orderMenu(Model model) {

        List<MenuDTO> allMenu = orderService.findAllMenu();
        model.addAttribute("allMenu", allMenu);

        return "/order/orderMenu";
    }

    @GetMapping("/orderIn")
    public String orderIn(Model model) {
        List<OrderListDTO> orderMenuDTOList = orderService.orderIn();
//        for (OrderListDTO order : orderMenuDTOList) {
//            log.info("주문번호" +order.getOrder().getId());
//            List<OrderMenu> orderMenus = order.getOrderMenus();
//            log.info(order.getOrderMenus().size());
//        }

        model.addAttribute("orderMenuList", orderMenuDTOList);

        return "/order/orderIn";
    }

    // 주문현황 주문준비중 / 주문준비완료
    @GetMapping("/orderStatus")
    public String orderStatus(Model model) {

        // 주문 준비중 번호
        List<Order> orderPrepare = orderRepository.findOrderPrepare();
        model.addAttribute("orderPrepare", orderPrepare);

        // 주문 완료 번호
        List<Order> orderEnd = orderRepository.findOrderEnd();
        model.addAttribute("orderEnd", orderEnd);

        return "/order/orderStatus";
    }




}
