package com.example.FastFoodDelivery.repository

import com.example.FastFoodDelivery.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long>