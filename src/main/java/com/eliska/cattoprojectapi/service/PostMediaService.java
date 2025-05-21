package com.eliska.cattoprojectapi.service;

import com.eliska.cattoprojectapi.model.PostMediaEntity;
import com.eliska.cattoprojectapi.repository.PostMediaRepository;
import com.eliska.cattoprojectapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostMediaService {

    @Autowired
    private PostMediaRepository postMediaRepository;

    @Autowired
    private PostRepository postRepository;

    public List<PostMediaEntity> getall() {
        return postMediaRepository.findAll();
    }

    public PostMediaEntity getById(long id) {
        return postMediaRepository.findById(id).get();
    }
}
