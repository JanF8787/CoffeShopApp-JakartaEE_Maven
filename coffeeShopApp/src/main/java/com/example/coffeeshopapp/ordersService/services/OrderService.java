package com.example.coffeeshopapp.ordersService.services;

import com.example.coffeeshopapp.ordersService.models.Order;
import com.example.coffeeshopapp.ordersService.models.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order addOrder(OrderDto orderDto);
    List<OrderDto> allOrders();
    void cancelOrder(Long id);
    void deleteOrder(Order order);
    void forwardOrderToBarista(Order order);
    Optional<Order> findById(Long id);
}
