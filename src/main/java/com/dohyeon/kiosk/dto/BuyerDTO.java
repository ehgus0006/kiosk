package com.dohyeon.kiosk.dto;

import com.dohyeon.kiosk.entity.Buyer;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class BuyerDTO {
    private Long id;

    // 고유ID
    private String imp_uid;
    // 상점 거래 ID
    private String merchant_uid;
    //결제 방법
    private String pay_method;
    // 실제 결제되는 가격
    private String all_price;
    // 카드 승인번호
    private String apply_num;
    private LocalDateTime regDate;

    private int bt_cancel;

    private Long order_id;

    public BuyerDTO(Buyer entity) {
        this.id = entity.getId();
        this.imp_uid = entity.getImp_uid();
        this.merchant_uid = entity.getMerchant_uid();
        this.pay_method = entity.getPay_method();
        this.all_price = entity.getAll_price();
        this.apply_num = entity.getApply_num();
        this.order_id = entity.getOrder().getId();
        this.bt_cancel = entity.getBt_cancel();
        this.regDate = entity.getRegDate();
    }
}
