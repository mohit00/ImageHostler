package com.upgrad.imagehostler.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tags")
public class tags {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String tag_name;

    public List<Image> getImage() {

        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }
    public tags() {
    }

    public tags(String tagName) {
        this.tag_name = tagName;
    }
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private List<Image> image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

 }
