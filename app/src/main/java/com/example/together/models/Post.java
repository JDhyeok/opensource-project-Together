package com.example.together.models;

public class Post {
    private String title; // 제목
    private String content; // 게시내용
    private String writer; // 작성자

    public Post(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}
    public String getContent(){return this.content;}
    public void setContent(String content){this.content = content;}
    public String getWriter(){return this.writer;}
    public void setWriter(String writer){this.writer = writer;}
}
