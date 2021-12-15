package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.dto.BuyerDTO;
import com.dohyeon.kiosk.entity.Admin;
import com.dohyeon.kiosk.entity.Position;
import com.dohyeon.kiosk.repository.AdminRepository;
import com.dohyeon.kiosk.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final BuyerRepository buyerRepository;

    @Transactional
    @Override
    public String adminResister(AdminDTO adminDTO) {

        String encodePassword = passwordEncoder.encode(adminDTO.getPassword());


        Admin admin = Admin.builder()
                .user_id(adminDTO.getUser_id())
                .password(encodePassword)
                .position(Position.valueOf(adminDTO.getPosition()))
                .build();

        adminRepository.save(admin);

        return admin.getUser_id();
    }

    @Transactional
    @Override
    public String loginCheck(AdminDTO adminDTO) {

        String user_id = adminDTO.getUser_id();
        String password = adminDTO.getPassword();

        Optional<Admin> admin = adminRepository.loginCheck(user_id);

        if (admin.isPresent() && passwordEncoder.matches(password,admin.get().getPassword())) {
            return "1";
        }else{
            return "2";
        }
    }

    // 중복검사
    @Transactional
    @Override
    public String IdChk(String user_id) {
        Admin admin = adminRepository.findByUserId(user_id);
        if (admin != null) {
            return "YES";
        }else{
            return "NO";
        }
    }

    @Override
    public List<BuyerDTO> getPayList() {
        return buyerRepository.getList().stream()
                .map(BuyerDTO::new)
                .collect(Collectors.toList());

//        return orderRepository.findOrderIn().stream()
//                .map(OrderMenuDTO::new)
//                .collect(Collectors.toList());
    }


}
