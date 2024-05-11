package com.fiipractic.model;

public class Like {
    private String id;
    private User user;
    private Post post;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
