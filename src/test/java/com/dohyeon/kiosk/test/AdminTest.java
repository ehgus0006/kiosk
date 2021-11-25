package com.dohyeon.kiosk.test;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.entity.Position;
import com.dohyeon.kiosk.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void adminTest() {

        AdminDTO dto = new AdminDTO("admin", "123456789", "MANAGER");

        adminService.adminResister(dto);

    }
}
