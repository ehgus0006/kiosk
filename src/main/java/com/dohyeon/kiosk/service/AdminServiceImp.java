package com.dohyeon.kiosk.service;

import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.entity.Admin;
import com.dohyeon.kiosk.entity.Position;
import com.dohyeon.kiosk.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

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
}
