package com.dohyeon.kiosk.controller;

import com.dohyeon.kiosk.dao.AdminMapper;
import com.dohyeon.kiosk.dto.AdminDTO;
import com.dohyeon.kiosk.dto.ChartDTO;
import com.dohyeon.kiosk.dto.QsaDTO;
import com.dohyeon.kiosk.entity.Menu;
import com.dohyeon.kiosk.repository.BuyerRepository;
import com.dohyeon.kiosk.repository.MenuRepository;
import com.dohyeon.kiosk.repository.OrderRepository;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin")
public class AdminApiController {

    private final AdminService adminService;
    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;
    private final AdminMapper adminMapper;
    private final MenuRepository menuRepository;

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

    @PostMapping("/chart")
    public Map<String, Object> chart(Model model) {

        Map<String, Object> map = new HashMap<>();

        // 총 주문완료 수
        int complete_count = orderRepository.AllOrderComplete();

        // 총 주문취소 수
        int cancel_count = orderRepository.AllOrderCancel();

        // 일별 총주문 가격
        List<ChartDTO> glancePrice = adminMapper.DayPrice();

        // 주간별 총주문 가격
        List<ChartDTO> weeklyPrice = adminMapper.weeklyPrice();
        for (ChartDTO chartDTO : weeklyPrice) {
            chartDTO.weekly(chartDTO.getStart(), chartDTO.getEnd());
        }

        // 월별 총주문 가격
        List<ChartDTO> monthlyPrice = adminMapper.MonthPrice();

        // 일별 메뉴별 판매 수량 및 매출
        List<QsaDTO> glanceMenuSales = adminMapper.GlanceSalesAnalysis();

//        for (QsaDTO qsaDTO : glanceMenuSales) {
//            System.out.println(qsaDTO.getDate());
//            System.out.println(qsaDTO.getMenu_code());
//            System.out.println(qsaDTO.getMenu_price());
//            System.out.println(qsaDTO.getMenu_quantity());
//            System.out.println(qsaDTO.getMenu_name());
//        }

        // 주간별 메뉴별 판매 수량 및 매출
        List<QsaDTO> weeklySalesAnalysis = adminMapper.WeeklySalesAnalysis();




        map.put("complete_count", complete_count);
        map.put("cancel_count", cancel_count);
        map.put("glancePrice", glancePrice);
        map.put("weeklyPrice", weeklyPrice);
        map.put("monthlyPrice", monthlyPrice);
        map.put("glanceMenuSales", glanceMenuSales);

        return map;
    }
}
