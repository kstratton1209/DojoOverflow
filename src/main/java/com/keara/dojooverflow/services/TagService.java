package com.keara.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.keara.dojooverflow.models.Tag;
import com.keara.dojooverflow.repositories.TagRepository;

@Service
public class TagService {
	
	private final TagRepository tagRepo;
	
	public TagService(TagRepository tagRepo) {
		this.tagRepo = tagRepo;
	}
	
	public Tag create(Tag t) {
		return tagRepo.save(t);
	}
	
	public List<Tag> getAll() {
		return (List<Tag>) tagRepo.findAll();
	}
	
	public Tag getOne(Long id) {
		Optional<Tag> tag = tagRepo.findById(id);
		return tag.isPresent() ? tag.get() : null;
	}
	
	public Tag update(Tag t) {
		if(t.getId() == null) {
			return null;
		} else {
			return tagRepo.save(t);
		}
	}
}
