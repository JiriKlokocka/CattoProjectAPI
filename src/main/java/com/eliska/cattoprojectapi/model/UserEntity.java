package com.eliska.cattoprojectapi.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NonNull
    @NotBlank
    @Column(name = "nickname", unique = true, nullable = false, length = 30)
    private String username;

    @Getter
    @Setter
    private String Lname;

    @Getter
    @Setter
    private String Fname;

    @Getter
    @Setter
    //@Column(name = "lastlogon")
    private Instant Lastlogon;

    @Getter
    @Setter
    @Column(name = "passwrd")
//    @JsonIgnore
    private String Password;

    @Getter
    @Setter
    private String Phonenum;

    @Getter
    @Setter
    private String Email;

    @Getter
    @Setter
    private String BAN;

    @Getter
    @Setter
    private Long IdAddress;

    @Getter
    @Setter
    private Long IdUsertype;

//    @Getter
//    @Setter
//    @OneToMany(mappedBy = "user")
//    private List<PostEntity> posts = new ArrayList<>();
}
