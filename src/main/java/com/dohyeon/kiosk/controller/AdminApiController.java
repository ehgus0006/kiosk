package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin")
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/loginCheck")
    public ResponseEntity<String> loginCheck(@RequestBody AdminDTO adminDTO) {


        String data = adminService.loginCheck(adminDTO);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
