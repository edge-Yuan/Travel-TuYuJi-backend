package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO implements java.io.Serializable{
    private Long userId;
    private String username;
    private String realName;
    private String phone;
    private String email;
    private Byte status;
    private int userRole;
    private String token;

}
