package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin")
public class AdminApiController {

    private final AdminService adminService;

    // Json Id 중복검사
    @PostMapping("/idChk")
    public String IdChk(String user_id) throws Exception {

        System.out.println(user_id);
        String str = adminService.IdChk(user_id);

        return str;
    }

    @PostMapping("/adminJoinPage")
    public ResponseEntity<String> join(@RequestBody @Validated AdminDTO adminDTO, BindingResult bindingResult, Model model) {
        log.info(adminDTO);
        System.out.println("====================================");
//        // validation check
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for(ObjectError error : errorList) {
                System.out.println(error.getDefaultMessage());
                String defaultMessage = error.getDefaultMessage();
                return new ResponseEntity<>(defaultMessage, HttpStatus.BAD_REQUEST);
            }
        }
        System.out.println("====================================");


        return new ResponseEntity<>(adminService.adminResister(adminDTO), HttpStatus.OK);
    }

    @PostMapping("/loginCheck")
    public ResponseEntity<String> loginCheck(@RequestBody AdminDTO adminDTO) {


        String data = adminService.loginCheck(adminDTO);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
