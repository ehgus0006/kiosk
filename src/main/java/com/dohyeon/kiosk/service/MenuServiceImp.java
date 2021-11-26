package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.MenuDTO;
import com.dohyeon.kiosk.entity.Admin;
import com.dohyeon.kiosk.entity.Category;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class MenuServiceImp implements MenuService{

    private final MenuRepository menuRepository;

    @Transactional
    @Override
    public Long menuResister(MenuDTO menuDTO) {



        Menu menu = Menu.builder()
                .menu_name(menuDTO.getMenu_name())
                .menu_price(menuDTO.getMenu_price())
                .img_url(menuDTO.getImg_url())
                .menu_priority(menuDTO.getMenu_priority())
                .category(Category.valueOf(menuDTO.getCategory()))
                .menu_stat("0")
                .admin(Admin.builder().admin_code(menuDTO.getAdmin_code()).build())
                .build();

        menuRepository.save(menu);

        return menu.getMenu_code();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuDTO> menuList() {

        return menuRepository.findpriority().stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
    }
}
