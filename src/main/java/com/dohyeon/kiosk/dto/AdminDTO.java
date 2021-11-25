package com.dohyeon.kiosk.dto;

import com.dohyeon.kiosk.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private String user_id;
    private String password;
    private String position;
}
