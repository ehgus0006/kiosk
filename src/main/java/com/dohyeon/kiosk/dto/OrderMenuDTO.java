package com.dohyeon.kiosk.dto;


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


    List<OrderMenuDTO> arr_title = new ArrayList<>();
    // 메뉴이름
    private String name;

    // 메뉴가격
    private int price;

    // 수
    private int rcount;


}
