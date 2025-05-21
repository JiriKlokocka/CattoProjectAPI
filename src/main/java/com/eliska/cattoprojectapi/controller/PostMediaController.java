package com.eliska.cattoprojectapi.controller;


import com.eliska.cattoprojectapi.model.PostMediaEntity;
import com.eliska.cattoprojectapi.service.PostMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class PostMediaController {
    @Autowired
    PostMediaService postMediaService;

    @GetMapping("getall")
    public List<PostMediaEntity> getAll(){
        return postMediaService.getall();
    }

    @GetMapping("getbyid/{mediaId}")
    public PostMediaEntity getById(@PathVariable("mediaId") long mediaId){
        return postMediaService.getById(mediaId);
    }


}
