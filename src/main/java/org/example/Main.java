package org.example;

import org.example.entity.Post;
import org.example.entity.PostComment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory()){
            add(sessionFactory);
            load(sessionFactory);
        }
    }

    private static void add(SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()){
            Post post = new Post();
            post.setId(1);
            post.setTitle("Title-1");

            PostComment postComment = new PostComment();
            postComment.setId(1);
            postComment.setText("Text-1-Post-1");

            post.addPostComment(postComment);

            Transaction tx = session.beginTransaction();
            session.persist(post);
            session.persist(postComment);
            tx.commit();
        }
    }

    private static void load(SessionFactory sessionFactory){
        try (Session session = sessionFactory.getCurrentSession()){
            Post post = session.find(Post.class, 1);
            PostComment postComment = session.find(PostComment.class, 1);

            System.out.println("Post(1) = " + post);
            System.out.println("PostComment(1) = " + postComment);
        }
    }

    private static void delete(SessionFactory sessionFactory){
        try (Session session = sessionFactory.getCurrentSession()){
            Post toDeletePost = session.find(Post.class, 1);
            PostComment toDeletePostComment = session.find(PostComment.class, 1);

            Transaction tx = session.beginTransaction();
            session.remove(toDeletePost);
            session.remove(toDeletePostComment);
            tx.commit();
        }
    }
}