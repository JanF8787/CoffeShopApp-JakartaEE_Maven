package com.example.coffeeshopapp.baristaService.services;

import com.example.coffeeshopapp.baristaService.models.Barista;
import com.example.coffeeshopapp.ordersService.models.Order;

import java.util.Optional;

public interface BaristaService {

    Barista addOrder(Order order);
    void processOrders();
    Optional<Barista> findBaristaByOrderNumber(Long orderNumber);
    Barista save(Barista barista);
}
