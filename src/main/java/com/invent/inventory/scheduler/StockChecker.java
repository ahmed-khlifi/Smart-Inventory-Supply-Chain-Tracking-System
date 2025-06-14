package com.invent.inventory.scheduler;

import com.invent.inventory.Iservice.IEmailService;
import com.invent.inventory.entity.Product;
import com.invent.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockChecker {
    @Autowired
    private ProductService service;
    @Autowired
    private IEmailService emailService;

    @Scheduled(cron = "0 0 9 * * *") // Every day at 9 AM
    public void checkStockLevels() {
        List<Product> lowStock = service.findAll().stream()
                .filter(p -> p.getQuantity() < 10)
                .toList();
        if (!lowStock.isEmpty()) {
            String subject = "Low-Stock Alert: " + lowStock.size() + " item(s)";
            String body = lowStock.stream()
                    .map(p -> p.getName() + " (ID: " + p.getId() + ") – Qty: " + p.getQuantity())
                    .collect(Collectors.joining("\n"));
            emailService.sendLowStockAlert(subject, body);
        }
    }
}
