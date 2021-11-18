package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.MenuDTO;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class MenuServiceImp implements MenuService{

    private final MenuRepository menuRepository;

    @Override
    public Long menuResister(MenuDTO menuDTO) {

        Menu menu = Menu.builder()
                .menu_name(menuDTO.getMenu_name())
                .menu_price(menuDTO.getMenu_price())
                .img_url(menuDTO.getImg_url())
                .menu_stat("0")
                .build();

        menuRepository.save(menu);

        return menu.getMenu_code();
    }

    @Override
    public List<MenuDTO> menuList() {

        return menuRepository.findAll().stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
    }
}
