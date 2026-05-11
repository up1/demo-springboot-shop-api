package com.example.shop.services;

import com.example.shop.models.Invoice;
import com.example.shop.models.Order;
import com.example.shop.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private InvoiceNumberGenerator generator;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(Order order) {
        if (generator == null) {
            generator = createGenerator();
        }
        String invoiceNumber = generator.nextNumber();
        Invoice invoice = new Invoice(invoiceNumber, order);
        return invoiceRepository.save(invoice);
    }

    private InvoiceNumberGenerator createGenerator() {
        Integer maxNumber = invoiceRepository.findMaxInvoiceNumber();
        int startingNumber = (maxNumber != null) ? maxNumber : 0;
        return new InvoiceNumberGenerator(startingNumber);
    }
}
