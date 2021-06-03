package com.practice.springApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path="/springapp")
public class Controller {

	@Autowired
	private Repository repository;

	@GetMapping(path="/viewall")
	public Iterable<Relation> getAllRelations(){
		log.info("Get all Relations called with count: {}", repository.count());
		return repository.findAll();
	}

	@GetMapping(path="/view/{id}")
	public Relation getRelationById(@PathVariable long id){
		log.info("Get Relation based on id: {}", id);
		return repository.findById(id).orElse(null);
	}

	@PostMapping(path="/add")
	public Relation addRelation(@RequestBody Relation relation){
		log.info("Adding new Relation");
		repository.save(relation);
		log.info("New Relation added with total count: {}", repository.count());
		return relation;
	}

	@PutMapping(path="edit/{id}")
	public ResponseEntity editRelationById(@PathVariable long id, @RequestBody Relation relation){
		log.info("editing relation based on relationId: "+ id);
		Optional<Relation> rel = repository.findById(id);
		if(!rel.isPresent()){
			log.error("relation id is incorrect - EDIT failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect relationId - Edit failed");
		}
		relation.setId(id);
		repository.save(relation);
		return ResponseEntity.ok(repository.findById(id));
	}

	@DeleteMapping(path="delete/{id}")
	public ResponseEntity deleteRelationById(@PathVariable long id){
		log.info("Deleting relation based on relationId: "+ id);
		Optional<Relation> rel = repository.findById(id);
		if(!rel.isPresent()){
			log.error("relation id is incorrect - DELETE failed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect relationId - Delete failed");
		}
		repository.deleteById(id);
		log.info("relation has been deleted");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("relation has been deleted");
	}

}
