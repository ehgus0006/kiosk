package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.MenuDTO;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;
    private final MenuRepository menuRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/menuResister")
    public String menuResiter() {
        return "/menu/menuResister";
    }

    @GetMapping("/menuList")
    public String menuList(Model model) {
        List<MenuDTO> menuList = menuService.menuList();
        model.addAttribute("menuList", menuList);

        return "/menu/menuList";
    }
//                <a th:href="@{/menuUpdate(menu_code = ${menus.menu_code})}">메뉴수정하기</a>
    @GetMapping("/menuUpdate")
    public String menuUpdate(Long menu_code, Model model) {
        log.info("업데이트 들어옴" + menu_code);

        Menu menu = menuRepository.findById(menu_code).get();
        model.addAttribute("menu", menu);

        return "/menu/menuUpdate";
    }


}
