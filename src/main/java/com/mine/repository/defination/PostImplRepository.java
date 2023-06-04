package com.mine.repository.defination;

import com.mine.entity.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostImplRepository implements IPostRepository {

    @Override
    public List<Post> searchPostByCondition(String condition, EntityManager entityManager) {
        return entityManager.createQuery(condition,Post.class).getResultList();
    }
}
