package com.keara.dojooverflow.services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.keara.dojooverflow.models.Question;
import com.keara.dojooverflow.repositories.QuestionRepository;


@Service
public class QuestionService {
	
	private final QuestionRepository questionRepo;
	
	public QuestionService(QuestionRepository questionRepo) {
		this.questionRepo = questionRepo;
	}

	public Question create(Question question) {
		return questionRepo.save(question);
	}
	
	
	public Question update(Question q) {
		if(q.getId() == null) {
			return null;
		} else {
			return questionRepo.save(q);
		}
	}
	
	public List<Question> getAll() {
		return (List<Question>) questionRepo.findAll();
	}
	
	public Question getOne(Long id) {
		Optional<Question> question = questionRepo.findById(id);
		return question.isPresent() ? question.get() : null;
	}

}
