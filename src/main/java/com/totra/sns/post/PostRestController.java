package com.totra.sns.post;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.totra.sns.post.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	

	@PostMapping("/board/create")
	public boolean boardCreate(@RequestParam("contents") String contents
							, @RequestParam(name="imagePath", required=false) MultipartFile imagePath
							, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		return postService.setBoardCreate(userId, contents, imagePath);
	}
	
	@PostMapping("/like/create")
	public boolean addLike(@RequestParam("postId") int postId
						,HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		
		boolean isTrue = postService.addLike(userId, postId);
		
		return isTrue;
	}
	
	@DeleteMapping("/like/delete")
	public boolean deleteLike(@RequestParam("postId") int postId
						,HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		postService.deleteLike(userId, postId);
		
		return true;
	}
	
	@PostMapping("/comment/create")
	public boolean commentCreate(@RequestParam("comment") String comment
								,@RequestParam("postId") int postId
								,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		boolean isTrue = postService.addComment(userId, postId, comment);
		return isTrue;
		// return false;
	}
	
	@DeleteMapping("/board/delete")
	public boolean deletePost(@RequestParam("postId") int postId) {
		 boolean isTrue = postService.deletePost(postId);
		 
		 return isTrue;
	}
	
	@PostMapping("/bookmark/create")
	public boolean addBookmark(@RequestParam("postId") int postId
					,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		
		
		return postService.addBookmark(userId, postId);
	}
	
	@DeleteMapping("/bookmark/delete")
	public void deleteBookmark(@RequestParam("postId") int postId
			,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userId");
		
		postService.deleteBookmark(userId, postId);
	}
}
