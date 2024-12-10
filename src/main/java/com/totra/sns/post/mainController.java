package com.totra.sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.totra.sns.post.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/main")
@Controller
public class mainController {
	
	private PostService postService;
	
	public mainController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/timeline-view")
	public String timeline(Model model
							,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		
		List<Object> resultList = postService.timelineList(userId);
		
		
		if(resultList != null) {
			model.addAttribute("resultList" ,resultList);
			model.addAttribute("result", true);
		}else {
			model.addAttribute("result", false);
		}
		
		model.addAttribute("userNickname", session.getAttribute("userNickname"));
		
		
		return "main/timeline";
	}
	
	@GetMapping("/post-view")
	public String post(Model model
					,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userNickname = (String) session.getAttribute("userNickname");
		
		model.addAttribute("userNickname", userNickname);
		
		return "main/post";
	}
}
