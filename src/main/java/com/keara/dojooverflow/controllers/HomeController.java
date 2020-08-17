package com.keara.dojooverflow.controllers;

import java.util.ArrayList;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keara.dojooverflow.models.Answer;
import com.keara.dojooverflow.models.Question;
import com.keara.dojooverflow.models.Tag;
import com.keara.dojooverflow.services.AnswerService;
import com.keara.dojooverflow.services.QuestionService;
import com.keara.dojooverflow.services.TagService;

@Controller
public class HomeController {

	private final TagService tServ;
	private final QuestionService qServ;
	private final AnswerService aServ;
	
	public HomeController(TagService tServ, QuestionService qServ, AnswerService aServ) {
		this.tServ = tServ;
		this.qServ = qServ;
		this.aServ = aServ;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("questions", qServ.getAll());

		return "index.jsp";
	}
	
	@GetMapping("/newquestion")
	public String newquestion(Model model) {
		model.addAttribute("quest", new Question());
		model.addAttribute("tags", tServ.getAll());

		return "newquestion.jsp";
	}
	

	@PostMapping("/question/create")
	public String createquestion(@Valid @ModelAttribute("quest") Question question, BindingResult result, Model model, @RequestParam(value = "tags") String tags) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("tags", tServ.getAll());
			return "newquestion.jsp";
		} else {
			String[] listOfTags = tags.split(" ");
			Question newQuest = qServ.create(question);
			ArrayList<Tag> valid_tags = new ArrayList<Tag>();
			for(int i=0; i <listOfTags.length; i++) {
				valid_tags.add(tServ.create(new Tag(listOfTags[i])));
			}
			newQuest.setTags(valid_tags);
			qServ.update(newQuest);
			return "redirect:/";
		}
		
		
	}
	
	@GetMapping("/question/{id}")
	public String questionDetails(@PathVariable("id") Long q_id, Model model) {
		Question thisQuest = qServ.getOne(q_id);
		model.addAttribute("question", thisQuest);
		model.addAttribute("answ", new Answer());
		return "questionDetails.jsp";
	}
	
	@PostMapping(value = "/{id}/answer/create")
	public String addAnswerToQuestion(@Valid @ModelAttribute("answ") Answer answer, BindingResult result, Model model, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			Question thisQuest = qServ.getOne(id);
			model.addAttribute("question", thisQuest);
			return "questionDetails.jsp";
		} else {
			//creates new answer instead of updating by manually setting id to null
			answer.setId(null);
			aServ.create(answer);
			return "redirect:/question/"+id;
		}
	
	}
	
}
