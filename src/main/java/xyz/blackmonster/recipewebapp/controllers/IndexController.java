package xyz.blackmonster.recipewebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	private static final String INDEX = "index";
	
	@RequestMapping({"", "/", "/index"})
	public String getIndexPage(Model model) {
		model.addAttribute("value", 3);
		return INDEX;
	}
}
