package com.mine.client.dto;

import com.mine.util.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDtoReq extends Request {
    private int postId;
    private int userId;
    private String content;
    private String title;
}
