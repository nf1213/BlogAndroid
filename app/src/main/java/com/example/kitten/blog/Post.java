package com.example.kitten.blog;

/**
 * Created by kitten on 4/15/15.
 */
public class Post {
    int id;
    String subject;
    String content;
    String blogImageUrl;
    String instagramLink;

    public Post(int id, String subject, String content, String blogImageUrl, String instagramLink) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.blogImageUrl = blogImageUrl;
        this.instagramLink = instagramLink;
    }
}
