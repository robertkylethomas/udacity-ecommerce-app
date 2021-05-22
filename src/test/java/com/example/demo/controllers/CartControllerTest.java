package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CartControllerTest {

  private CartController cartController;
  private UserRepository userRepository = mock(UserRepository.class);
  private CartRepository cartRepository  = mock(CartRepository.class);
  private ItemRepository itemRepository = mock(ItemRepository.class);

  @Before
  public void setUp(){
    cartController = new CartController();
    TestUtils.injectObjects(cartController, "userRepository", userRepository);
    TestUtils.injectObjects(cartController, "cartRepository", cartRepository);
    TestUtils.injectObjects(cartController, "itemRepository", itemRepository);

    User user = new User();
    Cart cart = new Cart();
    user.setId(0L);
    user.setUsername("test");
    // TODO ADD PASSWORD
    user.setCart(cart);
    when(userRepository.findByUsername("test")).thenReturn(user);

    Item item = new Item();
    item.setId(1L);
    item.setName("Round Widget");
    BigDecimal price = BigDecimal.valueOf(2.99);
    item.setPrice(price);
    item.setDescription("A widget that is round");
    when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(item));
  }

  @Test
  public void addToCart(){
    ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
    modifyCartRequest.setItemId(1L);
    modifyCartRequest.setQuantity(1);
    modifyCartRequest.setUsername("test");

    ResponseEntity<Cart> cartResponseEntity = cartController.addTocart(modifyCartRequest);
    assertNotNull(cartResponseEntity);
    assertEquals(200, cartResponseEntity.getStatusCodeValue());
    Cart cart = cartResponseEntity.getBody();
    assertNotNull(cart);
    assertEquals(BigDecimal.valueOf(2.99), cart.getTotal());

  }



  @Test
  public void removeItemFromCart() {
    // Set up test by adding two items to cart.
    ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
    modifyCartRequest.setItemId(1L);
    modifyCartRequest.setQuantity(2);
    modifyCartRequest.setUsername("test");
    ResponseEntity<Cart> response = cartController.addTocart(modifyCartRequest);
    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());

    modifyCartRequest = new ModifyCartRequest();
    modifyCartRequest.setItemId(1L);
    modifyCartRequest.setQuantity(1);
    modifyCartRequest.setUsername("test");
    response = cartController.removeFromcart(modifyCartRequest);

    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
    Cart cart = response.getBody();
    assertNotNull(cart);
    assertEquals(BigDecimal.valueOf(2.99), cart.getTotal());

  }

  @Test
  public void invalidUserRemovesItems() {
    ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
    modifyCartRequest.setItemId(1L);
    modifyCartRequest.setQuantity(1);
    modifyCartRequest.setUsername("boo");
    ResponseEntity<Cart> response = cartController.removeFromcart(modifyCartRequest);

    assertNotNull(response);
    assertEquals(404, response.getStatusCodeValue());
  }

}
