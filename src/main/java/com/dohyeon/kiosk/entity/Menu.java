package com.dohyeon.kiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Menu extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menu_code;

    private String menu_name;

    private int menu_price;

    // 제품 이미지
    private String img_url;

    // 메뉴 상태 (0 등록중 , 1 등록 x) 마이페이지 수정가능
    private String menu_stat;

//    메뉴 표시순서 1 2 3 4 5 6 // 수정  1 2 3 4 5 6  1
    private int menu_priority;

    // SPAGHETTI, SPECIAL, PIZZA, SIDE
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_code")
    private Admin admin;



}
