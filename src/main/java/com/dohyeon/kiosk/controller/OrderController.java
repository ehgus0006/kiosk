package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.MenuDTO;
import com.dohyeon.kiosk.dto.OrderDTO;
import com.dohyeon.kiosk.dto.OrderMenuDTO;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import com.dohyeon.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orderMenu")
    public String orderMenu(Model model) {

        List<MenuDTO> allMenu = orderService.findAllMenu();
        model.addAttribute("allMenu", allMenu);

        return "/order/orderMenu";
    }

    @GetMapping("/orderIn")
    public String orderIn(Model model) {
        List<OrderMenuDTO> orderMenuDTOList = orderService.orderIn();
        log.info(orderMenuDTOList);
        for (OrderMenuDTO order : orderMenuDTOList) {
            log.info(order);
        }

        model.addAttribute("orderMenuList", orderMenuDTOList);

        return "/order/orderIn";
    }

}
