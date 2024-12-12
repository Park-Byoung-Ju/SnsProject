package com.totra.sns.post.domain;

import java.util.List;

import com.totra.sns.user.domain.User;

public class Timeline {
	
	private User user;
	
	private Post post;
	
	private List<Comment> commentList;
	
	private int likeCount;
	
	private boolean likeIsTrue;
	
	private int commentCount;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public boolean isLikeIsTrue() {
		return likeIsTrue;
	}

	public void setLikeIsTrue(boolean likeIsTrue) {
		this.likeIsTrue = likeIsTrue;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
