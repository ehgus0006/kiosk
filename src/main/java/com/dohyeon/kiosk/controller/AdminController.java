package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.entity.Admin;
import com.dohyeon.kiosk.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;

    @GetMapping("/loginPage")
    public String login() {


        return "/admin/loginPage";
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        return "/admin/adminPage";
    }

    @PostMapping("/signIn")
    public String signIn(String user_id, HttpServletRequest request, HttpServletResponse response, Model model) {
        log.info("들어옴"+ user_id);

        Optional<Admin> data = adminRepository.loginCheck(user_id);
        Admin admin = data.get();
        Long admin_code = admin.getAdmin_code();
        HttpSession session = (HttpSession) request.getSession();
        session.setAttribute("admin_code", admin_code);

        return "redirect:/admin/adminPage";
    }

    @GetMapping("/admin/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

}
