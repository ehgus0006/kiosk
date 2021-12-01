package com.dohyeon.kiosk.dto;

import com.dohyeon.kiosk.entity.Admin;
import com.dohyeon.kiosk.entity.Category;
import com.dohyeon.kiosk.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Long menu_code;
    private String menu_name;
    private int menu_price;
    private String real_img_url;
    private String img_url;
    private String menu_stat;
    private int menu_priority;
    private int stockQuantity;
    private String category;
    private Long admin_code;
    private LocalDateTime regDate, modDate;


    public MenuDTO(Menu menu) {
        this.menu_code = menu.getMenu_code();
        this.menu_name = menu.getMenu_name();
        this.menu_price = menu.getMenu_price();
        this.img_url = menu.getImg_url();
        this.real_img_url = menu.getReal_img_url();
        this.menu_stat = menu.getMenu_stat();
        this.menu_priority = menu.getMenu_priority();
        this.category = String.valueOf(menu.getCategory());
        this.admin_code = menu.getMenu_code();
        this.stockQuantity = menu.getStockQuantity();
    }

}
