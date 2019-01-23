package com.test.jdbc;

import java.util.Date;

public class Passage {

	 private int id;
     private String username;
     private String email;
     private String keyword;
     private int age;
     // 这个模拟的是文章
     private String content;
     private Date addDate;

     public Passage(int id, String username, String email, String keyword, int age, String content, Date addDate) {
         super();
         this.id = id;
         this.username = username;
         this.email = email;
         this.keyword = keyword;
         this.age = age;
         this.content = content;
         this.addDate = addDate;
     }

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getKeyword() {
         return keyword;
     }

     public void setKeyword(String keyword) {
         this.keyword = keyword;
     }

     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }

     public String getContent() {
         return content;
     }

     public void setContent(String content) {
         this.content = content;
     }

     public Date getAddDate() {
         return addDate;
     }

     public void setAddDate(Date addDate) {
         this.addDate = addDate;
     }
 
}
