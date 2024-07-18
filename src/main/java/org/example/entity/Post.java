package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @JoinColumn
    private String title;

    @OneToMany(mappedBy = "postComment", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> postComments;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void addPostComment(PostComment postComment){
        postComment.setPost(this);
        postComments.add(postComment);
    }
    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postComment=" + postComments +
                '}';
    }
}
