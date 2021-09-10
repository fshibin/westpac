package com.thefans.ecommerce.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.thefans.ecommerce.model.Comment;

@Controller
public class WelcomeController {
	@RequestMapping(value = {"/", "/posts"})
	public String posts() {
		System.out.println("accessing /posts...");
		return "posts";
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	@GetMapping(value = {"/comments/{id}"})
	public String comments(@PathVariable("id") String id, ModelMap map) {
		System.out.println("accessing /comments/" + id + "...");
		// TODO get comments
		// put returned lists into model
		// access model in thymeleaf template file
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://jsonplaceholder.typicode.com/posts/" + id + "/comments";
		ResponseEntity<Comment[]> response = restTemplate.getForEntity(url, Comment[].class);
		Comment[] comments = response.getBody();
		map.addAttribute("comments", comments);
		System.out.println(comments.length);
		return "comments";
	}

}
