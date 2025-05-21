package com.eliska.cattoprojectapi.repository;

import com.eliska.cattoprojectapi.model.PostMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMediaRepository extends JpaRepository<PostMediaEntity, Long> {
    //public PostMediaEntity getById(long id);


}
