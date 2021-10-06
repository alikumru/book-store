package com.manage.bookstore.user;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    @Column(unique=true)
    private String email;
    private String phone;

    public User() {

    }

    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
    }
}
