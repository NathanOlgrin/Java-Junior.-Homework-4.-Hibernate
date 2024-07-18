package org.example.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "postComment")
public class PostComment {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "text")
    @JoinColumn
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    Date date;

    public PostComment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
        return "PostComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", post=" + post +
                ", date=" + date +
                '}';
    }
}
