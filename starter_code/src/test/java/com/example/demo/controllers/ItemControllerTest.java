package com.example.demo.controllers;


import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {

  private ItemController itemController;
  private ItemRepository itemRepository = mock(ItemRepository.class);

  @Before
  public void setUp() {
    itemController = new ItemController();
    TestUtils.injectObjects(itemController, "itemRepository", itemRepository);
    Item item = new Item();
    item.setId(1L);
    item.setName("Round Widget");
    BigDecimal price = BigDecimal.valueOf(2.99);
    item.setPrice(price);
    item.setDescription("An item that is round");

    when(itemRepository.findAll()).thenReturn(Collections.singletonList(item));
    when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(item));
    when(itemRepository.findByName("Round Widget")).thenReturn(Collections.singletonList(item));

  }


  @Test
  public void findAllItems(){
    ResponseEntity<List<Item>> responseEntity = itemController.getItems();
    assertNotNull(responseEntity);
    assertEquals(200, responseEntity.getStatusCodeValue());
    List<Item> items = responseEntity.getBody();
    assertNotNull(items);
    assertEquals(1, items.size());
  }

  @Test
  public void getItemByID(){
    ResponseEntity<Item> responseEntity = itemController.getItemById(1L);
    assertNotNull(responseEntity);
    assertEquals(200, responseEntity.getStatusCodeValue());
    Item item = responseEntity.getBody();
    assertNotNull(item);
  }

  @Test
  public void getItemByIDNotFound(){

    // TODO doesnt work so lekker
    ResponseEntity<Item> responseEntity = itemController.getItemById(2L);
    assertNotNull(responseEntity);
    System.out.println(responseEntity);
    assertEquals(404, responseEntity.getStatusCodeValue());

  }

  @Test
  public void getItemsByNameNotFound(){

    ResponseEntity<List<Item>> responseEntity = itemController.getItemsByName("Square Widget");
    assertNotNull(responseEntity);
    assertEquals(404, responseEntity.getStatusCodeValue());

  }



}
