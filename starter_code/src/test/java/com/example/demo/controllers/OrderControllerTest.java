package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

  private OrderController orderController;
  private OrderRepository orderRepository = mock(OrderRepository.class);
  private UserRepository userRepository = mock(UserRepository.class);

  @Before
  public void setUp(){
    orderController = new OrderController();
    TestUtils.injectObjects(orderController, "orderRepository", orderRepository);
    TestUtils.injectObjects(orderController, "userRepository", userRepository);

    Item item = new Item();
    item.setId(1L);
    item.setName("Round Widget");
    BigDecimal price = BigDecimal.valueOf(2.99);
    item.setPrice(price);
    item.setDescription("A widget that is round");
    List<Item> items = new ArrayList<>();
    items.add(item);

    User user = new User();
    Cart cart = new Cart();
    user.setId(0);
    user.setUsername("test");
//    TODO ADD Password
//    user.setPassword("testPassword");
    cart.setId(0L);
    cart.setUser(user);
    cart.setItems(items);
    BigDecimal total = BigDecimal.valueOf(2.99);
    cart.setTotal(total);
    user.setCart(cart);
    when(userRepository.findByUsername("test")).thenReturn(user);
    when(userRepository.findByUsername("someone")).thenReturn(null);

  }

  @Test
  public void submitOrder() {
    ResponseEntity<UserOrder> userOrderResponseEntity = orderController.submit("test");
    assertNotNull(userOrderResponseEntity);
    assertEquals(200, userOrderResponseEntity.getStatusCodeValue());
    UserOrder userOrder = userOrderResponseEntity.getBody();
    assertNotNull(userOrder);
    assertEquals(1, userOrder.getItems().size());
  }

  @Test
  public void submitOrderUserNotFound() {
    ResponseEntity<UserOrder> userOrderResponseEntity = orderController.submit("someone");
    assertNotNull(userOrderResponseEntity);
    assertEquals(404, userOrderResponseEntity.getStatusCodeValue());
  }

  @Test
  public void getOrdersForUser() {
    ResponseEntity<List<UserOrder>> ordersForUser = orderController.getOrdersForUser("test");
    assertNotNull(ordersForUser);
    assertEquals(200, ordersForUser.getStatusCodeValue());
    List<UserOrder> orders = ordersForUser.getBody();
    assertNotNull(orders);

  }

  @Test
  public void getOrdersForUserNotFound() {
    ResponseEntity<List<UserOrder>> ordersForUser = orderController.getOrdersForUser("someone");
    assertNotNull(ordersForUser);
    assertEquals(404, ordersForUser.getStatusCodeValue());

  }

}
