package com.demo.entities;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postid;
	String title;
	@Column(length = 10000)
	String content;
	String image;

	Date adddate;
	@ManyToOne
	@JoinColumn(name="category_id")
	Category category;
	@Override
	public String toString() {
		return "Post [postid=" + postid + ", title=" + title + ", content=" + content + ", image=" + image
				+ ", adddate=" + adddate + ", category=" + category + ", user=" + user + "]";
	}
	@ManyToOne
	User user;
	public Long getPostid() {
		return postid;
	}
	public void setPostid(Long postid) {
		this.postid = postid;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getAdddate() {
		return adddate;
	}
	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post(Long postid) {
		super();
		this.postid = postid;
	}
	public Post() {
		super();
	}

}
