package com.totra.sns.post.domain;

import com.totra.sns.user.domain.User;

public class PostJoin {
	
	private Post post;
	
	private User user;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
