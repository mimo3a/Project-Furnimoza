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

import com.mimoza.furnimoza.Models.Details;
import com.mimoza.furnimoza.Models.Post;
import com.mimoza.furnimoza.Repo.DetailsRepository;

@Controller
public class OrderController {
	
	@Autowired
	DetailsRepository detailsRepository;
	
	@GetMapping("/order")
	public String orderMain(Model model) {
		Iterable <Details> details = detailsRepository.findAll();
		model.addAttribute("details", details);
		
		return "order-main";
		
	}
	
	@PostMapping("/order")
	public String blogPostAdd(@RequestParam int length, @RequestParam int width, @RequestParam int thick, @RequestParam int amount, @RequestParam String material  ) {
		
		Details detail = new Details(length, width, thick, amount, material);
		detailsRepository.save(detail);
		return "redirect:/order";
	}
	
	@GetMapping("/order/{id}/edit")
	public String blogEdit(@PathVariable (value="id") long id, Model model) {
		if(!detailsRepository.existsById(id)) {
			return "redirect:/order";
		}
		
		Optional<Details> detail = detailsRepository.findById(id);
		ArrayList<Details> res = new ArrayList<>();
		detail.ifPresent(res::add);
		model.addAttribute("detail", res);
		Iterable <Details> details = detailsRepository.findAll();
		model.addAttribute("details", details);
		
		return "detail-edit";
		
	}

}
