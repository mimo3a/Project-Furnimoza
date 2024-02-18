package com.mimoza.furnimoza.Controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mimoza.furnimoza.Models.Post;
import com.mimoza.furnimoza.Repo.PostRepository;

@Controller
public class BlogController {
	
	@Autowired
	private PostRepository repository;
	
	@GetMapping("/blog")
	public String blog(Model model) {
	Iterable <Post> posts = repository.findAll();
	model.addAttribute("posts", posts);
	
		return "blog-main";
	}
	
	@GetMapping("/blog/add")
	public String blogAdd(Model model) {
		System.out.println("add");
		return "blog-add";
	}
	@PostMapping("/blog/add")
	public String blogPostAdd(@RequestParam String title, @RequestParam String anonce, @RequestParam String fulltext) {
		Post post = new Post(title, anonce, fulltext);
		repository.save(post);
		return "redirect:/blog";
	}
	
	@GetMapping("/blog/{id}")
	public String blogDetails(@PathVariable (value="id") long id, Model model) {
		if(!repository.existsById(id)) {
			return "redirect:/blog";
		}
		
		Optional<Post> post = repository.findById(id);
		ArrayList<Post> res = new ArrayList<>();
		post.ifPresent(res::add);
		model.addAttribute("post", res);
		return "blog-details";
		
	}
	
	@GetMapping("/blog/{id}/edit")
	public String blogEdit(@PathVariable (value="id") long id, Model model) {
		if(!repository.existsById(id)) {
			return "redirect:/blog";
		}
		
		Optional<Post> post = repository.findById(id);
		ArrayList<Post> res = new ArrayList<>();
		post.ifPresent(res::add);
		model.addAttribute("post", res);
		return "blog-edit";
		
	}
	
	@PostMapping("/blog/{id}/edit")
	public String blogPostEdit(@PathVariable (value="id") long id, @RequestParam String title, @RequestParam String anonce, @RequestParam String fulltext) {
		Post post = repository.findById(id).orElseThrow();
		post.setTitle(title);
		post.setAnonce(anonce);
		post.setFull_text(fulltext);
		repository.save(post);
		return "redirect:/blog";
	}
	
	@PostMapping("/blog/{id}/remove")
	public String blogPostRemove(@PathVariable (value="id") long id) {
		Post post = repository.findById(id).orElseThrow();
		
		repository.delete(post);
		return "redirect:/blog";
	}
	

}
