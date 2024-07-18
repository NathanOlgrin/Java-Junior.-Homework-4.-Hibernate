package org.example;

import org.example.entity.Post;
import org.example.entity.PostComment;
import org.example.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

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

            Users user = new Users();
            user.setId(1);
            user.setName("User1");

            post.addPostComment(postComment);
            user.addPosts(post);
            user.addPostComment(postComment);

            Transaction tx = session.beginTransaction();
            session.persist(post);
            session.persist(postComment);
            session.persist(user);
            tx.commit();
        }
    }

    private static void load(SessionFactory sessionFactory){
        try (Session session = sessionFactory.getCurrentSession()){
            Post post = session.find(Post.class, 1);
            PostComment postComment = session.find(PostComment.class, 1);
            Users user = session.find(Users.class, 1);

            System.out.println("Post(1) = " + post);
            System.out.println("PostComment(1) = " + postComment);
            System.out.println("User(1) = " + user);
        }
    }

    private static void delete(SessionFactory sessionFactory){
        try (Session session = sessionFactory.getCurrentSession()){
            Post toDeletePost = session.find(Post.class, 1);
            PostComment toDeletePostComment = session.find(PostComment.class, 1);
            Users toDeleteUser = session.find(Users.class, 1);

            Transaction tx = session.beginTransaction();
            session.remove(toDeletePost);
            session.remove(toDeletePostComment);
            session.remove(toDeleteUser            );
            tx.commit();
        }
    }

    private static void getPostComment(SessionFactory sessionFactory, int id_post){
        try(Session session = sessionFactory.getCurrentSession()){
            Post post = session.find(Post.class, id_post);
            System.out.println(post.getPostComments().toString());
        }
    }

    private static void getUserPost(SessionFactory sessionFactory, int id_user){
        try(Session session = sessionFactory.getCurrentSession()){
            Users user = session.find(Users.class, id_user);
            System.out.println(user.getPosts().toString());
        }
    }

    private static void getUserComment(SessionFactory sessionFactory, int id_user){
        try(Session session = sessionFactory.getCurrentSession()){
            Users user = session.find(Users.class, id_user);
            System.out.println(user.getPostComments().toString());
        }
    }

    private static void getListUsers(SessionFactory sessionFactory, int id_user){
        try(Session session = sessionFactory.getCurrentSession()){
            Users user = session.find(Users.class, id_user);
            List<PostComment> userPostComment = user.getPostComments();
            List<Post> posts = new ArrayList<>();
            for (PostComment postComment : userPostComment) {
                posts.add(postComment.getPost());
            }
            List<Users> users = new ArrayList<>();
            for(Post post : posts){
                users.add(post.getUsers());
            }

            System.out.println("Пользователь " + user.getName() + " оставил комментарии у пользователей " + users.toString());
        }
    }
}