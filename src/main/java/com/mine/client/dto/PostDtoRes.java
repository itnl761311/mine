package com.mine.client.dto;

import com.mine.entity.Post;
import com.mine.util.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDtoRes extends Response{
    List<Post> posts;
}
