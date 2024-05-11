package com.fiipractic.model;

public class Reply extends Post {
    private Post parent;
    private Boolean isPublic; //daca il vede doar cel care a facut postarea sau si ceilalti useri
    private User user;
    private String message;
    private Long timestamp;

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "parent=" + parent +
                ", isPublic=" + isPublic +
                ", user=" + user +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
