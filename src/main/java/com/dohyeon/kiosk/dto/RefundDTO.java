package com.dohyeon.kiosk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefundDTO {

    private String merchant_uid;
    private String all_price;
    private Long buyer_id;
    private Long order_id;
}
