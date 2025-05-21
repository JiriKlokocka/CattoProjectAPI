package com.eliska.cattoprojectapi.repository;

import com.eliska.cattoprojectapi.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> searchAllById(Long id);

    List<PostEntity> searchAllByUserIdOrderByIdDesc(Long id);

    PostEntity findPostEntityById(Long postId);
}
