package com.codepath.shivagss.instapop;

/**
 * Created by Sandeep on 9/14/2014.
 */
public class InstagramPhoto {
    String username;
    String caption;
    String imageURL;
    int imageHeight;
    int likes;

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
