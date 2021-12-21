package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.dto.BuyerDTO;
import com.dohyeon.kiosk.entity.Admin;
import com.dohyeon.kiosk.repository.AdminRepository;
import com.dohyeon.kiosk.service.AdminService;
import com.dohyeon.kiosk.validation.JoinUp;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final AdminService adminService;

    @GetMapping("/loginPage")
    public String login() {


        return "/admin/loginPage";
    }


    @GetMapping("/adminJoinPage")
    public String adminJoin() {
        return "/admin/adminJoinPage";
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        return "/admin/adminPage";
    }
//    admin/adminLogout

    // 로그아웃
    @GetMapping("/adminLogout")
    public String logout(HttpServletRequest request) {

        HttpSession session = (HttpSession) request.getSession();
        session.removeAttribute("admin_code");
        session.invalidate();
        return "redirect:/admin/loginPage";
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

    @GetMapping("/payManagement")
    public String payManagement(Model model) {

        List<BuyerDTO> payList = adminService.getPayList();
        model.addAttribute("payLists", payList);

        return "/admin/payManagement";
    }

    @GetMapping("/chartManagement")
    public String chartManagement() {

        return "/admin/chartManagement";
    }

}
