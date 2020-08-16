package com.keara.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.keara.dojooverflow.models.Answer;
import com.keara.dojooverflow.repositories.AnswerRepository;

@Service
public class AnswerService {
	private final AnswerRepository answerRepo;
	
	public AnswerService(AnswerRepository answerRepo) {
		this.answerRepo = answerRepo;
	}
	
	public Answer create(Answer a) {
		return answerRepo.save(a);
	}
	
	public List<Answer> getAll() {
		return (List<Answer>) answerRepo.findAll();
	}
	
	public Answer getOne(Long id) {
		Optional<Answer> a = answerRepo.findById(id);
		return a.isPresent() ? a.get() : null;
	}
	
	public Answer update(Answer a) {
		if(a.getId() == null) {
			return null;
		} else {
			return answerRepo.save(a);
		}
	}
}
