package com.dohyeon.kiosk.dto;

import com.dohyeon.kiosk.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private String menu_name;
    private int menu_price;
    private String img_url;
    private String menu_stat;
    private LocalDateTime regDate, modDate;

    public MenuDTO(Menu menu) {
        this.menu_name = menu.getMenu_name();
        this.menu_price = menu.getMenu_price();
        this.img_url = menu.getImg_url();
        this.menu_stat = menu.getMenu_stat();
    }

}
