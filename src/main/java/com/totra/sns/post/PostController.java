package com.totra.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class PostController {

	@RequestMapping("/timeline-view")
	public String list() {
		return "main/timeline";
	}
}
