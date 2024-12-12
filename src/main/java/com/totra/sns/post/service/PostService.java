package com.totra.sns.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.totra.sns.common.FileManager;
import com.totra.sns.post.domain.Comment;
import com.totra.sns.post.domain.Post;
import com.totra.sns.post.domain.Timeline;
import com.totra.sns.post.repository.PostRepository;
import com.totra.sns.user.domain.User;
import com.totra.sns.user.repository.UserRepository;

@Service
public class PostService {
	
	private PostRepository postRepository;
	
	private UserRepository userRepository;

	public PostService(PostRepository postRepository
					,UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	public boolean setBoardCreate(int userId, String contents, MultipartFile imagePath) {
		String imageEncoding;
		
		if(imagePath == null) {
			imageEncoding = null;
		}else {
			imageEncoding = FileManager.saveFile(userId, imagePath);			
		}
		
		int count = postRepository.setBoardCreate(userId, contents, imageEncoding);
		
		if(count == 1) {
			return true;
		}
		
		return false;
	}
	
	public List<Timeline> timelineList(int userId){
		
		List<Post> resultList = postRepository.timeline();
		
		List<Timeline> timelineList = new ArrayList<>();
		
		for(int i = 0; i < resultList.size(); i++) {
			Timeline timeline = new Timeline(); // timeline => post, user, like정보, 댓글 저장
			
			User user = userRepository.readUser(resultList.get(i).getUserId());
			
			Integer likeCount = postRepository.likeCount(resultList.get(i).getId());
			
			Integer likeIsTrue = postRepository.likeSelect(userId, resultList.get(i).getId());
			
			List<Comment> commentList = postRepository.postIdByCommentList(resultList.get(i).getId());
			
			for(int j = 0; j < commentList.size(); j++) {
				int commentUserId = commentList.get(j).getUserId();
				User commentUser = userRepository.readUser(commentUserId);
				commentList.get(j).setUser(commentUser);
			}
			
			timeline.setPost(resultList.get(i));
			timeline.setUser(user);
			timeline.setCommentList(commentList);
			timeline.setCommentCount(commentList.size());
			
			if(likeCount == null) {
				likeCount = 0;
			}
			
			timeline.setLikeCount(likeCount);
			
			if(likeIsTrue == null || likeIsTrue <= 0) {
				timeline.setLikeIsTrue(false);
			}else {
				timeline.setLikeIsTrue(true);
			}
			
			timelineList.add(timeline);
		}
		
		return timelineList;
	}
	
/*	
	//map 사용 -> timeline으로 변경
	public List<Object> timelineList(int userId){
	
		List<Post> resultList  = postRepository.timeline();
		
		List<Object> result = new ArrayList<>();
		
		for(int i = 0; i < resultList.size(); i++) {
			User user = postRepository.readUser(resultList.get(i).getUserId());
			
			Integer like = postRepository.likeCount(resultList.get(i).getId());
			
			Integer likeIsTrue = postRepository.likeSelect(userId, resultList.get(i).getId());
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("user", user);
			resultMap.put("post", resultList.get(i));
			
			if(like == null || (int)like <= 0) {
				resultMap.put("likeCount", 0);
			}else {
				resultMap.put("likeCount", like);
			}
			
			if(likeIsTrue == null || likeIsTrue <= 0) {
				resultMap.put("like", false);
			}else {
				resultMap.put("like", true);
			}
			result.add(resultMap);
		}
		
		return result;
	}
*/
	
	public boolean addLike(int userId, int postId) {
		int count = postRepository.addLike(userId, postId);
		
		if(count == 1) {
			return true;
		}else {
			return false;
		}

	}
	
	public void deleteLike(int userId, int postId) {
		postRepository.deleteLike(userId, postId);
	}
	
	public boolean addComment(int userId, int postId, String comment) {
		
		int count = postRepository.addComment(userId, postId, comment);
		
		if(count == 1) {
			return true;
		}
		
		return false;
	}
}
