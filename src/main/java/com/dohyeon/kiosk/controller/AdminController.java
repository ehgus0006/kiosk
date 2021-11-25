package com.dohyeon.kiosk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/loginPage")
    public String login() {


        return "/admin/loginPage";
    }

    @PostMapping("/signIn")
    public String signIn() {
        log.info("들어옴");
        return "/admin/adminPage";
    }

}
