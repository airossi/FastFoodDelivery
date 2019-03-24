package com.example.FastFoodDelivery.service

import com.example.FastFoodDelivery.model.Order
import com.example.FastFoodDelivery.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun getAllOrders(): List<Order> =
            orderRepository.findAll()

    fun createNewOrder(order: Order): Order =
            orderRepository.save(order)

    fun getOrderById(orderId: Long): Optional<Order> =
            orderRepository.findById(orderId)

    fun updateOrderById(orderId: Long, newOrder: Order): Optional<Order> {
        return orderRepository.findById(orderId).map { existingOrder ->
            val updatedOrder: Order = existingOrder
                    .copy(description = newOrder.description, price = newOrder.price)
            orderRepository.save(updatedOrder)
            Optional.of(updatedOrder)
        }.orElse(Optional.empty())
    }

    fun deleteOrderById(orderId: Long): Optional<Order> {
        return orderRepository.findById(orderId).map { order ->
            orderRepository.delete(order)
            Optional.of(order)
        }.orElse(Optional.empty())
    }

}