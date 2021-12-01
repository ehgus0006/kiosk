package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.MenuDTO;

import java.util.List;

public interface OrderService {
    List<MenuDTO> findAllMenu();

    Long order(Long menu_code, int count);
}
