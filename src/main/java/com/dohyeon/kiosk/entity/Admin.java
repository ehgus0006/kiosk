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
public class Admin extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_code;

    private String user_id;

    private String password;

    // STAFF , SUPERVISOR , MANAGER
    @Enumerated(EnumType.STRING)
    private Position position;



}
