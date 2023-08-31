package com.example.coffeeshopapp.baristaService.models;

import jakarta.persistence.*;

@Entity
@Table(name = " baristas")
public class Barista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderNumber;
    private String status;

    public Barista() {
    }

    public Barista(Long id, Long orderNumber, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderId) {
        this.orderNumber = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
