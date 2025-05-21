package com.eliska.cattoprojectapi.service;

import com.eliska.cattoprojectapi.CattoProjectApiApplication;
import com.eliska.cattoprojectapi.model.PostEntity;
import com.eliska.cattoprojectapi.repository.PostRepository;
import com.eliska.cattoprojectapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<PostEntity> getAllPosts() {

        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

    }

    public List<PostEntity> getPostsByUserId(Long id) {
        return postRepository.searchAllByUserIdOrderByIdDesc(id);
    }

    public PostEntity getPostByPostId(Long id) {
        return postRepository.findPostEntityById(id);
    }

    public PostEntity createOrSavePostFromPostEntity(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }
}
