package com.dohyeon.kiosk.dto;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChartDTO {

    private String start;
    private String end;
    private String date;
    private int totalPrice;


    public String weekly(String start, String end) {
        this.date = this.start + "~" + this.end;
        return date;
    }

}
