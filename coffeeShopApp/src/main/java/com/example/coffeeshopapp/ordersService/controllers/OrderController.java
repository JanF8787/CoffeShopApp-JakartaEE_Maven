package com.example.coffeeshopapp.ordersService.controllers;

import com.example.coffeeshopapp.baristaService.models.Barista;
import com.example.coffeeshopapp.baristaService.services.BaristaService;
import com.example.coffeeshopapp.ordersService.models.Order;
import com.example.coffeeshopapp.ordersService.models.OrderDto;
import com.example.coffeeshopapp.ordersService.services.OrderService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Path("/orders")
public class OrderController {

    @Inject
    private OrderService orderService;

    @Inject
    private BaristaService baristaService;

    @POST
    @Path("/add_order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(OrderDto orderDto) {
        return Response.ok(orderService.addOrder(orderDto)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        List<OrderDto> orders = orderService.allOrders();

        if (orders.isEmpty()) {
            return Response.ok("All orders are done.").build();
        }

        return Response.ok(orders).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cancelOrder(@PathParam("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        Optional<Barista> barista = baristaService.findBaristaByOrderNumber(order.get().getOrderNumber());

        if (!barista.get().getStatus().equals("waiting")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Your order with number " + order.get().getOrderNumber() + " is in preparation, you can't cancel it!")
                    .build();
        }

        orderService.cancelOrder(id);

        barista.get().setStatus("canceled");
        baristaService.save(barista.get());

        return Response.ok("Your order number " + order.get().getOrderNumber() + " was canceled.").build();
    }

    @POST
    @Path("/notify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response notification(Order order) {
        orderService.deleteOrder(order);
        return Response.ok().build();
    }

}
