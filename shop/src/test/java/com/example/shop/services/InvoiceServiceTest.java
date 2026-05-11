package com.example.shop.services;

import com.example.shop.models.Invoice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("Testing with unstabled test!! with duplicated invoice number")
    void firstTwoOrdersGetInvoiceNumbersOneAndTwo() {
        CompletableFuture<Invoice> alice = CompletableFuture.supplyAsync(
                () -> orderService.checkout("customer1", BigDecimal.TEN));
        CompletableFuture<Invoice> bob = CompletableFuture.supplyAsync(
                () -> orderService.checkout("customer2", BigDecimal.TEN));

        String num1 = alice.join().getInvoiceNumber();
        String num2 = bob.join().getInvoiceNumber();

        assertEquals(Set.of("INV-00001", "INV-00002"), Set.of(num1, num2));
    }
}