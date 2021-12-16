package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.config.PaymentCheck;
import com.dohyeon.kiosk.dto.BuyerDTO;
import com.dohyeon.kiosk.dto.OrderMenuDTO;
import com.dohyeon.kiosk.dto.RefundDTO;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BuyerDTO payResult(@RequestBody BuyerDTO buyerDTO) {
        log.info("승인번호는 "+ buyerDTO.getApply_num());
        buyerDTO.setBt_cancel(0);

        orderService.payment(buyerDTO);
        return buyerDTO;
    }

    // 결제환불
    @PostMapping("/cancel")
    public Boolean payResult(@RequestBody RefundDTO refundDTO) {

        PaymentCheck paymentCheck = new PaymentCheck();
        String token = paymentCheck.getImportToken();
        paymentCheck.cancelPayment(token, refundDTO.getMerchant_uid());


        Long buyer_id = refundDTO.getBuyer_id();
        Long order_id = refundDTO.getOrder_id();

        orderService.refundDel(buyer_id, order_id);
        return true;
    }

    // 주문완료
    @PostMapping("/orderComplete/{order_id}")
    public void orderComplete(@PathVariable Long order_id) {

        log.info("주문번호는 " + order_id);

        orderService.orderComplete(order_id);
    }

    @PostMapping("/orderEnd/{order_id}")
    public void orderEnd(@PathVariable Long order_id) {

        log.info("주문번호는 " + order_id);

        orderService.orderEnd(order_id);
    }



}
