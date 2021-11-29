package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.MenuDTO;
import com.dohyeon.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImp implements OrderService{

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MenuDTO> findAllMenu() {
                return menuRepository.findAllMenu().stream()
                .map(MenuDTO::new)
                .collect(Collectors.toList());
    }

}
