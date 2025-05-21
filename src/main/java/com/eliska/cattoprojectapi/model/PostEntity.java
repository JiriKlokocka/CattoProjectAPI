package com.eliska.cattoprojectapi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Table(name = "posts")
@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    @Getter
    private Long id;

    @Getter
    @Setter
    private String Text;


    @Getter
    @Setter
    private Instant Sharedate;

    @Getter
    @Setter
    private Long Likecount;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

//    @Getter
//    @Setter
//    @OneToMany(mappedBy = "post")
//    private List<PostMediaEntity> media = new ArrayList<>();

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "id_postmedia")
    //@JsonBackReference
    private PostMediaEntity media;

}
