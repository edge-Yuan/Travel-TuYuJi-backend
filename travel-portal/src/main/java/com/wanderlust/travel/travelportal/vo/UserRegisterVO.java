package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterVO {
    private String username;
    private String realName;
    private String phone;
    private String email;
    private int userRole;
    private LocalDateTime createTime;
}
