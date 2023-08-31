package com.example.coffeeshopapp.baristaService.controller;

import com.example.coffeeshopapp.baristaService.services.BaristaService;
import com.example.coffeeshopapp.ordersService.models.Order;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.inject.Inject;

@Path("/barista")
public class BaristaController {

    @Inject
    private BaristaService baristaService;

    @POST
    @Path("/add_order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(Order order) {
        baristaService.addOrder(order);
        return Response.ok().build();
    }

}
