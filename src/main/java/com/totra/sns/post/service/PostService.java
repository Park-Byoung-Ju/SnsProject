package com.totra.sns.post.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.totra.sns.common.FileManager;
import com.totra.sns.post.domain.Post;
import com.totra.sns.post.repository.PostRepository;
import com.totra.sns.user.domain.User;

@Service
public class PostService {
	
	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
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
}
