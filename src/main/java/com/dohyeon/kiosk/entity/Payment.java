package com.dohyeon.kiosk.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "buyer_pt_id")
    private Long id;

    // 고유ID
    private String imp_uid;
    // 상점 거래 ID
    private String merchant_uid;
    //결제 방법
    private String pay_method;
    // pt 실제 결제되는 가격
    private String pt_amount;
    // 카드 승인번호
    private String apply_num;

    // 결제 취소  default 0 결제완료 , 1 결제거절,   2 승인대기
    private int bt_cancel;
}
