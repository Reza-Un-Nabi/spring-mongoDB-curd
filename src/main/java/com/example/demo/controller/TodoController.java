package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Todo;
import com.example.demo.repositories.TodoRepositories;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	TodoRepositories todoRepository;


	@GetMapping("/getTodos") public List<Todo> getAllTodos() { Sort
		sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt"); return
				todoRepository.findAll(sortByCreatedAtDesc); }


	// @PostMapping("/todos")
	@RequestMapping(value = "/todos", method=RequestMethod.POST)
	public Todo createTodo( @RequestBody Todo todo) {
		System.out.println(todo.getTitle());
		System.out.println(todo.getCompleted());
		//todo.setCompleted(false);
		return todoRepository.save(todo);
	}


	@GetMapping(value="/todos/{id}") 
	public ResponseEntity<Todo>getTodoById(@PathVariable("id") String id) {

		return todoRepository.findById(id) .map(todo -> ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value="/todos/{id}") 
	public ResponseEntity<Todo>updateTodo(@PathVariable("id") String id,
			@Valid @RequestBody Todo todo) { 
		return todoRepository.findById(id).map(todoData -> { 
			todoData.setTitle(todo.getTitle());
			todoData.setCompleted(todo.getCompleted()); 
			Todo updatedTodo = todoRepository.save(todoData); return ResponseEntity.ok().body(updatedTodo);
		}).orElse(ResponseEntity.notFound().build()); 

	}

	@DeleteMapping(value="/todos/{id}") 
	public ResponseEntity<?>deleteTodo(@PathVariable("id") String id) { 

		return todoRepository.findById(id) .map(todo -> { 
			todoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build()); 
	}


}
