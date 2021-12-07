package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.BuyerPTDTO;
import com.dohyeon.kiosk.dto.MenuDTO;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<MenuDTO> findAllMenu();

    Long order(Long menu_code, int count);

    void payment(BuyerPTDTO buyerPTDTO);

    void testOrder(List<Map<String, Object>> orderMenuList);


}
