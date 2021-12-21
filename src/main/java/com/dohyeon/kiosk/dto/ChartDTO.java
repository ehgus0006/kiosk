package com.dohyeon.kiosk.dto;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChartDTO {

    private String date;
    private int totalPrice;

}
