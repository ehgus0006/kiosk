package com.dohyeon.kiosk.dto;

import com.dohyeon.kiosk.entity.Menu;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class QsaDTO {

    // Qsa Quarterly sales analysis 분기별매출분석

    private String date;
    private int menu_price;
    private Long menu_code;
    private int menu_quantity;
    private String menu_name;
}
