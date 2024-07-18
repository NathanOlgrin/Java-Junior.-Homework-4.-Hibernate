package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "users")
public class Users {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @JoinColumn
    private String name;

    @OneToMany(mappedBy = "postComment", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postComments;

    @OneToMany(mappedBy = "post", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> post) {
        this.posts = post;
    }

    public void addPostComment(PostComment postComment){
        postComment.setUsers(this);
        postComments.add(postComment);
    }

    public void addPosts(Post post){
        post.setUsers(this);
        posts.add(post);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
