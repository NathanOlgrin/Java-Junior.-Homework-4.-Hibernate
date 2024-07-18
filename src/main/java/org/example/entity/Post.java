package org.example.entity;

import jakarta.persistence.*;

import java.util.Date;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postComments=" + postComments +
                ", date=" + date +
                '}';
    }
}
