package com.example.FastFoodDelivery.controller

import com.example.FastFoodDelivery.model.Order
import com.example.FastFoodDelivery.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    /**
     * List all orders.
     */
    @GetMapping("")
    fun getAllOrders(): List<Order> =
            orderService.getAllOrders()


    /**
     * Create a new order.
     */
    @PostMapping("")
    fun createNewOrder(@Valid @RequestBody order: Order): Order =
            orderService.createNewOrder(order)

    /**
     * Find order by Id.
     */
    @GetMapping("/{id}")
    fun getOrderById(@PathVariable(value = "id") orderId: Long): ResponseEntity<Order> {
        return orderService.getOrderById(orderId).map { order ->
            ResponseEntity.ok(order)
        }.orElse(ResponseEntity.notFound().build())
    }

    /**
     * Update order by Id.
     */
    @PutMapping("/{id}")
    fun updateOrderById(@PathVariable(value = "id") orderId: Long,
                        @Valid @RequestBody newOrder: Order): ResponseEntity<Order> {

        return orderService.updateOrderById(orderId, newOrder).map { order ->
            ResponseEntity.ok().body(order)
        }.orElse(ResponseEntity.notFound().build())

    }

    /**
     * Delete order by Id.
     */
    @DeleteMapping("/{id}")
    fun deleteOrderById(@PathVariable(value = "id") orderId: Long): ResponseEntity<Void> {

        return orderService.deleteOrderById(orderId).map { order ->
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}