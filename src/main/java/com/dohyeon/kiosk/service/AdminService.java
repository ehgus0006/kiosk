package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.dto.MenuDTO;

public interface AdminService {

    Long adminResister(AdminDTO adminDTO);

    String loginCheck(AdminDTO adminDTO);
}
