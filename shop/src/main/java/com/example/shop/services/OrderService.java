package com.example.shop.services;

import com.example.shop.models.Invoice;
import com.example.shop.models.Order;
import com.example.shop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InvoiceService invoiceService;

    public OrderService(OrderRepository orderRepository, InvoiceService invoiceService) {
        this.orderRepository = orderRepository;
        this.invoiceService = invoiceService;
    }

    public Invoice checkout(String customerName, BigDecimal amount) {
        Order order = new Order(customerName, amount);
        order = orderRepository.save(order);
        return invoiceService.createInvoice(order);
    }
}
