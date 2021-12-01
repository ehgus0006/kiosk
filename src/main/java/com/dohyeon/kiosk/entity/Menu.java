package com.dohyeon.kiosk.entity;

import com.dohyeon.kiosk.dto.MenuDTO;
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

    // 진짜 이미지 url
    private String real_img_url;

    // 섬네일 이미지 url
    private String img_url;

    // 메뉴 상태 (0 등록중 , 1 등록 x) 마이페이지 수정가능
    private String menu_stat;

//    메뉴 표시순서 1 2 3 4 5 6 // 수정  1 2 3 4 5 6  1
    private int menu_priority;
    
    // 재고량
    private int stockQuantity;

    // SPAGHETTI, SPECIAL, PIZZA, SIDE
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_code")
    private Admin admin;

    // 재고량 추가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고량 빼기 
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new IllegalArgumentException("재고가 더이상 없다");
        }
        this.stockQuantity = restStock;
    }

    public void update(MenuDTO menuDTO) {
        this.menu_name = menuDTO.getMenu_name();
        this.menu_price = menuDTO.getMenu_price();
        this.img_url = menuDTO.getImg_url();
        this.real_img_url = menuDTO.getReal_img_url();
        this.menu_stat = menuDTO.getMenu_stat();
        this.menu_priority = menuDTO.getMenu_priority();
        this.category = Category.valueOf(menuDTO.getCategory());
        this.stockQuantity = menuDTO.getStockQuantity();
    }



}
