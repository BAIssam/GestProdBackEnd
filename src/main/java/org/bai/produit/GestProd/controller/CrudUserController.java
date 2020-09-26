package org.bai.produit.GestProd.controller;

import java.util.List;

import org.bai.produit.GestProd.dao.UserRepository;
import org.bai.produit.GestProd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/GestProd/crud_user")
public class CrudUserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);		
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {		
		userRepository.deleteById(id);		
	}

}
