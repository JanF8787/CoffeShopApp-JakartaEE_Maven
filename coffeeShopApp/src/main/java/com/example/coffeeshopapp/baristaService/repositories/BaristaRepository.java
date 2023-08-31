package com.example.coffeeshopapp.baristaService.repositories;

import com.example.coffeeshopapp.baristaService.models.Barista;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BaristaRepository {

    @PersistenceContext(unitName = "coffeeShop")
    private EntityManager entityManager;

    @Transactional
    public Barista save(Barista barista) {
        entityManager.persist(barista);
        return entityManager.find(Barista.class, barista.getId());
    }

    @Transactional
    public void delete(Barista barista) {
        entityManager.remove(barista);
    }

    @Transactional
    public Optional<Barista> findByOrderNumber(Long orderNumber) {
        return entityManager.createQuery("SELECT b FROM Barista b WHERE b.orderNumber = :orderNumber", Barista.class)
                .setParameter("orderNumber", orderNumber)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Transactional
    public List<Barista> findByStatus(String status) {
        return entityManager.createQuery("SELECT b FROM Barista b WHERE b.status = :status", Barista.class)
                .setParameter("status", status)
                .getResultList();
    }
}
