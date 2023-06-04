package com.mine.repository.defination;

import com.mine.entity.Post;

import javax.persistence.EntityManager;
import java.util.List;

public interface IPostRepository {
    List<Post> searchPostByCondition(String condition, EntityManager entityManager);
}
