package com.example.blog.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Post {
    
    @JsonProperty
    private Long id;
    
    @JsonProperty
    private String title;
    
    @JsonProperty
    private String content;
    
    @JsonProperty
    private String author;
    
    @JsonProperty
    private Instant createdAt;
    
    @JsonProperty
    private Instant updatedAt;
    
    @JsonProperty
    private Boolean published;

    public Post() {
        this.published = false;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public Post(String title, String content, String author) {
        this();
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}