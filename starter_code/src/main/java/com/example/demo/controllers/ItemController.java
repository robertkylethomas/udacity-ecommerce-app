package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping
	public ResponseEntity<List<Item>> getItems() {
		return ResponseEntity.ok(itemRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable Long id) {
		Optional<Item> optionalItem = itemRepository.findById(id);
	  Item item = new Item();
		ResponseEntity<Item> responseEntity = null;

    System.out.println("==================");
    System.out.println(optionalItem);
    System.out.println("==================");

		optionalItem.ifPresentOrElse(item1 -> {
      item.setName(item1.getName());
      item.setId(item1.getId());
      item.setDescription(item1.getDescription());
      item.setPrice(item1.getPrice());
    }, () -> {


    });



	  return optionalItem.isPresent() ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Item>> getItemsByName(@PathVariable String name) {
		List<Item> items = itemRepository.findByName(name);
		if(items == null){
		  return ResponseEntity.notFound().build();
    }

		ResponseEntity.ok(items);

		return items == null || items.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(items);

	}

}
