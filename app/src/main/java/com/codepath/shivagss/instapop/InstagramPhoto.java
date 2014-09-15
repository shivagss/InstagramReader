package com.codepath.shivagss.instapop;

import java.util.ArrayList;

/**
 * Created by Sandeep on 9/14/2014.
 */
public class InstagramPhoto {
    String username;
    String caption;
    String imageURL;
    int imageHeight;
    int likes;
    String profilePicURL;
    String submittedTime;
    int commentsCount;
    ArrayList<String> comments = new ArrayList<String>();

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public String getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(String submittedTime) {
        this.submittedTime = submittedTime;
    }

    @Override
    public String toString() {
        return "InstagramPhoto{" +
                "username='" + username + '\'' +
                ", caption='" + caption + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", imageHeight='" + imageHeight + '\'' +
                ", likes=" + likes +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
