package com.example.coffeeshopapp.baristaService.services;

import com.example.coffeeshopapp.baristaService.models.Barista;
import com.example.coffeeshopapp.baristaService.repositories.BaristaRepository;
import com.example.coffeeshopapp.ordersService.models.Order;
import com.example.coffeeshopapp.ordersService.repositories.OrderRepository;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class DatabaseBaristaService implements BaristaService{

    @Inject
    private BaristaRepository baristaRepository;
    @Inject
    private OrderRepository orderRepository;
    @Inject
    private Client restClient;

    @Override
    public Barista addOrder(Order order) {
        Barista barista = new Barista();

        barista.setOrderNumber(order.getOrderNumber());
        barista.setStatus("waiting");

        baristaRepository.save(barista);
        return barista;
    }

    @Override
    @Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
    public void processOrders() {
        List<Barista> baristas = baristaRepository.findByStatus("waiting");

        for (Barista barista : baristas) {

            timer(10);

            if (!barista.getStatus().equals("canceled")) {
                barista.setStatus("in preparation");
                baristaRepository.save(barista);
                timer(10);

                barista.setStatus("finished");
                baristaRepository.save(barista);
                timer(10);

                barista.setStatus("picked up");
                baristaRepository.save(barista);

                Optional<Order> order = orderRepository.findById(barista.getOrderNumber());
                restClient.target("http://localhost:8080/coffeeShopApp-1.0-SNAPSHOT/orders/notify")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(order, MediaType.APPLICATION_JSON));

                baristaRepository.delete(barista);
            }
        }
    }

    private void timer(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Optional<Barista> findBaristaByOrderNumber(Long orderNumber) {
        return baristaRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public Barista save(Barista barista) {
        return baristaRepository.save(barista);
    }
}
