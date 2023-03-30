package com.mine.management.user.dto;

import com.mine.util.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDtoRes {
    Response response;
    String jwt;

}
