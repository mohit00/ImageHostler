package com.upgrad.imagehostler.Model;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="image")
public class Image {
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    @Type(type="text")
    private String imageFile;

    private String description;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public List<com.upgrad.imagehostler.Model.tags> getTags() {
        return tags;
    }

    public void setTags(List<com.upgrad.imagehostler.Model.tags> tags) {
        this.tags = tags;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    private List<tags> tags = new ArrayList<>();


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image(Integer id, String title, String imageFile, String description, Date date) {
        this.id = id;
        this.title = title;
        this.imageFile = imageFile;
        this.description = description;
        this.date = date;
    }

    public Image() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
