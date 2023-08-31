package com.example.coffeeshopapp.ordersService.services;

import com.example.coffeeshopapp.baristaService.models.Barista;
import com.example.coffeeshopapp.baristaService.repositories.BaristaRepository;
import com.example.coffeeshopapp.ordersService.models.Order;
import com.example.coffeeshopapp.ordersService.models.OrderDto;
import com.example.coffeeshopapp.ordersService.repositories.OrderRepository;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class DatabaseOrderService implements OrderService{

    @Inject
    private OrderRepository orderRepository;
    @Inject
    private BaristaRepository baristaRepository;
    @Inject
    private Client restClient;

    @Override
    public Order addOrder(OrderDto orderDto) {
        Order order = new Order();

        order.setCoffeeType(orderDto.getCoffeeType());
        order.setCoffeeSize(orderDto.getCoffeeSize());
        order.setMilkType(orderDto.getMilkType());
        order.setOrderType(orderDto.getOrderType());
        order.setPrice(orderDto.getPrice());

        orderRepository.save(order);

        order.setOrderNumber(order.getId());
        orderRepository.save(order);

        forwardOrderToBarista(order);

        return order;
    }

    @Override
    public List<OrderDto> allOrders() {
        List<Order> orders = orderRepository.findAll();

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();

            orderDto.setOrderNumber(order.getOrderNumber());
            orderDto.setCoffeeType(order.getCoffeeType());
            orderDto.setCoffeeSize(order.getCoffeeSize());
            orderDto.setMilkType(order.getMilkType());
            orderDto.setOrderType(order.getOrderType());
            orderDto.setPrice(order.getPrice());

            Optional<Barista> barista = baristaRepository.findByOrderNumber(order.getOrderNumber());
            barista.ifPresent(b -> orderDto.setStatus(b.getStatus()));

            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void forwardOrderToBarista(Order order) {
        String baristaServiceUrl = "http://localhost:8080/coffeeShopApp-1.0-SNAPSHOT/barista/add_order";
        Response response = restClient.target(baristaServiceUrl)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(order, MediaType.APPLICATION_JSON));
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

}
