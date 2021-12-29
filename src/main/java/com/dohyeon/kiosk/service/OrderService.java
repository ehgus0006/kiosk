package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderService {
    List<MenuDTO> findAllMenu();

    Long order(Long menu_code, int count);

    void payment(BuyerDTO buyerDTO);


    Long orderTest(List<OrderMenuDTO> orderMenuList);

    List<OrderListDTO> orderIn();

    void refundDel(Long buyer_id, Long order_id);


    void orderComplete(Long order_id);

    void orderEnd(Long order_id);

    List<MenuDTO> findMenuPasta();

    List<MenuDTO> findMenuSpecial();

    List<MenuDTO> findMenuPizza();

    List<MenuDTO> findMenuSide();
}
