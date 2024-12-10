package com.totra.sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.totra.sns.post.domain.Post;
import com.totra.sns.user.domain.User;

@Mapper
public interface PostRepository{

	public List<Post> timeline();
	
	public int setBoardCreate(@Param("userId") int userId
							, @Param("contents") String contents
							, @Param("imagePath") String imagePath);
	
	public User readUser(@Param("id") int id);
	
	public Integer likeCount(@Param("postId") int postId);
	
	public Integer likeSelect(@Param("userId") int userId
						, @Param("postId") int postId);
	
	public int addLike(@Param("userId") int userId
					, @Param("postId") int postId);
	
	public void deleteLike(@Param("userId") int userId
						, @Param("postId") int postId);
}
