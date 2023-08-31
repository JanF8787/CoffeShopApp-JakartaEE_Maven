package com.example.coffeeshopapp.ordersService.models;

import com.example.coffeeshopapp.ordersService.models.enums.CoffeeSize;
import com.example.coffeeshopapp.ordersService.models.enums.CoffeeType;
import com.example.coffeeshopapp.ordersService.models.enums.MilkType;
import com.example.coffeeshopapp.ordersService.models.enums.OrderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public class OrderDto {
    private Long orderNumber;
    @Enumerated(EnumType.STRING)
    private CoffeeType coffeeType;
    @Enumerated(EnumType.STRING)
    private CoffeeSize coffeeSize;
    @Enumerated(EnumType.STRING)
    private MilkType milkType;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private BigDecimal price;
    private String status;

    public OrderDto() {
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
