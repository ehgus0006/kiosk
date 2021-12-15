package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.dto.BuyerDTO;

import java.util.List;

public interface AdminService {

    String adminResister(AdminDTO adminDTO);

    String loginCheck(AdminDTO adminDTO);

    String IdChk(String user_id);

    List<BuyerDTO> getPayList();
}
