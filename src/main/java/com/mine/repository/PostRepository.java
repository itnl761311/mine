package com.mine.repository;

import com.mine.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int id);

    @Query(value = "SELECT post FROM Post post WHERE post.userId = :userId OR post.id IN (SELECT post.id FROM Post post INNER JOIN SharePost sharepost ON post.id = sharepost.postId AND sharepost.userId =:userId)")
    Page<Post> findAllByUserId(@Param("userId") int userId, Pageable pageable);
}
