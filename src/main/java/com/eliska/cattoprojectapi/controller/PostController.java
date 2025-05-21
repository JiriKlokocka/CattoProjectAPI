package com.eliska.cattoprojectapi.controller;

import com.eliska.cattoprojectapi.CattoProjectApiApplication;
import com.eliska.cattoprojectapi.model.PostEntity;
import com.eliska.cattoprojectapi.model.UserEntity;
import com.eliska.cattoprojectapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/getall")
    public List<PostEntity> GetAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/getpostsofuser/{userId}")
    public List<PostEntity> searchAllByUserId(@PathVariable(required = true) Long userId){
        return postService.getPostsByUserId(userId);
    }

    @GetMapping("/getpostbypostid/{postId}")
    public PostEntity getPostByPostId(@PathVariable(required = true) Long postId){
        return postService.getPostByPostId(postId);
    }


    @PostMapping("/createorsavepost")
    @ResponseBody
    public ResponseEntity createOrSavePost(RequestEntity<PostEntity> requestEntity)
    {
        CattoProjectApiApplication.MyLog("createorsavepost endpoint called");
        try {
            PostEntity postEntity = postService.createOrSavePostFromPostEntity(requestEntity.getBody());

            return ResponseEntity.status(HttpStatus.CREATED).body(postEntity);

        } catch (Exception e) {
            CattoProjectApiApplication.MyLog(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
