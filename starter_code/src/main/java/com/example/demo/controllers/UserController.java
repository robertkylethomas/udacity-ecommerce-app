package com.example.demo.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

  final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);

	  if(user == null){
	    return ResponseEntity.notFound().build();
    } else {
	    return ResponseEntity.of(user);
    }

	}

	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		User user = new User();
		user.setUsername(createUserRequest.getUsername());

    /*
    * TODO THIS NEEDS TO VALIDATE THE Passwords
    *  if the password length is less than 6
    * or the confirm and the password doesnt match
    * */

//    if (
//      (createUserRequest.getPassword().length() <= 6) ||
//        !(createUserRequest.getPassword().equals(createUserRequest.getConfirmationPassword()))
//    ) {
//
//      System.out.println(  (createUserRequest.getPassword().length() <= 6) ||
//        !(createUserRequest.getPassword().equals(createUserRequest.getConfirmationPassword())));
////      log.error("Cannot create user {} because the password is invalid", createUserRequest.getUsername());
//      return ResponseEntity.badRequest().build();
//    }

    System.out.println(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);

		userRepository.save(user);
	  logger.info("Created user with user name {}", user.getUsername());
		return ResponseEntity.ok(user);
	}



}
