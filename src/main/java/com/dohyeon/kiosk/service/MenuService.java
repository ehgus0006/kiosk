package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.MenuDTO;

import java.util.List;

public interface MenuService {

    Long menuResister(MenuDTO menuDTO);

    List<MenuDTO> menuList();

    void menuUpdate(MenuDTO menuDTO);

    void menuRemove(Long menu_code);
}
