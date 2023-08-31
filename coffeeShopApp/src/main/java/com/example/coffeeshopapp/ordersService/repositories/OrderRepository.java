package com.example.coffeeshopapp.ordersService.repositories;

import com.example.coffeeshopapp.ordersService.models.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderRepository {

    @PersistenceContext(unitName = "coffeeShop")
    private EntityManager entityManager;

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    @Transactional
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Transactional
    public void delete(Order order) {
        entityManager.remove(order);
    }

    @Transactional
    public void deleteById(Long id) {
        entityManager.createQuery("DELETE FROM Order o WHERE o.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public Optional<Order> findById(Long id) {
        return entityManager.createQuery("SELECT o FROM Order o WHERE o.id = :id", Order.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

}
