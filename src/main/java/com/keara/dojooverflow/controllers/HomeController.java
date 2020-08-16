package com.keara.dojooverflow.controllers;

import java.util.ArrayList;

import javax.naming.Binding;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/newquestion")
	public String newquestion(Model model) {
		model.addAttribute("question", new Question());
		return "newquestion.jsp";
	}
	
	@PostMapping("/question/create")
	public String createquestion(@RequestParam(value = "question") String q, @RequestParam(value = "tags") String tags) {
		
//		if(result.hasErrors()) {
//			System.out.println(result.getAllErrors());
//			return "redirect:/newquestion";
//		} else {
			String[] listOfTags = tags.split(" ");
			Question newQuest = qServ.create(new Question(q));
			ArrayList<Tag> valid_tags = new ArrayList<Tag>();
			for(int i=0; i <listOfTags.length; i++) {
				valid_tags.add(tServ.create(new Tag(listOfTags[i])));
			}
			newQuest.setTags(valid_tags);
			qServ.update(newQuest);
			return "redirect:/";
//		}
		
		
	}
	
	@RequestMapping("/question/{id}")
	public String questionDetails(@PathVariable("id") Long q_id, Model model) {
		Question thisQuest = qServ.getOne(q_id);
		model.addAttribute("question", thisQuest);
		model.addAttribute("answers", thisQuest.getAnswers());
		return "questionDetails.jsp";
	}
	
	@PostMapping("/{id}/answer/create")
	public String createanswer(@PathVariable("id") Long q_id, @RequestParam(value = "answer") String answer) {
		Answer newAnswer = new Answer(answer);
		newAnswer.setQuestion(qServ.getOne(q_id));
		aServ.create(newAnswer);
		return "redirect:/question/"+q_id;

	
	}
	
}
