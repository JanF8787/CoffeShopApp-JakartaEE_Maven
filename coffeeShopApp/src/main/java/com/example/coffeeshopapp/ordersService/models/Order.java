package com.example.coffeeshopapp.ordersService.models;

import com.example.coffeeshopapp.ordersService.models.enums.CoffeeSize;
import com.example.coffeeshopapp.ordersService.models.enums.CoffeeType;
import com.example.coffeeshopapp.ordersService.models.enums.MilkType;
import com.example.coffeeshopapp.ordersService.models.enums.OrderType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CoffeeType coffeeType;
    @Enumerated(EnumType.STRING)
    private CoffeeSize coffeeSize;
    @Enumerated(EnumType.STRING)
    private MilkType milkType;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private BigDecimal price;
    private Long orderNumber;

    public Order() {
    }

    public Order(Long id, CoffeeType coffeeType, CoffeeSize coffeeSize, MilkType milkType, OrderType orderType, BigDecimal price) {
        this.id = id;
        this.coffeeType = coffeeType;
        this.coffeeSize = coffeeSize;
        this.milkType = milkType;
        this.orderType = orderType;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public CoffeeSize getCoffeeSize() {
        return coffeeSize;
    }

    public void setCoffeeSize(CoffeeSize coffeeSize) {
        this.coffeeSize = coffeeSize;
    }

    public MilkType getMilkType() {
        return milkType;
    }

    public void setMilkType(MilkType milkType) {
        this.milkType = milkType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
