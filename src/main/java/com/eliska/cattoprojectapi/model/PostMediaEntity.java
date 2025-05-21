package com.eliska.cattoprojectapi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "post_media")
public class PostMediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postmedia")
    @Getter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String filepath;

//    @Getter
//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "id_post")
//    @JsonBackReference
//    private PostEntity post;

    @Getter
    @Setter
    @OneToMany(mappedBy = "media")
    @JsonBackReference
    private List<PostEntity> posts = new ArrayList<>();
}
