package com.example.together.models;

public class Post {
    private String UID;
    private String title; // 제목
    private String content; // 게시내용
    private String writer; // 작성자
    private String createdAt;

    public Post(String UID, String title, String content, String writer){
        this.UID = UID;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public String getUID() {return UID; }
    public void setUID(String UID) {this.UID = UID;}
    public String getCreatedAt() {return createdAt;}
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt;}
    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}
    public String getContent(){return this.content;}
    public void setContent(String content){this.content = content;}
    public String getWriter(){return this.writer;}
    public void setWriter(String writer){this.writer = writer;}
}
