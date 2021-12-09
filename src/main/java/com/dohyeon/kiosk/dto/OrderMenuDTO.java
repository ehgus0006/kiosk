package com.dohyeon.kiosk.dto;


import com.dohyeon.kiosk.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMenuDTO {



    // 메뉴코드
    private Long menu_code;

    // 메뉴이름
    private String name;

    // 메뉴가격
    private int price;

    // 수
    private int rcount;

    private int total;


}
