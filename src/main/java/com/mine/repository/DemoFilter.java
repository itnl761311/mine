package com.mine.repository;

import com.mine.entity.User;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class DemoFilter extends SimpleJpaRepository {
    public EntityManager em;
    public DemoFilter(EntityManager em) {
        super(User.class, em);
        this.em = em;
    }

    public String getU(String field){
//        this.em.createQuery("select * from tbl_user where " + field);

        System.out.println(em.createQuery("select user from User user where user.id = 1").toString());
        return "ok";
    }
}
