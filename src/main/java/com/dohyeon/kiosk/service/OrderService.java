package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.*;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.entity.Order;
import com.dohyeon.kiosk.entity.OrderMenu;
import com.dohyeon.kiosk.entity.OrderStatus;
import net.bytebuddy.asm.Advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface OrderService {
    List<MenuDTO> findAllMenu();

    Long order(Long menu_code, int count);

    void payment(BuyerPTDTO buyerPTDTO);


    Long orderTest(List<OrderMenuDTO> orderMenuList);

    List<OrderMenuDTO> orderIn();
}
