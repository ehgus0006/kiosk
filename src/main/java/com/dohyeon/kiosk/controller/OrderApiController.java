package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.BuyerPTDTO;
import com.dohyeon.kiosk.dto.OrderDTO;
import com.dohyeon.kiosk.dto.OrderMenuDTO;
import com.dohyeon.kiosk.dto.OrderMenuListDTO;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.entity.OrderMenu;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/order")
public class OrderApiController {

    private final OrderService orderService;
    private final MenuRepository menuRepository;

    @PostMapping("/orderMenu")
    public ResponseEntity<Long> resister(@RequestBody List<OrderMenuDTO> orderMenuList) {


        log.info(orderMenuList);

        Long orderCode = orderService.orderTest(orderMenuList);

        return new ResponseEntity<>(orderCode, HttpStatus.OK);
    }

    // 결제처리
    @PostMapping("/payment")
    public BuyerPTDTO payResult(@RequestBody BuyerPTDTO buyerPTDTO) {
        buyerPTDTO.setBt_cancel(0);
        orderService.payment(buyerPTDTO);
        return buyerPTDTO;
    }

}
