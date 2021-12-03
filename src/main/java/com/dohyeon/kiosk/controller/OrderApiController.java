package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.OrderMenuDTO;
import com.dohyeon.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/order")
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/orderMenu")
    public ResponseEntity<Long> resister(@RequestBody List<OrderMenuDTO> arr_title) {

        System.out.println(arr_title.size());


        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

}
