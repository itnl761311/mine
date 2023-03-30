package com.mine.management.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDtoReq {
    private String email;
    private String password;
    private String name;
}
