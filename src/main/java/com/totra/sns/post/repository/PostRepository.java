package com.totra.sns.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.totra.sns.post.domain.Comment;
import com.totra.sns.post.domain.Post;

@Mapper
public interface PostRepository{
	// 타임라인 데이터
	public List<Post> timeline();
	// 게시글 조회
	@Transactional
	public Post getPostById(@Param("postId") int postId);
	
	// 게시글 게시
	public int setBoardCreate(@Param("userId") int userId
							, @Param("contents") String contents
							, @Param("imagePath") String imagePath);
	// 좋아요 숫자
	public Integer likeCount(@Param("postId") int postId);
	// 좋아요 누른 여부
	public Integer likeSelect(@Param("userId") int userId
						, @Param("postId") int postId);
	// 좋아요 추가
	public int addLike(@Param("userId") int userId
					, @Param("postId") int postId);
	// 좋아요 취소
	public void deleteLike(@Param("userId") int userId
						, @Param("postId") int postId);
	// 댓글추가
	public int addComment(@Param("userId") int userId
					,@Param("postId") int postId
					,@Param("contents") String contents);
	// 게시글에 달린 댓글 데이터
	public List<Comment> postIdByCommentList(@Param("postId") int postId);

	// 게시글 삭제
	@Transactional
	public void deletePost(@Param("postId") int postId);
	// 게시글에 달린 좋아요 삭제
	@Transactional
	public void deleteByLike(@Param("postId") int postId);
	// 게시글에 달린 댓글 삭제
	@Transactional
	public void deleteComment(@Param("postId") int postId);
	
	// 북마크 추가
	public int addBookmark(@Param("userId") int userId
						,@Param("postId") int postId);
	
	//북마크 삭제
	public void deleteBookmark(@Param("userId") int userId
							,@Param("postId") int postId);
	// 북마크 누른 여부
	public Integer bookmarkIsTrue(@Param("userId") int userId
								,@Param("postId") int postId);
	// 게시글 삭제에 사용되는 북마크 삭제
	public void deleteByPostIdBookmark(@Param("postId") int postId);

}
