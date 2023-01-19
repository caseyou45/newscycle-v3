package com.java.newscycle.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
public class Users {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "creationDate")
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_user_fk", referencedColumnName = "id")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_user_fk", referencedColumnName = "id")
    private List<Comment> comments;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public Users() {
    }

    public Users(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public Users(String name, String password, Date creationDate) {
        this.username = name;
        this.password = password;
        this.creationDate = creationDate;
    }

    public Users(Integer id, String username, String password, Date creationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}