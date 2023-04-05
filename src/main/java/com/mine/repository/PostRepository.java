package com.mine.repository;

import com.mine.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
    Post findPostById(int id);
    Page<Post> findAllByUserId(int userId, Pageable pageable);
}
